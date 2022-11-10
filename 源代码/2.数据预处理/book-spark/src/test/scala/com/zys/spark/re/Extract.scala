package com.zys.spark.re

import org.apache.commons.lang3.StringUtils

import scala.util.matching.Regex

/**
 * @author ilwuwei
 * @Date 2022/10/2 17:22
 * @version 1.0
 */
object Extract {
  def main(args: Array[String]): Unit = {
    val pattern: Regex = "((-)?(([1-9]\\d*)|(0))(\\.(\\d){1,2})?)".r
    val float = pattern.findFirstIn("港幣二百元").mkString
    println(StringUtils.isBlank(float))
    println(float)
  }

}
