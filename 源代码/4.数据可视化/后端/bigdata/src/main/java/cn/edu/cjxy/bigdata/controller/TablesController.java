package cn.edu.cjxy.bigdata.controller;

import cn.edu.cjxy.bigdata.common.QueryPageParam;
import cn.edu.cjxy.bigdata.entity.BookList;
import cn.edu.cjxy.bigdata.service.BookListService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "表")
@RestController
@RequestMapping("/tables")
public class TablesController {

    @Autowired
    private BookListService bookListService;

    @ApiOperation("最终推荐书单统计表")
    @GetMapping("/bookList")
    public List<BookList> bookListALL(){
        return bookListService.list();
    }

    @ApiOperation("最终推荐书单统计表分页")
    @PostMapping("/bookListPage")
    public List<BookList> bookListPage(@RequestBody QueryPageParam query){
        HashMap book = query.getBook();
        String bookName = (String)book.get("bookName");
        System.out.println((String)book.get("bookName"));

        Page<BookList> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<BookList> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(BookList::getBookName, bookName);

        IPage result = bookListService.page(page, lambdaQueryWrapper);

        System.out.println(result.getTotal());

        return result.getRecords();
    }

}
