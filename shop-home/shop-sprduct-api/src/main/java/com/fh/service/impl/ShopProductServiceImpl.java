package com.fh.service.impl;

import com.fh.bean.ProductParamter;
import com.fh.bean.ShopProductBean;
import com.fh.dao.ShopProductDao;
import com.fh.service.ShopProductService;
import com.fh.utils.PageBean;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ShopProductServiceImpl implements ShopProductService {
    @Autowired
    private ShopProductDao shopProductDao;

    @Override
    public void queryProductList(PageBean<ShopProductBean> page, ProductParamter parameter) {
        Long count = shopProductDao.queryShopProductCount(parameter);
        page.setRecordsTotal(count);
        page.setRecordsFiltered(count);

        List<ShopProductBean> shopProductBeans= shopProductDao.queryProductList(page,parameter);

        page.setData(shopProductBeans);
    }

    @Override
    public ResponseServer getProductById(Integer productId) {
        ShopProductBean product = shopProductDao.getProductById(productId);
        return ResponseServer.success(product);
    }


}
