package com.github.miaosha.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName MiaoshaMessage
 * @Author audience
 * @Date 2021/11/2
 * @Version 1.0
 * @Description
 */
@Data
@TableName("miaosha_message")
@ApiModel("秒杀消息实体类")
public class MiaoshaMessage {

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id ;

    @ApiModelProperty("商品名称")
    @TableField(value = "messageid")
    private Long messageId ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private Long userId ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private String content ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private Date createTime;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private Integer status ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private Date overTime ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private Integer messageType ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private Integer sendType ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private String goodName ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private BigDecimal price ;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private String messageHead ;

}
