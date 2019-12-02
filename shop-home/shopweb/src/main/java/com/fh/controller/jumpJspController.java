package com.fh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class jumpJspController {
    @RequestMapping("jumpJsp")
    public String jumpJsp(String url){
        return url;
    }
}
