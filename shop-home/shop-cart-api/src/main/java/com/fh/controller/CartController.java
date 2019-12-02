package com.fh.controller;

import com.fh.bean.CartBean;
import com.fh.login.LoginAnnotation;
import com.fh.service.CartService;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/carts")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders="NOLONGIN")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private RedisTemplate redisTemplate;

        @PostMapping
        @LoginAnnotation
    public ResponseServer addCarts(Integer productId, HttpServletRequest request){
           String  userPhone = (String)request.getAttribute("phone");
           return  cartService.addCarts(productId,userPhone);
        }


        @GetMapping("toCart")
        @LoginAnnotation
        public ResponseServer toCart(){
                return ResponseServer.success();
            }


        @GetMapping("queryCarts")
        @LoginAnnotation
        public ResponseServer cartShow(HttpServletRequest request){
            String phone= (String) request.getAttribute("phone");
            Map<String, Object> carts = cartService.queryCarts(phone);
            return ResponseServer.success(carts);
        }

        @PostMapping("/updateNum")
        @LoginAnnotation
        public ResponseServer changeNum(Integer productId,HttpServletRequest request){
            String phone = (String) request.getAttribute("phone");
            cartService.changeNum(productId,phone);
            return ResponseServer.success();
        }

    @LoginAnnotation
    @PostMapping("/deleteShop")
    public ResponseServer deleteShop(Integer productId,HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        cartService.deleteShop(productId,phone);
        return ResponseServer.success();
    }



    //修改购物车的选中状态
    @LoginAnnotation
    @PostMapping("/changCheck")
    public ResponseServer changChecked(Integer productId,HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        cartService.updateCheckStatus(productId,phone);
        return ResponseServer.success();
    }

    @LoginAnnotation
    @PostMapping("/cartCheck")
    public ResponseServer cartCheck(Integer productId,HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean cart= (CartBean) redisTemplate.opsForHash().get(cartId,productId);
        if(cart.getIsChecked()){
            cart.setIsChecked(false);
        }else {
            cart.setIsChecked(true);
        }
        redisTemplate.opsForHash().put(cartId,productId,cart);
        return ResponseServer.success();
    }





}
