package com.atguigu.atcrowdfunding.front.enums;

/**
 * @author Administrator
 * @create 2019-06-20 20:56
 */
public enum ProjectInvoiceEnume {

    NO_FP("0", "不开发票"),
    HAVE_FP("1", "开发票");

    private String code;
    private String msg;

    private ProjectInvoiceEnume(String code,String msg){

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
