
往hive写入数据
1. 
~~~shell
    spark.sql(
    """
      |create table bigdata.book if not exists (
      |book_name String,
      |author String,
      |publisher String,
      |produce String,
      |pub_time String,
      |page_num String,
      |price String,
      |binding String,
      |ISBN String,
      |category String,
      |score String,
      |commented String,
      |ID String
      |)
      |""".stripMargin)

    spark.sql("insert overwrite table bigdata.book  select * from book")
~~~

~~~shell


~~~


指定元数据

~~~bash
    //    val structType: StructType = StructType(List(
    //      StructField("ID", StringType),
    //      StructField("book_name", StringType),
    //      StructField("author", StringType),
    //      StructField("publisher", StringType),
    //      StructField("produce", StringType),
    //      StructField("pub_time", StringType),
    //      StructField("page_num", StringType),
    //      StructField("price", StringType),
    //      StructField("binding", StringType),
    //      StructField("ISBN", StringType),
    //      StructField("category", StringType),
    //      StructField("score", StringType),
    //      StructField("commented", StringType),
    //      StructField("href", StringType),
    //    ))
~~~
