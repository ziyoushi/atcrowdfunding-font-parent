package com.atguigu.atcrowdfunding.front.enums;

/**
 * @author Administrator
 * @create 2019-06-20 20:48
 */
public enum ProjectReturnEnume {
    //0 - 实物回报， 1 虚拟物品回报
    VIRTUAL("1","虚拟物品回报"),
    REAL("0","实物回报");

    private String code;
    private String msg;

    private ProjectReturnEnume(String code,String msg){

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
