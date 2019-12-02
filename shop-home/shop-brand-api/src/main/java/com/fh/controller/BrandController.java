package com.fh.controller;

import com.fh.service.BrandService;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brands")
@CrossOrigin(maxAge = 4500,origins = "http://localhost:8080")
public class BrandController {

    @Autowired
    private BrandService brandService;

        @GetMapping("/{pid}")
        public ResponseServer queryBrandsByCateId(@PathVariable("pid") Integer pid){
            return brandService.queryBrandsByCateId(pid);
        }



}
