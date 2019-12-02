package com.fh;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public  class ShopSprductApiApplicationTests {

   @Test
 public  void contextLoads() {

       //jwt如何生成字符串
       //声明头部信息
       Map<String,Object> headerMap=new HashMap<String,Object>();
       headerMap.put("alg","HS256");
       headerMap.put("typ","JWT");
       //设置负载：不要方至涉密的东西比如：银行账号密码，余额，身份证号
       Map<String,Object> payload = new HashMap<String,Object>();
       payload.put("userPhone","13523816047");
       payload.put("userId","124234253456547231");
       Long iat = System.currentTimeMillis();
       //设置jwt 的失效时间
       payload.put("exp",iat+60000l);
       payload.put("iat",iat);
       //签名至就是我们的密钥
       String token = Jwts.builder()
               .setHeader(headerMap)
               .setPayload(JSON.toJSONString(payload))
               .signWith(SignatureAlgorithm.HS256,getSecretKey("chenyiyang"))
               .compact();
             System.out.println(token);

    }




    @Test
    public void  resolverTest(){

       String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyUGhvbmUiOiIxMzUyMzgxNjA0NyIsImV4cCI6MTU3NDgyNDk2NzIzOCwidXNlcklkIjoiMTI0MjM0MjUzNDU2NTQ3MjMxIiwiaWF0IjoxNTc0ODI0OTA3MjM4fQ.-pe46FcspSO1tE_A94AZ8CmMozODmfgN4Z3fstrE_WI";
       try {
           Claims claims = Jwts.parser()
                   .setSigningKey(getSecretKey("chenyiyang"))
                   .parseClaimsJws(token)
                   .getBody();
       }catch (ExpiredJwtException exp){
           System.out.println("token超时，token失效了");
       }catch (SignatureException sing){
           System.out.println("token解析失败");
       }

    }


    private  static String getSecretKey(String key){
        return new BASE64Encoder().encode(key.getBytes());
    }

}
