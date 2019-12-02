package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.bean.ProductParamter;
import com.fh.bean.ShopProductBean;
import com.fh.utils.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShopProductDao extends BaseMapper<ShopProductBean> {

    Long queryShopProductCount(@Param("parameter") ProductParamter parameter);

    List<ShopProductBean> queryProductList(@Param("page") PageBean<ShopProductBean> page,@Param("parameter") ProductParamter parameter);

    ShopProductBean getProductById(Integer productId);
}
