package com.zys.spark.preprocess

import com.zys.spark.pojo.Book
import com.zys.spark.util.MyUdf.{priceFieldUdf, timeFiledUdf}
import com.zys.spark.util.SparkUtils
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.types.{StringType, StructType}
import org.apache.spark.sql.{DataFrame, Encoders, SparkSession}

/**
 * @author ilwuwei
 * @Date 2022/9/30 10:09
 * @version 1.0
 */
object run {


  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkUtils.createSparkSession(this.getClass)

    // 从case class获取schema
    val bookSchema: StructType = Encoders.product[Book].schema

    // 读取hdfs上的数据
    var df = spark.read
      .format("csv")
      .option("sep", "\001")
      .option("encoding", "utf-8")
      .schema(bookSchema)
      .load("hdfs://mycluster:8020/bigdata/book/")

    // 根据id排序
    df = df.sort("id")

    // 缓存df
    df.cache()

    // 删除不必要的列
    df = df.drop("link")

    // 除去缺失值(书名空白的行)
    df = df.filter(col("book_name").isNotNull)

    // 获取string类型的column
    val stringTypeCol: Seq[String] = "id" +: df.schema
      .filter(item => item.productElement(1).isInstanceOf[StringType])
      .map(item => item.productElement(0).toString)


    // 获取非String类型的column
    val notStrTypeCol: Seq[String] = df.schema
      .filter(item => !item.productElement(1).isInstanceOf[StringType])
      .map(item => item.productElement(0).toString)


    //println(stringTypeCol)
    //println(notStrTypeCol)

    //  注册自定义函数, 用于处理数据
    val priceField: UserDefinedFunction = spark.udf.register("priceField", priceFieldUdf _)
    val timeFiled: UserDefinedFunction = spark.udf.register("timeFiled", timeFiledUdf _)


    /**
     * ===================   处理String类型的字段  ===================
     * (id, book_name, author, publisher, produce, pub_time, price, binding, category)
     */
    //  除去string类型列的首尾空格, 并填充空值为"其他"
    var stringDf = df.select(stringTypeCol.map(item => trim(col(item)).as(item)): _*)
      .na.fill("其他")

    stringDf = stringDf
      .withColumn("price", priceField(col("price"))) // 处理价格
      .withColumn("pub_time", timeFiled(col("pub_time"))(0)) // 处理时间
      .withColumn("year", timeFiled(col("pub_time"))(1)) // 年
      .withColumn("month", timeFiled(col("pub_time"))(2)) // 月


    /**
     * ===================   int类型的字段  ===================
     * (id, page_num, ISBN, score, commented)
     */
    // 填充缺失值
    val intDf: DataFrame = df.select(notStrTypeCol.map(item => col(item)): _*).na.fill(0)


    // 创建临时表
    stringDf.createOrReplaceTempView("strDf")
    intDf.createOrReplaceTempView("intDf")


    // 在hive创建database&&table
    spark.sql("create database if not exists bigdata")
    spark.sql("drop table if exists bigdata.book")

    // 合并数据, 并写入hive
    spark.sql(
      """
        |create table bigdata.book
        |as
        |select
        |t1.*,
        |t2.page_num, t2.ISBN, t2.score, t2.commented
        |from
        |strDf t1 join intDf t2
        |on t1.id = t2.id
        |""".stripMargin)


    spark.close()
  }

}
