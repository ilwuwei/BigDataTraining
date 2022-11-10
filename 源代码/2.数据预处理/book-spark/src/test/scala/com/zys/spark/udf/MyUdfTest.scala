package com.zys.spark.udf

import com.zys.spark.util.MyUdf

/**
 * @author ilwuwei
 * @Date 2022/10/2 19:23
 * @version 1.0
 */
object MyUdfTest {
  def main(args: Array[String]): Unit = {
    println(MyUdf.timeFiledUdf("2022-1-2"))
    println(MyUdf.timeFiledUdf("2022年1月2"))
    println(MyUdf.timeFiledUdf("2022/1-2"))
  }

}
