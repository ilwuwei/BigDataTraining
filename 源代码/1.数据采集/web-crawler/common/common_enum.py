from enum import Enum, unique


# 该注解为了避免值重复的情况
@unique
class DbEnum(Enum):
    INSERT_OK = "添加成功"
    UPDATE_OK = "修改成功"
    DELETE_OK = "删除成功"
    QUERY_OK = "查询成功"

    INSERT_FAIL = "添加失败"
    UPDATE_FAIL = "修改失败"
    DELETE_FAIL = "删除失败"
    QUERY_FAIL = "查询失败"

    CRUD_OK = "操作成功"
    CRUD_FAIL = "操作失败"

    TRUNCATE_OK = "清空表成功"
    TRUNCATE_FAIL = "清空表失败"
