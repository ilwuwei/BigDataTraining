package cn.edu.cjxy.bigdata.controller;

import cn.edu.cjxy.bigdata.entity.*;
import cn.edu.cjxy.bigdata.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "图")
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private BookNumService bookNumService;

    @ApiOperation("图书数据总数表")
    @GetMapping("/bookNum")
    public List<BookNum> bookNumList(){
        return bookNumService.list();
    }

    @Autowired
    private BookCategoryService bookCategoryService;

    @ApiOperation("图书大类统计表")
    @GetMapping("/bookCategory")
    public List<BookCategory> bookCategoryList(){
        return bookCategoryService.list();
    }

    @Autowired
    private BookScoreService bookScoreService;

    @ApiOperation("图书得分统计表")
    @GetMapping("/bookScore")
    public List<BookScore> bookScoresList(){
        return bookScoreService.list();
    }

    @Autowired
    private BookPriceService bookPriceService;

    @ApiOperation("图书价格统计表")
    @GetMapping("/bookPrice")
    public List<BookPrice> bookPricesList(){
        return bookPriceService.list();
    }

    @Autowired
    private BookPublisherService bookPublisherService;

    @ApiOperation("出版社平均得分及图书数量统计表")
    @GetMapping("/bookPublisher")
    public List<BookPublisher> bookPublishersList(){
        return bookPublisherService.list();
    }

    @Autowired
    private BookTimeService bookTimeService;

    @ApiOperation("图书发售时间顺序统计表")
    @GetMapping("/bookTime")
    public List<BookTime> bookTimes(){
        return bookTimeService.list();
    }

    @Autowired
    private BookAuthorService bookAuthorService;

    @ApiOperation("作者出版的图书数量统计表")
    @GetMapping("/bookAuthor")
    public List<BookAuthor> bookAuthors(){
        return bookAuthorService.list();
    }

    @Autowired
    private BookCommentService bookCommentService;

    @ApiOperation("评论数量多的top15图书的统计表")
    @GetMapping("/bookComment")
    public List<BookComment> bookComments(){
        return bookCommentService.list();
    }

    @Autowired
    private CategoryTimeService categoryTimeService;

    @ApiOperation("图书大类在2000年以来数量变化的统计表")
    @GetMapping("/categoryTime")
    public List<CategoryTime> categoryTimes(){
        return categoryTimeService.list();
    }

    @Autowired
    private CategoryTop3Service categoryTop3Service;

    @ApiOperation("每个图书大类最热图书top3的统计表")
    @GetMapping("/categoryTop3")
    public List<CategoryTop3> categoryTop3(){
        return categoryTop3Service.list();
    }

    @Autowired
    private BookCommentedService bookCommentedService;

    @ApiOperation("图书评论分布统计表")
    @GetMapping("/bookCommented")
    public List<BookCommented> bookCommenteds(){
        return bookCommentedService.list();
    }
}
