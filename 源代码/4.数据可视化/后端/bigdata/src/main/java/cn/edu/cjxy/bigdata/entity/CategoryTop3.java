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
@ApiModel(value="CategoryTop3对象", description="")
public class CategoryTop3 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "categotyName", type = IdType.AUTO)
    private String categotyName;

    private String bookName;

    private Integer commented;


}
