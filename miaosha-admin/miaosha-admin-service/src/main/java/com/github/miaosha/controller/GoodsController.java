package com.github.miaosha.controller;

import com.github.miaosha.common.Result;
import com.github.miaosha.common.ResultGen;
import com.github.miaosha.redis.RedisUtil;
import com.github.miaosha.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GoodsController
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/goods")
@Slf4j
@Api(tags = "商品管理")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有用户列表",notes = "查询所有用户列表，全查，列表返回")
    public Result findAll() {
        redisUtil.set("name","小贝");
        return ResultGen.success();
    }

}
