package com.github.miaosha.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Goods
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@Data
@TableName("goods")
@ApiModel("商品实体类")
public class Goods implements Serializable {

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private String id;

    @ApiModelProperty("商品名称")
    @TableField(value = "goods_name")
    private String goodsName;

    @ApiModelProperty("商品标题")
    @TableField(value = "goods_title")
    private String goodsTitle;

    @ApiModelProperty("商品的图片")
    @TableField(value = "goods_img")
    private String goodsImg;

    @ApiModelProperty("商品的详情介绍")
    @TableField(value = "goods_detail")
    private String goodsDetail;

    @ApiModelProperty("商品单价")
    @TableField(value = "goods_price")
    private String goodsPrice;

    @ApiModelProperty("商品库存，-1表示没有限制")
    @TableField(value = "goods_stock")
    private String goodsStock;
}
