package cn.edu.cjxy.bigdata.service.impl;

import cn.edu.cjxy.bigdata.entity.BookNum;
import cn.edu.cjxy.bigdata.mapper.BookNumMapper;
import cn.edu.cjxy.bigdata.service.BookNumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigdata
 * @since 2022-10-23
 */
@Service
public class BookNumServiceImpl extends ServiceImpl<BookNumMapper, BookNum> implements BookNumService {

}
