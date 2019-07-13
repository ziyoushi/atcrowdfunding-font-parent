package com.atguigu.atcrowdfunding.front.enums;

/**
 * @author Administrator
 * @create 2019-06-20 20:54
 */
public enum ProjectStatusEnume {

    DRAFT("0","草稿"),
    SUBMIT_AUTH("1","提交审核申请"),
    AUTHING("2","后台正在审核"),
    AUTHED("3","后台审核通过"),
    AUTHFAIL("4","审核失败"),
    STARTING("5","开始众筹"),//新增众筹项目一些状态
    SUCCESS("6","众筹成功"),
    FINISHED("7","众筹完成"),
    FAIL("8","众筹失败");

    private String code;
    private String msg;

    private ProjectStatusEnume(String code,String msg){

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
