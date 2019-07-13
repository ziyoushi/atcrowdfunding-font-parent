package com.atguigu.atcrowdfunding.front.enums;

/**
 * @author changchen
 * @create 2019-06-18 下午 1:03
 *  用户类型: 0 - 个人， 1 - 企业
 */
public enum UserTypeEnum {

    PERSON("0","个人"),
    COMPANY("1","企业");

    private String code;
    private String msg;

    private UserTypeEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
