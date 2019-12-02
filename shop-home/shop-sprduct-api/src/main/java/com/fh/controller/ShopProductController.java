package com.fh.controller;

import com.fh.bean.ProductParamter;
import com.fh.bean.ShopProductBean;
import com.fh.service.ShopProductService;
import com.fh.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(maxAge = 3800,origins = "http://localhost:8080")
public class ShopProductController {
    @Autowired
    private ShopProductService shopProductService;


    @PostMapping
    public PageBean<ShopProductBean> queryProductList(PageBean<ShopProductBean> page, ProductParamter parameter){
        shopProductService.queryProductList(page,parameter);
        return page;
    }




}
