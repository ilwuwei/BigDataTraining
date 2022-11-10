package com.zys.spark.dataframe

import org.apache.spark.sql.functions.{col, expr, when}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

/**
 * @author ilwuwei
 * @Date 2022/10/1 16:15
 * @version 1.0
 */
object SelectCol {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[1]").appName("SparkByExamples.com")
      .getOrCreate()

    spark.sparkContext.setLogLevel("error")

    import spark.implicits._


    val columns = Seq("name", "address")

    val data = Seq(
      ("Robert, Smith", "1 Main st, Newark, NJ, 92537"),
      ("Maria, Garcia", "3456 Walnut st, Newark, NJ, 94732")
    )

    val dfFromData = spark.createDataFrame(data).toDF(columns: _*)

//    dfFromData.where('name === "Robert, Smith").show(false)
    //    dfFromData.where(col("name") === "Robert, Smith").show(false)

//    dfFromData.select(when(col("name").contains("m"), "替换").otherwise("你好")).show()

    dfFromData.select(expr(
      """
        |case
        |when name like "%m%" then "你好"
        |else "12344"
        |end
        |""".stripMargin)).show()
//    val value: Dataset[(String, String, String, String, String, String)] = dfFromData.map(f => {
//      val nameSplit = f.getAs[String](0).split(",")
//      val addSplit = f.getAs[String](1).split(",")
//      (nameSplit(0), nameSplit(1), addSplit(0), addSplit(1), addSplit(2), addSplit(3))
//    })
//
//    value.toDF().show()
//
//
//    value.toDF().map(item => {
//      println(item.getAs[String](1))
//      (item.getAs[String](0), item.getAs[String](1), item.getAs[String](2),
//        item.getAs[String](3), item.getAs[String](4), item.getAs[String](5))
//    }).show()


  }

}
