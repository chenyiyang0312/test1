package com.fh.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("t_shop_brand")
@Data

public class BrandBean {


    private  Integer brandId;

    private String brandName;

    private String telPhone;

    private  String webSite;

    private Integer categoryId;

    private String brandLogo;

    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Data updateTime;








}
