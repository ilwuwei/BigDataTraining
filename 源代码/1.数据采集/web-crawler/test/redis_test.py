import redis

from config.setting import RedisPool
from common.mysql import MysqlHelp
redisPool = RedisPool.redisPool
conn = redis.Redis(connection_pool=redisPool)

# 添加键
conn.sadd("nihao", *(124, 435, 456, 456, 45456, "hello平"))
# print(conn.sismember("nihao", "hello"))  # 判断集合是否包含
# print(conn.sismember("nihao", "hello平"))
# conn.delete("book")



# db = MysqlHelp()
# link = db.fetch_many("select link from c_book")
# link = [i[0] for i in link]
conn.close()