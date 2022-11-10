from common.mysql import MysqlHelp
from common.common_enum import DbEnum
from common.mylogger import MyLogger
from common.proxy import Proxy
from parsel import Selector
import httpx
import re


class Category:
    def __init__(self):
        self.db = MysqlHelp()
        self.url = "https://book.douban.com/tag/?view=type"
        self.header = {
            "User-Agent": Proxy.getUserAgent()
        }
        self.logger = MyLogger().logHandler(__file__)
        self.tag_map = {"文学": 1, "流行": 2, "文化": 3, "生活": 4, "经管": 5, "科技": 6}

    def getOrigin(self):
        """获取网页源码"""
        return httpx.get(self.url, headers=self.header).text

    def parse(self, html):
        """解析网页数据"""
        selector = Selector(html)
        div_list = selector.css(".article div:nth-child(2) div")
        for div in div_list:
            tag = div.css(".tag-title-wrapper::attr(name)").get()
            td_list = div.css("td")
            for td in td_list:
                data_dict = {
                    "name": td.css("a::text").get(),
                    "number": re.sub("[()]", "", td.css("b::text").get()),
                    "url": "https://book.douban.com" + td.css("a::attr(href)").get(),
                    "tag_code": self.tag_map[tag]
                }
                self.save(data_dict)
                # 记录日志
                self.logger.success(f"{DbEnum.INSERT_OK.value}[c_category]: {list(data_dict.values())}")

    def save(self, data_dict: dict):
        """保存数据到mysql"""
        sql = "insert into c_category values (null, %s, %s, %s, %s)"
        data = list(data_dict.values())
        self.db.insert(sql, data)

    def run(self):
        self.parse(self.getOrigin())


if __name__ == '__main__':
    # category = Category()
    # category.run()
    pass
