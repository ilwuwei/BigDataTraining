from scrapy import signals
from common.proxy import Proxy
import random
from crawler.douban_book.douban_book import settings


class DoubanBookDownloaderMiddleware:

    def process_request(self, request, spider):
        """拦截请求, 配置请求头信息"""
        request.headers["User-Agent"] = Proxy.getUserAgent()
        # 从ip池中取出一个ip
        ip = random.choice(settings.proxy_pool)
        # 为request请求添加ip
        request.meta['proxy'] = ip
        # 如果循环次数大于50, 则往ip池中, 重新填充一批ip
        # if settings.count > 50:
        #     settings.count = 0
        #     settings.proxy_pool = Proxy().getProxyIpPool()
        # # 每发送一次请求, 计数+1
        # settings.count += 1
        return None

    def process_exception(self, request, exception, spider):
        """当请求失败时, 更换ip代理重新请求"""
        ip = random.choice(settings.proxy_pool)
        request.meta['proxy'] = ip
        return request
        # return None
