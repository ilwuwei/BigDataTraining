package cn.edu.cjxy.bigdata.service.impl;

import cn.edu.cjxy.bigdata.entity.BookList;
import cn.edu.cjxy.bigdata.mapper.BookListMapper;
import cn.edu.cjxy.bigdata.service.BookListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2022-10-23
 */
@Service
public class BookListServiceImpl extends ServiceImpl<BookListMapper, BookList> implements BookListService {
}
