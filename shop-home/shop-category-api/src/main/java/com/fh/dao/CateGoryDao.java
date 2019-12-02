package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.bean.CateGoryBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CateGoryDao extends BaseMapper<CateGoryBean> {
    List<CateGoryBean> queryCategoryList();
}
