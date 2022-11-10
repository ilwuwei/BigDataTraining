package com.zys.spark.util

import com.zys.spark.config.ApplicationConfig
import org.apache.spark.sql.SparkSession

/**
 * @author ilwuwei
 * @Date 2022/10/1 12:36
 * @version 1.0
 */
object SparkUtils {


  /**
   * 构建sparkSession实例对象, 配置hive
   *
   * @param clazz
   * @return
   */
  def createSparkSession(clazz: Class[_]): SparkSession = {
    val builder: SparkSession.Builder = SparkSession
      .builder()
      .appName(clazz.getSimpleName.stripSuffix("$")) // 设置app名为类名

    /*
      是否为本地模式
     */
    if (ApplicationConfig.APP_LOCAL_MODE) { // 本地模式
      builder
        .master(ApplicationConfig.APP_SPARK_MASTER)
        .config("spark.sql.shuffle.partitions", 4)
    }

    /*
      是否集成hive
     */
    if (ApplicationConfig.APP_IS_HIVE) {
      builder
        .enableHiveSupport()
        .config("hive.metastore.uris", ApplicationConfig.APP_HIVE_METASTORE_URIS)
        .config("spark.sql.warehouse.dir", ApplicationConfig.APP_SPARK_WAREHOUSE_DIR)
    }

    val spark: SparkSession = builder.getOrCreate()

    /*
     spark读写hadoop HA集群
    */
    spark.sparkContext.hadoopConfiguration.set("fs.defaultFS", "hdfs://mycluster")
    spark.sparkContext.hadoopConfiguration.set("dfs.nameservices", "mycluster")

    spark.sparkContext.hadoopConfiguration.set("dfs.ha.namenodes.mycluster", "master,slave1,slave2")
    spark.sparkContext.hadoopConfiguration.set("dfs.namenode.rpc-address.mycluster.master", "master:8020")
    spark.sparkContext.hadoopConfiguration.set("dfs.namenode.rpc-address.mycluster.slave1", "slave1:8020")
    spark.sparkContext.hadoopConfiguration.set("dfs.namenode.rpc-address.mycluster.slave2", "slave2:8020")
    spark.sparkContext.hadoopConfiguration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider")

    // 设置日志级别
    spark.sparkContext.setLogLevel("error")

    spark
  }

}
