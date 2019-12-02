package com.fh.service.impl;

import com.fh.bean.BrandBean;
import com.fh.dao.BrandDao;
import com.fh.service.BrandService;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;
    @Override
    public ResponseServer queryBrandsByCateId(Integer pid) {
        List<BrandBean>  brandList = new ArrayList<>();
        brandList =  brandDao.queryBrandsByCateId();

        List<BrandBean> returnList= brandList.stream()
                .filter(BrandBean->BrandBean.getCategoryId().equals(pid))
                .collect(Collectors.toList());
        return ResponseServer.success(returnList);





    }
}
