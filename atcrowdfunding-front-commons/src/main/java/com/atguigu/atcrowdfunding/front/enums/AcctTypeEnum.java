package com.atguigu.atcrowdfunding.front.enums;

/**
 * @author changchen
 * @create 2019-06-18 下午 1:46
 * 账户类型
 * 账户类型: 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府
 */
public enum  AcctTypeEnum {

    COMPANY("0","企业"),
        PERSON_COMPANY("1","个体"),
            PERSON("2","个人"),
                GOV("3","政府");

    private String code;
    private String msg;
    private AcctTypeEnum(String code,String msg){
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
