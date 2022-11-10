from common.common_enum import DbEnum
from config.setting import MysqlConfig
from common.mylogger import MyLogger


class MysqlHelp:
    # mysql连接池
    __MYSQL_POOL = MysqlConfig.POOL

    def __init__(self):
        # 日志模块
        self.logger = MyLogger().logHandler(__file__)
        # 数据库连接相关
        self.connect = None
        self.cursor = None

    def insert(self, insert_sql, *args):
        """添加操作"""
        self.__edit(insert_sql, *args)

    def delete(self, delete_sql, *args):
        """删除操作"""
        self.__edit(delete_sql, *args)

    def update(self, update_sql, *args):
        """修改操作"""
        self.__edit(update_sql, *args)

    def fetch_one(self, sql, *args):
        """
        获取一条数据
        :param sql:
        :return: 元组类型的一条记录
        """
        try:
            self.__open()
            self.cursor.execute(sql, *args)
            return self.cursor.fetchone()
        except Exception as e:
            self.logger.error(DbEnum.QUERY_FAIL.value + f": {e}")
            self.connect.rollback()
        finally:
            self.__close()

    def fetch_many(self, sql, *args):
        """
        获取多条数据
        :param sql:
        :return: 二维元组, 其中每一项为一条记录
        """
        try:
            self.__open()
            self.cursor.execute(sql, *args)
            return self.cursor.fetchall()
        except Exception as e:
            self.logger.error(DbEnum.QUERY_FAIL.value + f": {e}")
            self.connect.rollback()
        finally:
            self.__close()

    def truncate(self, table_name):
        """清空数据表"""
        try:
            self.__open()
            sql = f"truncate table {table_name}"
            self.cursor.execute(sql)
        except Exception as e:
            self.logger.error(DbEnum.TRUNCATE_FAIL.value + f": {e}")
            self.connect.rollback()
        # else:
        #     self.logger.success(DbEnum.TRUNCATE_OK.value + f"[{table_name}]")
        finally:
            self.__close()

    def __edit(self, sql, *args):
        """封装通用的增删改操作"""
        try:
            self.__open()
            self.cursor.execute(sql, *args)
        except Exception as e:
            self.logger.error(DbEnum.CRUD_FAIL.value + f": {e}")
            self.connect.rollback()
        # else:
        #     # 操作日志输出
        #     crud = sql.split(" ")[0].upper()
        #     if crud == "INSERT":
        #         self.logger.success(DbEnum.INSERT_OK.value + f": {args[0]}")
        #     elif crud == "UPDATE":
        #         self.logger.success(DbEnum.UPDATE_OK.value)
        #     elif crud == "DELETE":
        #         self.logger.success(DbEnum.DELETE_OK.value)
        #     else:
        #         self.logger.success(DbEnum.CRUD_OK.value)
        finally:
            self.__close()

    def __open(self):
        """获取数据库连接-游标"""
        self.connect = MysqlHelp.__MYSQL_POOL.connection()
        self.cursor = self.connect.cursor()

    def __close(self):
        """关闭资源"""
        # 统一在关闭资源前提交事务, 则不需要在每次CRUD后再操作
        self.connect.commit()
        if self.cursor is not None:
            self.cursor.close()
        if self.connect is not None:
            self.connect.close()
