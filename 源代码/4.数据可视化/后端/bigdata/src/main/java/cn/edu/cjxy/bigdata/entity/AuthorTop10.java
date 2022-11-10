package cn.edu.cjxy.bigdata.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="AuthorTop10对象", description="")
public class AuthorTop10 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "author", type = IdType.AUTO)
    private String author;

    private Long commenteds;


}
