package com.fh.utils;

public enum ServerEnum {
    ACCOUNT_OR_PWD_ISNULL(1001,"账号或者密码不存在"),
    ACCOUNT_ERROR(5001,"账号异常，请联系管理员")
    ,ACCOUNT_NOT_EXIST(1002,"账号不存在请联系管理员")
    ,ACCOUNT_NOT_INVALID(1003,"账号无效请联系管理员")
    ,TOKEN_TIMEOUT(5006,"登陆失败，请重新登陆")
    ,PHONE_ISNULL(5007,"手机号不能为空")
    ,LOGIN_PHONEORCODE_ISNULL(10000,"手机号或者验证码为空")
    ,SAFETY_ERROR(9000,"接口验签失败")
    ,SERVER_TIMEOUT(8004,"服务连接请求超时")
    ,SERVER_STOP(8005,"服务连接不上")

    ,LOGIN_CODE_ERROR(10001,"手机验证码输入有误")

    ,PWD_ERROR(1004,"密码输入有误，请重新输入")
    ,RIGHT_NO(1005,"该用户没有权限访问，请联系管理员")
    ,SUCCESS(200,"成功")
    ,ERROR(500,"失败")
    ,EXPORT_NULL(7001,"导出数据是空的")
    ,EXPORT_Please(8001,"请假时间选择有误")
    ,FLOW_NO_RIGHT(8002,"没有权限审批")
    ,RIGHT_NO_TO(8004,"没有权限访问");
    private ServerEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }
    private Integer code;
    private String message;


    public Integer getCode() {

        return code;
    }

    public String getMessage() {

        return message;
    }
}
