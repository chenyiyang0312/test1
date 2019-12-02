package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.bean.UserBean;
import com.fh.dao.UserDao;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;



    @Override
    public UserBean isregister(String phone) {

        //判断手机号是否存在
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<UserBean>();
        queryWrapper.eq("phone", phone);
        UserBean customer= userDao.selectOne(queryWrapper);
        if(customer == null){
            customer =new UserBean();
            customer.setModifiedTime(new Date());
            customer.setUserStats(1);
            customer.setPhone(phone);
            customer.setCartId(UUID.randomUUID().toString().replace("-",""));
            userDao.insert(customer);
        }else{
            customer.setModifiedTime(new Date());
            userDao.updateById(customer);
        }
        return customer;
    }
}
