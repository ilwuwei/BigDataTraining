# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class DoubanBookItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    book_name = scrapy.Field()  # 书名
    author = scrapy.Field()  # 作者
    publisher = scrapy.Field()  # 出版社
    produce = scrapy.Field()  # 出品方
    pubTime = scrapy.Field()  # 出版时间
    pageNum = scrapy.Field()  # 页数
    price = scrapy.Field()  # 价格
    binding = scrapy.Field()  # 装帧
    ISBN = scrapy.Field()  # ISBN
    category = scrapy.Field()  # 类别
    score = scrapy.Field()  # 评分
    commented = scrapy.Field()  # 评论条数
    link = scrapy.Field()  # 链接
    pass
