package cn.cz.util

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * @author sjj
 * @date 2022/10/7 9:40
 * @version 1.0
 */
object MysqlUtils {

  /*
  * 保存分析数据到MySQL的方法
  * */
  def jdbcSave(df: DataFrame, tableName: String, saveMode: SaveMode = SaveMode.Overwrite): Unit = {
    df.write
      .format("jdbc")
      .mode(saveMode)
      .option("url", "jdbc:mysql://master:3306/bigdata")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", tableName)
      .save()
  }

  /*
  * 读取MySQL中的表
  * */
  def readMysql(tableName: String, spark: SparkSession): DataFrame = {
    spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://master:3306/bigdata")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", tableName)
      .load()
  }

}
