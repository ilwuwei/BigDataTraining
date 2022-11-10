package com.zys.spark.config

import com.typesafe.config.{Config, ConfigFactory}

/**
 * @author ilwuwei
 * @Date 2022/9/30 10:06
 * @version 1.0
 */
object ApplicationConfig {

  /*
    加载配置文件
   */
  private val config: Config = ConfigFactory.load("config.properties")

  /*
      运行模式，开发测试为本地模式
   */
  lazy val APP_LOCAL_MODE: Boolean = config.getBoolean("app.is.local")
  lazy val APP_SPARK_MASTER: String = config.getString("app.spark.master")


  /*
      是否集成hive，及hive MetaStore地址
   */

  lazy val APP_IS_HIVE: Boolean = config.getBoolean("app.is.hive")
  lazy val APP_HIVE_METASTORE_URIS: String = config.getString("app.hive.metastore.uris")
  lazy val APP_SPARK_WAREHOUSE_DIR: String = config.getString("app.spark.sql.warehouse.dir")

  /*
    mysql数据库连接信息
   */

  lazy val MYSQL_JDBC_DRIVER: String = config.getString("mysql.jdbc.driver")
  lazy val MYSQL_JDBC_URL: String = config.getString("mysql.jdbc.url")
  lazy val MYSQL_JDBC_USERNAME: String = config.getString("mysql.jdbc.username")
  lazy val MYSQL_JDBC_PASSWORD: String = config.getString("mysql.jdbc.password")

}
