package com.github.miaosha.controller;

import com.alibaba.fastjson.JSON;
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
        String s = (String) redisUtil.get(GoodsKey.goodsList);
        if (!StringUtils.isEmpty(s)){
            List<Goods> list = JSON.parseObject(s, List.class);
            return ResultGen.success(list);
        }else {
            List<Goods> list = goodsService.list();
            String jsonString = JSON.toJSONString(list);
            redisUtil.set(GoodsKey.goodsList,jsonString);
            return ResultGen.success(list);
        }
    }

    @GetMapping("detail/{id}")
    @ApiOperation(value = "查询商品详情",notes = "根据商品id查询秒杀商品详情")
    public Result list(@PathVariable Long id) {
        //redis查询秒杀商品详情
        String s = (String)redisUtil.get(GoodsKey.miaoshaGoodsDetail +id);
        if (!StringUtils.isEmpty(s)){
            //查到返回
            MiaoshaGoodsVO miaoshaGoodsVO = JSON.parseObject(s, MiaoshaGoodsVO.class);
            return ResultGen.success(miaoshaGoodsVO);
        }else {
            //未查到查数据库
            MiaoshaGoodsVO miaoshaGoodsVO = new MiaoshaGoodsVO();
            //商品详情
            String stringGoods = (String)redisUtil.get(GoodsKey.goods +id);
            if (!StringUtils.isEmpty(stringGoods)){
                Goods goods = JSON.parseObject(stringGoods, Goods.class);
                BeanUtils.copyProperties(goods,miaoshaGoodsVO);
            }else {
                Goods goods = goodsService.getById(id);
                String jsonString = JSON.toJSONString(goods);
                redisUtil.set(GoodsKey.goods +id,jsonString);
                BeanUtils.copyProperties(goods,miaoshaGoodsVO);
            }
            String stringMiaoshaGoods = (String)redisUtil.get(GoodsKey.miaoshaGoods +id);
            if (!StringUtils.isEmpty(stringGoods)){
                MiaoshaGoods miaoshaGoods = JSON.parseObject(stringMiaoshaGoods, MiaoshaGoods.class);
                BeanUtils.copyProperties(miaoshaGoods,miaoshaGoodsVO);

            }else {
                MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGoods(id);
                //商品可能没有秒杀商品的数据
                if (miaoshaGoods!=null){
                    BeanUtils.copyProperties(miaoshaGoods,miaoshaGoodsVO);
                    redisUtil.set(GoodsKey.miaoshaGoods +id,JSON.toJSONString(miaoshaGoods));
                }
            }
            redisUtil.set(GoodsKey.miaoshaGoodsDetail +id,JSON.toJSONString(miaoshaGoodsVO));
            return ResultGen.success(miaoshaGoodsVO);
        }
    }

}
