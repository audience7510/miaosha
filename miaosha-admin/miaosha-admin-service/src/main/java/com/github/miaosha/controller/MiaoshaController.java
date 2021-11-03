package com.github.miaosha.controller;

import com.alibaba.fastjson.JSON;
import com.github.miaosha.common.GoodsKey;
import com.github.miaosha.common.Result;
import com.github.miaosha.common.ResultGen;
import com.github.miaosha.pojo.po.MiaoshaGoods;
import com.github.miaosha.pojo.vo.SendMessageVO;
import com.github.miaosha.rabbitmq.MQSender;
import com.github.miaosha.redis.RedisUtil;
import com.github.miaosha.service.IMiaoshaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName MiaoshaController
 * @Author audience
 * @Date 2021/10/26
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/miaosha")
@Slf4j
@Api(tags = "秒杀管理")
public class MiaoshaController implements InitializingBean {

    private HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();

    @Autowired
    private IMiaoshaService miaoshaService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    MQSender sender;
    /**
     * redis初始化秒杀商品数量，
     * hashmap内存初始化标记秒杀商品是否还有库存
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<MiaoshaGoods> list = miaoshaService.list();
        for (MiaoshaGoods miaoshaGoods : list) {
            //把秒杀商品的数量初始化到redis中
            redisUtil.set(GoodsKey.miaoshaGoods +miaoshaGoods.getGoodsId(), miaoshaGoods.getStockCount());
            //hashmap内存标记是否还有库存
            localOverMap.put(miaoshaGoods.getGoodsId(),false);
        }

    }

    @GetMapping("doMiaosha/{goodsId}")
    @ApiOperation(value = "商品秒杀",notes = "传入")
    public Result miaosha(@PathVariable long goodsId) {

        //内存标记，秒杀是否结束，减少redis访问
        Boolean b = localOverMap.get(goodsId);
        if (b){
            return ResultGen.success("商品已经秒杀完毕");
        }
        //预减库存
        long decr = redisUtil.decr(GoodsKey.miaoshaGoods + goodsId, 1);
        if (decr<0){
            localOverMap.put(goodsId,true);
            return ResultGen.success("商品已经秒杀完毕");
        }
        //入队
        SendMessageVO sendMessageVO = new SendMessageVO();
        sendMessageVO.setGoodsId(goodsId);
        sender.sendMiaoshaMessage(sendMessageVO);
        return ResultGen.success();
    }

}
