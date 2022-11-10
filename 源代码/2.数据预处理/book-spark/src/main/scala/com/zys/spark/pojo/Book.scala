package com.zys.spark.pojo

/**
 * @author ilwuwei
 * @Date 2022/9/30 21:24
 * @version 1.0
 */
case class Book(
                 id: Long,
                 book_name: String,
                 author: String,
                 publisher: String,
                 produce: String,
                 pub_time: String,
                 page_num: Int,
                 price: String,
                 binding: String,
                 ISBN: Long,
                 category: String,
                 score: Float,
                 commented: Int,
                 link: String
               )
