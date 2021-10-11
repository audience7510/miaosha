package com.github.miaosha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.miaosha.pojo.po.Goods;
import com.github.miaosha.pojo.po.MiaoshaGoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName GoodsMapper
 * @Author audience
 * @Date 2021/10/11
 * @Version 1.0
 * @Description
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    MiaoshaGoods getMiaoshaGoods(Long id);
}
