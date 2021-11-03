package com.github.order.pojo.vo;

import com.github.order.pojo.po.MiaoshaUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SendMessageVO
 * @Author audience
 * @Date 2021/11/3
 * @Version 1.0
 * @Description
 */
@Data
@ApiModel("秒杀商品VO")
public class SendMessageVO {

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("秒杀用户")
    private MiaoshaUser miaoshaUser;
}
