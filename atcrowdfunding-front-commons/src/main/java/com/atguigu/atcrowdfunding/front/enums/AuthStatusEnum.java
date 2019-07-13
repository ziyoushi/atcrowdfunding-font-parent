package com.atguigu.atcrowdfunding.front.enums;

/**
 * @author changchen
 * @create 2019-06-18 下午 12:58
 * 实名认证 状态
 * 0未认证 1实名认证申请中 2已实名认证
 */
public enum AuthStatusEnum {

    UNAUTH("0","未实名认证"),
    AUTHING("1","实名认证申请中"),
    AUTHED("2","已实名认证");

    private String code;
    private String msg;

    private AuthStatusEnum(String code,String msg){

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
