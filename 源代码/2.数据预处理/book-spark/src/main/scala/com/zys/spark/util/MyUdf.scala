package com.zys.spark.util


import org.apache.commons.lang3.StringUtils

import scala.util.matching.Regex

/**
 * @author ilwuwei
 * @Date 2022/9/30 11:53
 * @version 1.0
 */
object MyUdf {


  /**
   * 提取价格
   *
   * @param price 价格
   * @return
   */
  def priceFieldUdf(price: String): Float = {
    val pattern: Regex = "((-)?(([1-9]\\d*)|(0))(\\.(\\d){1,2})?)".r
    val result: String = pattern.findFirstIn(price).mkString
    if (StringUtils.isNotBlank(result)) result.toFloat else 0
  }


  /**
   * 处理时间
   *
   * @param time 时间
   * @return
   */
  def timeFiledUdf(time: String): List[String] = {
    val pattern = "\\d{4}[-年/]\\d{1,2}".r
    val resStr: Option[String] = pattern.findFirstIn(time)
    if (resStr.isEmpty) {
      return List("其他", "其他", "其他")
    }
    val result = resStr.get.split("[-年/]")
    if (result.size == 2) {
      val year = result(0)
      val month = if (result(1).indexOf("0")==0) result(1).charAt(1).toString else result(1)
      List(s"$year-$month", year, month)
    } else {
      List(result(0), result(0), "其他")
    }
  }




}
