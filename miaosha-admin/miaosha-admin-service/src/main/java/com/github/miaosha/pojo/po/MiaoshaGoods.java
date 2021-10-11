package com.github.miaosha.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Goods
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@Data
@TableName("miaosha_goods")
@ApiModel("秒杀商品实体类")
public class MiaoshaGoods implements Serializable {

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品id")
    @TableField(value = "goods_id")
    private Long goodsId;

    @ApiModelProperty("秒杀价格")
    @TableField(value = "miaosha_price")
    private Double miaoshaPrice;

    @ApiModelProperty("库存数量")
    @TableField(value = "stock_count")
    private Integer stockCount;

    @ApiModelProperty("秒杀开始时间")
    @TableField(value = "start_date")
    private Date startDate;

    @ApiModelProperty("秒杀结束时间")
    @TableField(value = "end_date")
    private Date endDate;

}
