package com.github.miaosha.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.miaosha.common.GoodsKey;
import com.github.miaosha.common.Result;
import com.github.miaosha.common.ResultGen;
import com.github.miaosha.pojo.po.Goods;
import com.github.miaosha.pojo.po.MiaoshaGoods;
import com.github.miaosha.pojo.vo.MiaoshaGoodsVO;
import com.github.miaosha.redis.RedisUtil;
import com.github.miaosha.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("list")
    @ApiOperation(value = "查询所有商品列表",notes = "查询所有商品列表，列表返回")
    public Result list() {
        String s = (String) redisUtil.get(GoodsKey.getGoodsList);
        if (!StringUtils.isEmpty(s)){
            List<Goods> list = JSON.parseObject(s, List.class);
            return ResultGen.success(list);
        }else {
            List<Goods> list = goodsService.list();
            String jsonString = JSON.toJSONString(list);
            redisUtil.set(GoodsKey.getGoodsList,jsonString);
            return ResultGen.success(list);
        }
    }

    @GetMapping("detail/{id}")
    @ApiOperation(value = "查询所有商品列表",notes = "查询所有商品列表，列表返回")
    public Result list(@PathVariable String id) {
        //redis查询秒杀商品详情
        String s = (String)redisUtil.get(GoodsKey.getMiaoshaGoodsDetail+id);
        if (!StringUtils.isEmpty(s)){
            //查到返回
            MiaoshaGoodsVO miaoshaGoodsVO = JSON.parseObject(s, MiaoshaGoodsVO.class);
            return ResultGen.success(miaoshaGoodsVO);
        }else {
            //未查到查数据库
            MiaoshaGoodsVO miaoshaGoodsVO = new MiaoshaGoodsVO();
            //商品详情
            String stringGoods = (String)redisUtil.get(GoodsKey.getGoods+id);
            if (!StringUtils.isEmpty(stringGoods)){
                Goods goods = JSON.parseObject(stringGoods, Goods.class);
                BeanUtils.copyProperties(goods,miaoshaGoodsVO);
            }else {
                Goods goods = goodsService.getById(id);
                String jsonString = JSON.toJSONString(goods);
                redisUtil.set(GoodsKey.getGoods+id,jsonString);
                BeanUtils.copyProperties(goods,miaoshaGoodsVO);
            }
            String stringMiaoshaGoods = (String)redisUtil.get(GoodsKey.getMiaoshaGoods+id);
            if (!StringUtils.isEmpty(stringGoods)){
                MiaoshaGoods miaoshaGoods = JSON.parseObject(stringMiaoshaGoods, MiaoshaGoods.class);
                BeanUtils.copyProperties(miaoshaGoods,miaoshaGoodsVO);

            }else {
                MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGoods(id);
                BeanUtils.copyProperties(miaoshaGoods,miaoshaGoodsVO);
                redisUtil.set(GoodsKey.getMiaoshaGoods+id,JSON.toJSONString(miaoshaGoods));
            }
            redisUtil.set(GoodsKey.getMiaoshaGoodsDetail+id,JSON.toJSONString(miaoshaGoodsVO));
            return ResultGen.success(miaoshaGoodsVO);
        }
    }

}
