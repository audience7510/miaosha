package com.github.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.order.mapper.OrderInfoMapper;
import com.github.order.pojo.po.OrderInfo;
import com.github.order.service.IOrderInfoService;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsServiceImpl
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
