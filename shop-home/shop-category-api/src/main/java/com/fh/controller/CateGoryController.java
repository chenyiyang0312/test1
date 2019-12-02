package com.fh.controller;

import com.fh.redis.RedisService;
import com.fh.service.CateGoryService;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("categorys")
@CrossOrigin(maxAge = 4500, origins = "http://localhost:8080")
public class CateGoryController {
    @Autowired
    private CateGoryService cateGoryService;

    @Autowired
    private RedisService redisService;
    /**
     * 查询所有的类别的数据
     *
     * @return
     */
    @GetMapping
    public ResponseServer queryCategoryList() {
       /* List<Map<String, Object>> maps = cateGoryService.queryCategoryList();
        return ResponseServer.success(maps);
*/

        //先查缓存再去查数据库
        Boolean isExistKey=redisService.isExistKey("categoryAll");
        Object categoryList=null;
        if(!isExistKey){
            categoryList=cateGoryService.queryCategoryList();
            redisService.setStringKeyAndValue("categoryAll",categoryList);
        }else{
            categoryList=redisService.getStringValueByKey("categoryAll");
        }
        return ResponseServer.success(categoryList);
    }




}
