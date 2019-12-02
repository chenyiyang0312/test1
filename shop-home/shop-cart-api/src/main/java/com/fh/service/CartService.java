package com.fh.service;

import com.fh.utils.ResponseServer;

import java.util.Map;

public interface CartService {
    ResponseServer addCarts(Integer productId, String userPhone);

    Map<String, Object> queryCarts(String phone);

    void changeNum(Integer productId, String phone);

    void updateCheckStatus(Integer productId, String phone);

    ResponseServer deleteShop(Integer productId, String phone);
}
