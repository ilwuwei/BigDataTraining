package com.zys.spark.dataframe

import com.zys.spark.pojo.Book
import com.zys.spark.util.SparkUtils
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{count, countDistinct, monotonically_increasing_id}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Encoders, SparkSession}

/**
 * @author ilwuwei
 * @Date 2022/10/1 12:19
 * @version 1.0
 */
object SelectColumn {

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

    df = df.sort("id")
    //      .withColumn("id", monotonically_increasing_id())

    df.cache()

    df.createOrReplaceTempView("book")

    // List(id, book_name, author, publisher, produce, pub_time,
    // page_num, price, binding, ISBN, category, score, commented, link)
    //    val columns: List[String] = df.columns.toList
    //    println(columns)
    //    df.select(df.col("book_name")).show
    //    df.select("id", "book_name").show(10, truncate = false)

    //    println(df.collect()(0))
    //    df.collect().toList.take(10).foreach(println)
    //    println(df.collect().length)

    //    df.show()
    //    df.printSchema
    //    df.describe("book_name", "page_num", "score").show
    //    println(df.first())
    //    println(df.head(4).toList.head)
    //    println(df.head(4).toList(0)(2))
    //    val rows: Array[Row] = df.take(5)
    //    println(rows.mkString("Array(", ", ", ")"))

    //    df.where("publisher = '河南文艺出版社'").show()

    //    println(df.filter("id < 25").count())

    //    df.select(df.col("id"), df("book_name")).show
    //    df.select(df.col("id") + 100, df("book_name")).show

    //    val column: Column = df.col("id")
    //    val column1: Column = df("book_name")

    val temp1: UserDefinedFunction = spark.udf.register("temp", (item: Int) => item + 12)


    //    df.selectExpr("temp(id)").show()
    //    df.select(temp1(df("id")).as("123")).show()

    //    df.orderBy(df("id").asc).select("id", "book_name").show()

    //    df.orderBy(df("id").desc).select("id", "book_name").show()

    //    println(df.count())

    //    df.show(15000)


    //    df.groupBy("publisher").max("score", "page_num").show
    //    df.groupBy("publisher").max().

    //    df.groupBy("publisher").sum("score", "page_num").show
    //    df.groupBy("publisher").mean().show

    //    println(df.dropDuplicates.count, df.count)
    //    println(df.distinct.count, df.count)

    //    println(df.dropDuplicates(Seq("publisher", "author")).count, df.count)

    //    println(df.groupBy("publisher", "author").agg("id" -> "count").count())


    //    df.select(countDistinct("publisher")).show()
    //    spark.sql("select count(distinct(publisher)) from book ").show

//    val li = spark.udf.register("li", (item: String) => "3435")
//
//    df.withColumn("book_name", li(df.col("book_name"))).show()

  }
}
