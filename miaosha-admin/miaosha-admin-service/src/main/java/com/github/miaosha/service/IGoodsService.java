package com.github.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.miaosha.pojo.po.Goods;
import com.github.miaosha.pojo.po.MiaoshaGoods;

/**
 * @InterfaceName IGoodsService
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
public interface IGoodsService extends IService<Goods> {
    MiaoshaGoods getMiaoshaGoods(Long id);
}
