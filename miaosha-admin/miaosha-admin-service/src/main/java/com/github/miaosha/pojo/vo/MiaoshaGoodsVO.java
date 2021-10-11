package com.github.miaosha.pojo.vo;

import com.github.miaosha.pojo.po.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MiaoshaGoodsVO
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@Data
@ApiModel("秒杀商品VO")
public class MiaoshaGoodsVO extends Goods {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("秒杀价格")
    private Double miaoshaPrice;

    @ApiModelProperty("库存数量")
    private Integer stockCount;

    @ApiModelProperty("秒杀开始时间")
    private Date startDate;

    @ApiModelProperty("秒杀结束时间")
    private Date endDate;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品标题")
    private String goodsTitle;

    @ApiModelProperty("商品的图片")
    private String goodsImg;

    @ApiModelProperty("商品的详情介绍")
    private String goodsDetail;

    @ApiModelProperty("商品单价")
    private Double goodsPrice;

    @ApiModelProperty("商品库存，-1表示没有限制")
    private Integer goodsStock;
}
