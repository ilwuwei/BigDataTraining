import org.apache.spark.sql.SparkSession

/**
 * @author sjj
 * @date 2022/10/3 13:11
 * @version 1.0
 */
object T {
  def main(args: Array[String]): Unit = {
    val builder = SparkSession.builder().master("local[*]").enableHiveSupport()
  }

}
