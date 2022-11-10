package cn.cz.Analysis

import cn.cz.util.MysqlUtils.{jdbcSave, readMysql}
import cn.cz.util.{MysqlUtils, SparkUtils}
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{Bucketizer, QuantileDiscretizer}

/**
 * @author sjj
 * @date 2022/10/3 11:09
 * @version 1.0
 */
object BDAnalysis {
  // 获取SparkSession对象
  private val spark: SparkSession  = SparkUtils.createSparkSession(this.getClass)

  /**
   * 统计图书总量
   */
  def bookNum(): Unit = {
    println("================1.统计图书总量================")
    val bookNum = spark.sql("select count(*) as `num` from bigdata.book")
    bookNum.show()
    jdbcSave(bookNum, "book_num")
  }

  /**
   * 图书大类统计
   * */
  def bookCategory(): Unit = {
    println("================2.图书大类统计================")
    val bookCategory = spark.sql(
      """
        |SELECT
        |t.`name` as `category`,
        |count(*) as `num`
        |FROM
        |bigdata.tag t
        |JOIN bigdata.category c ON t.`code`=c.tag_code
        |JOIN bigdata.book b ON c.`name`=b.category
        |GROUP BY t.`name`
        |""".stripMargin
    )
    bookCategory.show()
    jdbcSave(bookCategory, "book_category")
  }

  /**
   * 得分分布统计
   * */
  def bookScore(): Unit = {
    println("================3.得分分布统计================")
    val df1 = spark.sql("select score from bigdata.book where score != 0 order by score")
    val splits = Array(Double.NegativeInfinity, 2.0, 4.0, 6.0, 8.0, 10.0, Double.PositiveInfinity)
    new Bucketizer()
      .setInputCol("score")
      .setOutputCol("scorepartithon")
      .setSplits(splits)
      .transform(df1)
      .createTempView("scorebuck")
    val bookScore = spark.sql(
      """
        |SELECT
        |scorepartithon ,
        |concat("[", min(score), "-", max(score), "]") as `partitionsplit`,
        |COUNT(*) as `num`
        |FROM
        |scorebuck
        |GROUP BY scorepartithon
        |ORDER BY scorepartithon
        |""".stripMargin
    )
    bookScore.show()
    jdbcSave(bookScore, "book_score")
  }

  /**
   *图书价格分布统计
   * */
  def bookPrice(): Unit = {
    println("================4.图书价格分布统计================")
    val df2 = spark.sql("select price from bigdata.book where price > 1")
    val discretizer1 = new QuantileDiscretizer()
      .setInputCol("price")
      .setOutputCol("partition")
      .setNumBuckets(5).fit(df2).transform(df2)
    discretizer1.createTempView("pricedis1")
    val bookPrice = spark.sql(
      """
        |select
        |partition,
        |concat("[", min(price), "-", max(price), "]") as `partitionsplit`,
        |count(1) as `num`
        |from
        |pricedis1
        |group by partition
        |order by partition
        |""".stripMargin)
    bookPrice.show()
    jdbcSave(bookPrice, "book_price")
  }

  /**
   *图书出版社图书数量top10与得分统计
   * */
  def bookPublisher(): Unit = {
    println("================5.图书出版社图书数量top10与得分统计================")
    val bookPublisher = spark.sql(
      """
        |select
        |publisher,
        |ROUND(AVG(score),1) as `avgscore`,
        |count(1) as `num`
        |from
        |bigdata.book
        |group by publisher
        |order by count(1) desc limit 10
        |""".stripMargin)
    bookPublisher.show()
    jdbcSave(bookPublisher, "book_publisher")
  }

  /**
   *图书近两年数量统计
   * */
  def bookTime(): Unit = {
    println("================6.图书近两年数量统计================")
    val bookTime = spark.sql(
      """
        |select
        |year,
        |count(1) as `num`
        |from
        |bigdata.book
        |where pub_time != '其他' and year in (2022,2021)
        |group by year
        |order by year desc limit 10
        |""".stripMargin)
    bookTime.show()
    jdbcSave(bookTime, "book_time")
  }

  /**
   *统计作者出版的图书数量
   * */
  def bookAuthor(): Unit = {
    println("================7.统计作者出版的图书数量================")
    val bookAuthor = spark.sql(
      """
        |select
        |author,
        |count(1) as `num`
        |from
        |bigdata.book
        |where author != '其他'
        |group by author
        |order by num desc limit 10
        |""".stripMargin)
    bookAuthor.show()
    jdbcSave(bookAuthor, "book_author")
  }

  /**
   *最热图书的top15
   * */
  def bookComment(): Unit = {
    println("================8.最热图书的top15================")
    val bookComment = spark.sql(
      """
        |select
        |book_name,
        |commented
        |from
        |bigdata.book
        |ORDER BY commented desc limit 15
        |""".stripMargin)
    bookComment.show()
    jdbcSave(bookComment, "book_comment")
  }

  /**
   *图书大类在2000年以来数量变化
   * */
  def categoryTime(): Unit = {
    println("================9.图书大类在2000年以来数量变化================")
    val categoryTime = spark.sql(
      """
        |select
        |t.`name`,
        |`year`,
        |COUNT(1) as `num`
        |from
        |bigdata.book b
        |JOIN bigdata.category c ON b.category=c.`name`
        |JOIN bigdata.tag t on c.tag_code = t.`code`
        |WHERE pub_time != '其他' and year BETWEEN 2000 AND 2022
        |GROUP BY t.`name`,`year`
        |ORDER BY t.`name` DESC , `year` desc
        |""".stripMargin)
    categoryTime.show()
    jdbcSave(categoryTime, "category_time")
  }

  /**
   *每个图书大类最热图书top3
   * */
  def categoryTop3(): Unit = {
    println("================10.每个图书大类最热图书top3================")
    val tempTable = spark.sql(
      """
        |SELECT
        |t.`name` as `categoty_name`,
        |b.book_name,
        |b.commented
        |FROM
        |bigdata.book b
        |JOIN bigdata.category c ON b.category=c.`name`
        |JOIN bigdata.tag t on c.tag_code = t.`code`
        |GROUP BY book_name,t.`name`,commented
        |HAVING COUNT(t.`name`) < 3
        |ORDER BY t.`name`,commented DESC
        |""".stripMargin)
    tempTable.createTempView("book_temp")
    val categoryTop3 = spark.sql(
      """
        |select
        |a.categoty_name,
        |a.book_name,
        |a.commented
        |from book_temp as a
        |left join book_temp b on a.categoty_name = b.categoty_name and a.commented < b.commented
        |group by a.categoty_name,a.book_name,a.commented
        |having count(b.categoty_name) < 3
        |order by a.categoty_name asc,a.commented desc
        |""".stripMargin)
    categoryTop3.show()
    jdbcSave(categoryTop3, "category_top3")
  }

  /**
   *最热门的作者排行top10
   * */
  def authorTop10(): Unit = {
    println("================11.最热门的作者排行top10================")
    val authorTop10 = spark.sql(
      """
        |SELECT
        |author,
        |sum(commented) as `commenteds`
        |FROM
        |bigdata.book
        |WHERE author != '其他'
        |GROUP BY author
        |ORDER BY sum(commented) DESC limit 10
        |""".stripMargin)
    authorTop10.show()
    jdbcSave(authorTop10, "author_top10")
  }

  /**
   *作者书的包装
   * */
  def authorBlinding(): Unit = {
    println("================12.作者书的包装================")
    readMysql("book_author", spark).createTempView("book_author")
    val authorBlinding = spark.sql(
      """
        |SELECT
        |	author,
        |	binding,
        |	COUNT( 1 ) as `num`
        |FROM
        |	bigdata.book
        |WHERE
        |	author IN (SELECT author FROM book_author) and binding in ('平装','精装','其他')
        |GROUP BY
        |	author,
        |	binding
        |ORDER BY
        |	author DESC
        |""".stripMargin)
    authorBlinding.show()
    jdbcSave(authorBlinding, "author_blinding")
  }

  /**
   *图书评论分布统计
   * */
  def bookCommented(): Unit = {
    println("================13.图书评论分布统计================")
    val df3 = spark.sql("select commented from bigdata.book where price > 1")
    val discretizer2 = new QuantileDiscretizer()
      .setInputCol("commented")
      .setOutputCol("partition")
      .setNumBuckets(5).fit(df3).transform(df3)
    discretizer2.createTempView("pricedis2")
    val bookCommented = spark.sql(
      """
        |select
        |partition,
        |concat("[", min(commented), "-", max(commented), "]") as `partitionsplit`,
        |count(1) as `num`
        |from
        |pricedis2
        |group by partition
        |order by partition
        |""".stripMargin)
    bookCommented.show()
    jdbcSave(bookCommented, "book_commented")
  }

  /**
   *最终推荐书单
   * */
  def bookList(): Unit = {
    println("================14.最终推荐书单================")
    val finaltemp = spark.sql(
      """
        |select distinct
        |book_name,
        |author,
        |publisher,
        |isbn,
        |CASE
        |	WHEN score>=2.6 AND score<=3.5 THEN 1
        |	WHEN score>=4.0 AND score<=5.9 THEN 2
        |	WHEN score>=6.0 AND score<=7.9 THEN 3
        |	WHEN score>=8.0 AND score<=9.9 THEN 4
        |	ELSE 5
        |END AS `score_weight`,
        |	CASE
        |	WHEN price>=1.04 AND price<=23.99 THEN 1
        |	WHEN price>=24 AND price<35 THEN 2
        |	WHEN price>=35 AND price<49 THEN 3
        |	WHEN price>=49 AND price<79 THEN 4
        |	ELSE 5
        |END AS `price_weight`,
        |	CASE
        |		WHEN commented>=0 AND commented<=53 THEN 1
        |		WHEN commented>=54 AND commented<=212 THEN 2
        |		WHEN commented>=213 AND commented<=638 THEN 3
        |		WHEN commented>=639 AND commented<=2414 THEN 4
        |		ELSE 5
        |	END AS `commented_weight`
        |from
        |bigdata.book
        |where score != 0 and price != 0 and commented != 0
        |""".stripMargin)
    //        finaltemp.show()
    finaltemp.createTempView("book_temp2")
    val bookList = spark.sql(
      """
        |select
        |book_name,
        |author,
        |publisher,
        |isbn,
        |(score_weight * 0.3 + price_weight * 0.3 + commented_weight * 0.4) as `weight`
        |from
        |book_temp2
        |order by `weight` desc limit 1000
        |""".stripMargin)
    bookList.show()
    jdbcSave(bookList, "book_list")
  }

  def main(args: Array[String]): Unit = {
    bookNum()
    bookCategory()
    bookScore()
    bookPrice()
    bookPublisher()
    bookTime()
    bookAuthor()
    bookComment()
    categoryTime()
    categoryTop3()
    authorTop10()
    authorBlinding()
    bookCommented()
    bookList()
  }
}
