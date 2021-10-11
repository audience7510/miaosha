package com.github.miaosha.redis;

import com.alibaba.fastjson.JSON;
import com.github.miaosha.common.GoodsKey;
import com.github.miaosha.pojo.po.Goods;
import com.github.miaosha.pojo.po.MiaoshaGoods;
import com.github.miaosha.pojo.vo.MiaoshaGoodsVO;
import com.github.miaosha.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName RedisInit
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@Component
@Slf4j
public class RedisInit implements CommandLineRunner {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(String... args) throws Exception {
        List<Goods> list = goodsService.list();
        //列表缓存
        redisUtil.set(GoodsKey.goodsList, JSON.toJSONString(list));
        for (Goods goods : list) {
            Long id = goods.getId();
            //单个商品缓存
            redisUtil.set(GoodsKey.goods +id, JSON.toJSONString(goods));
            MiaoshaGoodsVO miaoshaGoodsVO = new MiaoshaGoodsVO();
            BeanUtils.copyProperties(goods,miaoshaGoodsVO);
            MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGoods(goods.getId());
            if (miaoshaGoods!=null){
                //秒杀商品缓存
                redisUtil.set(GoodsKey.miaoshaGoods +id, JSON.toJSONString(miaoshaGoods));
                BeanUtils.copyProperties(miaoshaGoods,miaoshaGoodsVO);
            }
            redisUtil.set(GoodsKey.miaoshaGoodsDetail +id, JSON.toJSONString(miaoshaGoodsVO));
        }
    }
}
