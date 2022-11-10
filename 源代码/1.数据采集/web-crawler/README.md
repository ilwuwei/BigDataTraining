创建scrapy爬虫项目(CrawlSpider)
~~~bash
1. scrapy startproject douban_book
2. cd douban_book
3. scrapy genspider -t crawl book book.douban.com
~~~
运行项目
~~~python
from scrapy.cmdline import execute

# 启动类
if __name__ == '__main__':
    execute("scrapy crawl book".split())
~~~

.gitigonre文件不生效解决方法
~~~shell
git rm -r --cached .
~~~
