package com.github.order.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("order_info")
@ApiModel("订单实体类")
public class OrderInfo {

	@ApiModelProperty("主键id")
	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty("用户ID")
	@TableField(value = "user_id")
	private Long userId;

	@ApiModelProperty("商品ID")
	@TableField(value = "goods_id")
	private Long goodsId;

	@ApiModelProperty("收获地址ID")
	@TableField(value = "delivery_addr_id")
	private Long  deliveryAddrId;

	@ApiModelProperty("商品名称")
	@TableField(value = "goods_name")
	private String goodsName;

	@ApiModelProperty("商品数量")
	@TableField(value = "goods_count")
	private Integer goodsCount;

	@ApiModelProperty("商品单价")
	@TableField(value = "goods_price")
	private Double goodsPrice;

	@ApiModelProperty("下单渠道")
	@TableField(value = "order_channel")
	private Integer orderChannel;

	@ApiModelProperty("订单状态")
	@TableField(value = "status")
	private Integer status;

	@ApiModelProperty("订单的创建时间")
	@TableField(value = "create_date")
	private Date createDate;

	@ApiModelProperty("支付时间")
	@TableField(value = "pay_date")
	private Date payDate;
}
