package com.github.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.miaosha.mapper.GoodsMapper;
import com.github.miaosha.pojo.po.Goods;
import com.github.miaosha.service.IGoodsService;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsServiceImpl
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
}
