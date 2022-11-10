package cn.edu.cjxy.bigdata.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bigdata
 * @since 2022-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BookList对象")
public class BookList implements Serializable {

    private static final long serialVersionUID = 1L;


    private String bookName;

    private String author;

    private String publisher;

    @TableId(value = "isbn", type = IdType.AUTO)
    private Long isbn;

    private BigDecimal weight;


}
