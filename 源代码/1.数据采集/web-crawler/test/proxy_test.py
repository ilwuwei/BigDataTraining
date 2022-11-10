import httpx
from fake_useragent import UserAgent
from common.proxy import Proxy


headers = {
    "User-Agent": UserAgent().random
}

resp = httpx.get("https://www.baidu.com/s?wd=ip", proxies=Proxy().getProxyIp(), headers=headers)
print(resp.status_code)

with open("baidu.html", "w", encoding="utf-8") as f:
    f.write(resp.text)
