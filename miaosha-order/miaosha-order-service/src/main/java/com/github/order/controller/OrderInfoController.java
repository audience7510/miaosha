package com.github.order.controller;

import com.github.order.common.Result;
import com.github.order.common.ResultGen;
import com.github.order.pojo.po.OrderInfo;
import com.github.order.service.IOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Author audience
 * @Date 2021/11/3
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/order")
@Slf4j
@Api(tags = "订单管理")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @GetMapping("detail/{id}")
    @ApiOperation(value = "订单详情", notes = "根据订单id查询订单详情")
    public Result detail(@PathVariable Integer id) {
        OrderInfo byId = orderInfoService.getById(id);
        return ResultGen.success(byId);
    }
}