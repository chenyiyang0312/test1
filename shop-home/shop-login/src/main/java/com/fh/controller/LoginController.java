package com.fh.controller;


import com.fh.bean.UserBean;
import com.fh.service.UserService;
import com.fh.utils.ResponseServer;
import com.fh.utils.ServerEnum;
import com.fh.utils.jwt.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/logins")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:8080",exposedHeaders="NOLONGIN")
public class LoginController {

 @Autowired
    private RedisTemplate redisTemplate;

 @Autowired
 private UserService userService;

    @GetMapping("/{phone}")
    public ResponseServer sendCode(@PathVariable("phone") String phone) throws IOException {
        //先判断一下
        if (StringUtils.isBlank(phone)) {
            return ResponseServer.error(ServerEnum.PHONE_ISNULL);
        }


        //先发送验证码
        /*JSONObject jsonObject = SendMessage.sendMessage(phone);
        String code = jsonObject.getString("code");
        if (code.equals("200")) {
            String checkCode = jsonObject.getString("obj");
            redisTemplate.opsForValue().set("code_" + phone, checkCode, 300, TimeUnit.SECONDS);
        }*/


       redisTemplate.opsForValue().set("code_" + phone, "222222", 300, TimeUnit.SECONDS);


        //如果没注册先注册
        return ResponseServer.success();
    }

    /**
     * 用户登陆
     */

   @PostMapping
    public ResponseServer login(String phone,String checkCode){


       //判断手机号或者验证码不能为空
       if(StringUtils.isBlank(phone) || StringUtils.isBlank(checkCode)){
           return ResponseServer.error(ServerEnum.LOGIN_PHONEORCODE_ISNULL);
       }
       //先验证验证码是否正确
       String code= (String) redisTemplate.opsForValue().get("code_" + phone);
       if(!checkCode.equals(code)){
           return ResponseServer.error(ServerEnum.LOGIN_CODE_ERROR);
       }
       //删除该手机的验证码
       redisTemplate.delete("code_" + phone);

       //判断手机号是否存在，不存在就注册
       UserBean customer = userService.isregister(phone);
       redisTemplate.opsForValue().set("user_"+phone,customer);
       redisTemplate.opsForValue().set("cartid_"+phone,customer.getCartId());
       //生成Token
       Map<String,Object> map=new HashMap<String,Object>();
       map.put("phone",customer.getPhone());
       String token=JwtUtils.createToken(map);

       return ResponseServer.success(token);

   }

}
