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
@ApiModel(value="BookNum对象", description="")
public class BookNum implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "图书总数", type = IdType.AUTO)
    private Long 图书总数;


}
