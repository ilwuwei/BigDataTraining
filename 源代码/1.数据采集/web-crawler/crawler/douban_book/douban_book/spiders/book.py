import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from common.mylogger import MyLogger
from common.mysql import MysqlHelp
from crawler.douban_book.douban_book import settings
from common.proxy import Proxy
from config.setting import RedisPool
from crawler.douban_book.douban_book.items import DoubanBookItem
import re
import redis


class BookSpider(CrawlSpider):
    name = 'book'
    allowed_domains = ['book.douban.com']
    # start_urls = ['https://book.douban.com/tag/']

    rules = (
        # 图书列表页链接提取器
        # Rule(LinkExtractor(allow=r'/tag/.*?type=T'), callback='parse_item', follow=True),
    )

    def __init__(self, *a, **kw):
        super().__init__(*a, **kw)
        self._logger = MyLogger().logHandler(__file__)
        # redis连接池
        self.redisPool = RedisPool.redisPool
        # mysql连接
        self.db = MysqlHelp()
        """初始化ip代理池"""
        settings.proxy_pool = Proxy().getProxyIpPool()

        """增量式爬取数据"""
        # 获取一个redis连接
        conn = redis.Redis(connection_pool=self.redisPool)
        # 删除原有的redis中存在有图书连接的set集合
        conn.delete("book")
        # 将mysql中已经存在book图书的连接缓存到redis的sex集合
        link = self.db.fetch_many("select link from c_book")
        link = [i[0] for i in link]
        if len(link) != 0:
            conn.sadd("book", *link)
        conn.close()

    def start_requests(self):
        """重写start_requests替代start_urls"""
        url_list = [i[0] for i in self.db.fetch_many("select url from c_category")]
        for url in url_list:
            url = url + "?start=0"
            yield scrapy.Request(url=url, callback=self.parse_item)

    def parse_item(self, response):
        """解析图书列表页"""
        try:
            category = response.xpath('//*[@id="content"]/h1//text()').get().split(" ")[-1]
            div_list = response.css("div.info")
            for div in div_list:
                link = div.css('h2 > a::attr(href)').get()
                # 获取一个redis连接
                conn = redis.Redis(connection_pool=self.redisPool)
                # 判断该link连接是否已经存在于redis缓存中
                result = conn.sismember('book', link)
                # 如果存在于redis缓存, 则该数据已经爬取成功,
                if result:
                    continue
                # 如果不存在与redis缓存, 则添加进去, 并爬取数据
                conn.sadd("book", link)
                conn.close()
                # 根据link连接获取具体数据
                yield scrapy.Request(
                    url=link, callback=self.parse,
                    meta={"category": category, "link": link})

            # 分页下载
            url = response.url.split("=")[0] + "=" + str(int(response.url.split("=")[1]) + 20)
            yield scrapy.Request(url=url, callback=self.parse_item)

        except Exception as e:
            self._logger.error(f"{e}, 错误链接: {response.url}")
            return

    def parse(self, response):
        """解析详情页"""
        item = DoubanBookItem()
        content = response.text

        """由于网页的特殊性, 采用"""
        book_name = re.compile(r'<span property="v:itemreviewed">(?P<book_name>.*?)</span>', re.S)  # 书名
        author = re.compile(r'作者</span>.*?<a.*?>(?P<author>.*?)</a>', re.S)  # 作者
        publisher = re.compile(r'<span class="pl">出版社.*?<a.*?>(?P<publisher>.*?)</a>', re.S)  # 出版社
        produce = re.compile(r'出品方:</span>.*?<a.*?>(?P<produce>.*?)</a>', re.S)  # 出品方
        pubTime = re.compile(r'出版年:</span> (?P<pubTime>.*?)<br/>', re.S)  # 出版时间
        pageNum = re.compile(r'页数:</span> (?P<pageNum>.*?)<br/>', re.S)  # 页数
        price = re.compile(r'定价:</span> (?P<price>.*?)<br/>', re.S)  # 价格
        binding = re.compile(r'装帧:</span> (?P<binding>.*?)<br/>', re.S)  # 装帧
        ISBN = re.compile(r'<span class="pl">ISBN:</span> (?P<ISBN>\d+)<br/>', re.S)  # ISBN
        score = re.compile(r'<strong class="ll rating_num " property="v:average"> (?P<score>.*?) </strong>', re.S)  # 评分
        commented = re.compile(r'<span property="v:votes">(?P<commented>.*?)</span>人评价</a>', re.S)  # 评论条数

        item["book_name"] = self.getContent(book_name, content)
        item["author"] = self.getContent(author, content)
        item["publisher"] = self.getContent(publisher, content)
        item["produce"] = self.getContent(produce, content)
        item["pubTime"] = self.getContent(pubTime, content)
        item["pageNum"] = self.getContent(pageNum, content)
        item["price"] = self.getContent(price, content)
        item['binding'] = self.getContent(binding, content)
        item["ISBN"] = self.getContent(ISBN, content)
        item["category"] = response.meta["category"]
        item["score"] = self.getContent(score, content)
        item["commented"] = self.getContent(commented, content)
        item["link"] = response.meta["link"]

        self._logger.success(dict(item))

        yield item

    def getContent(self, pattern, content):
        """正则提取数据的异常处理"""
        try:
            item = pattern.search(content).group(1)
            return item
        except Exception as e:
            return ""
