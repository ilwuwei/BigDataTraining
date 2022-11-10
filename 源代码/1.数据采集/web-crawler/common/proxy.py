from fake_useragent import UserAgent
from jsonpath import jsonpath
import httpx


class Proxy:
    """爬虫代理类"""

    @staticmethod
    def getUserAgent():
        """UA池"""
        return UserAgent().random

    @staticmethod
    def getProxyIp():
        """代理ip"""
        resp = httpx.get(
            "http://http.tiqu.letecs.com/getip3?num=1&type=2&pro=&city=0&yys=0&port=11&time=1&ts=0&ys=0&cs=0&lb=1&sb=0&pb=4&mr=1&regions=&gm=4"
        ).json()
        proxies = {
            "https://": f"http://{jsonpath(resp, '$..ip')[0]}:{jsonpath(resp, '$..port')[0]}",
            "http://": f"http://{jsonpath(resp, '$..ip')[0]}:{jsonpath(resp, '$..port')[0]}"
        }
        return proxies

    @staticmethod
    def getProxyIpPool():
        """https代理ip池"""
        global data

        resp = httpx.get(
            "http://http.tiqu.letecs.com/getip3?num=10&type=2&pro=&city=0&yys=0&port=11&time=1&ts=0&ys=0&cs=0&lb=1&sb=0&pb=4&mr=1&regions=&gm=4"
        )

        ip_pools = []
        if resp.status_code == 200:
            data = resp.json()["data"]

        for item in data:
            ip_pools.append(
                f"https://{item['ip']}:{item['port']}"
            )

        return ip_pools
