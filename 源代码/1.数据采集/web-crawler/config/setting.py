from dbutils.pooled_db import PooledDB
import pymysql
import redis


class MysqlConfig:
    '''mysql配置类'''
    MYSQL_HOST = "192.168.137.11"
    MYSQL_PORT = 3306
    MYSQL_USER = "root"
    MYSQL_PASSWORD = "123456"
    MYSQL_DATABASE = "bigdata"
    MYSQL_CHARSET = "utf8"

    # mysql连接池
    POOL = PooledDB(
        creator=pymysql,
        maxconnections=6,  # 最大连接数
        mincached=2,  # 初始空闲连接数
        maxcached=5,  # 最大空闲连接数据
        maxusage=0,  # 单个连接的最大重用次数（默认为0或None表示无限重用）
        blocking=True,  # 连接池中如果没有可用连接后，是否阻塞等待。True: 等待; False: 不等待然后报错

        host=MYSQL_HOST,
        port=MYSQL_PORT,
        user=MYSQL_USER,
        password=MYSQL_PASSWORD,
        database=MYSQL_DATABASE,
        charset=MYSQL_CHARSET
    )


class LogConfig:
    # 日志文件保存的路径
    LOG_PATH = "D:\CodeFile\BIGDATA\web-crawler\log"


class RedisPool:
    """redis连接池"""
    REDIS_HOST = "192.168.137.11"
    REDIS_PASSWORD = "123456"
    REDIS_PORT = 6379
    REDIS_DB = 1

    redisPool = redis.ConnectionPool(host=REDIS_HOST, port=REDIS_PORT,
                                     password=REDIS_PASSWORD, db=REDIS_DB)
