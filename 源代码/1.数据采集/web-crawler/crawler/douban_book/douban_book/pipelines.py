from common.mysql import MysqlHelp


class DoubanBookPipeline:
    def __init__(self):
        self.db = MysqlHelp()

    def process_item(self, item, spider):
        sql = "insert into c_book values " \
              "(null, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
        value = list(dict(item).values())
        self.db.insert(sql, value)
        return item
