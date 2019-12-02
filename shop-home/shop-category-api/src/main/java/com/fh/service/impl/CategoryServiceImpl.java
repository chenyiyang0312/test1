package com.fh.service.impl;

import com.fh.bean.CateGoryBean;
import com.fh.dao.CateGoryDao;
import com.fh.service.CateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CateGoryService {
    @Autowired
    private CateGoryDao cateGoryDao;

    @Override
    public List<Map<String, Object>> queryCategoryList() {
        List<CateGoryBean> categoryBeans = cateGoryDao.queryCategoryList();
        return getCategory(0,categoryBeans);
    }

    private List<Map<String, Object>> getCategory(Integer pid, List<CateGoryBean> categoryBeans) {
        List<Map<String, Object>> list = new ArrayList<>();
        categoryBeans.forEach(cate -> {
            Map<String, Object> map = null;
            if (pid.equals(cate.getPid())) {
                map = new HashMap<>();
                map.put("id", cate.getCategoryId());
                map.put("name", cate.getTypeName());
                map.put("pid", cate.getPid());
                map.put("children", getCategory(cate.getCategoryId(), categoryBeans));
            }
            if (map != null) {
                list.add(map);
            }
        });
        return list;
    }

}
