package cn.edu.cjxy.bigdata.service.impl;

import cn.edu.cjxy.bigdata.entity.BookComment;
import cn.edu.cjxy.bigdata.mapper.BookCommentMapper;
import cn.edu.cjxy.bigdata.service.BookCommentService;
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
public class BookCommentServiceImpl extends ServiceImpl<BookCommentMapper, BookComment> implements BookCommentService {

}
