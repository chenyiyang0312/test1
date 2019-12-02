package com.fh.controller;

import com.fh.service.ShopProductService;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("productSearch")
public class ProductSearchController {
    @Autowired
    private ShopProductService shopProductService;
    @GetMapping("/{productId}")
    public ResponseServer getProductById(@PathVariable Integer productId) {
        return shopProductService.getProductById(productId);
    }
}
