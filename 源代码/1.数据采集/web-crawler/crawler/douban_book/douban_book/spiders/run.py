from scrapy.cmdline import execute

# 启动类
if __name__ == '__main__':
    execute("scrapy crawl book".split())
