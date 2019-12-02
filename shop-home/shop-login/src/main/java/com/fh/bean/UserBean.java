package com.fh.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("customer_login")
@Data
public class UserBean implements Serializable {

@TableId(value = "customerId",type = IdType.AUTO)
 private Integer    customerId;
@TableField("loginName")
 private String     loginName;
 @TableField("phone")
 private String     phone;
 @TableField("password")
 private String     password;
 @TableField("userStats")
 private Integer    userStats;
 @TableField("modifiedTime")
 @DateTimeFormat(pattern = "yyyy-MM-dd")
 private Date       modifiedTime;
 @TableField("cartId")
 private String     cartId;

}
