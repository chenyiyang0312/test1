package com.fh.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_shop_product")
public class ShopProductBean {
    private Integer id;

    private Integer brandId;

    private String name;

    private String subtitle;//宣传标题

    private String mainImg;//主图片

    private BigDecimal price;//价格

    private Integer stock;//库存

    private Integer status;//状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;


    //展示品牌名称使用
    private String brandName;
    //展示商品类型名称
    private String categroyName;
    //获取商品类型ID
    private String categroyIds;


}