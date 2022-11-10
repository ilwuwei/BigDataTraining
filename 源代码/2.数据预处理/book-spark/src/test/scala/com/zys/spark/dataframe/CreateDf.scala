package com.zys.spark.dataframe

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
 * @author ilwuwei
 * @Date 2022/10/1 15:49
 * @version 1.0
 */
object CreateDf {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[*]").appName("SparkByExamples.com")
      .getOrCreate()

    spark.sparkContext.setLogLevel("error")

    import spark.implicits._

    val columns = Seq("language", "users_count")
    val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))

    val rdd = spark.sparkContext.parallelize(data)

    // 从rdd使用toDF()创建dataframe
    rdd.toDF().show()
    rdd.toDF("language", "users_count").show()

    // 使用createDataFrame创建dataframe
    spark.createDataset(rdd).toDF(columns: _*).show()

    // 使用StructType&&StructField定义表头
    val schema = StructType(Array(
      StructField("language", StringType, nullable = true),
      StructField("users", StringType, nullable = true)
    ))
    val rowRDD = rdd.map(attributes => Row(attributes._1, attributes._2))
    spark.createDataFrame(rowRDD, schema).show()
    spark.createDataFrame(rowRDD, schema).printSchema()

    // 使用list, seq toDF()创建dataframe
    data.toDF().show()

    spark.createDataset(data).show()

  }

}
