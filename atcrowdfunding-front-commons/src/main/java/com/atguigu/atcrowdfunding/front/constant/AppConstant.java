package com.atguigu.atcrowdfunding.front.constant;

/**
 * @author changchen
 * @create 2019-06-18 下午 1:10
 * 系统常量
 */
public class AppConstant {

    //保存短信的key
    public static final String CODE_CHANGE_PREFIX = "code:";
    //用户登录成功后的key
    public static final String MEMBER_LOGIN_CHANGE_PREFIX = "member:login:info:";

    // 用户账号异常
    public static final String USER_LOGINACCT_EXCEPTION_MSG = "用户账号/手机号已经存在";
    // 用户邮箱异常
    public static final String USER_EMAIL_EXCEPTION = "邮箱已经存在";

    //登录失败
    public static final String LOGIN_FAIL_MSG = "登录失败,账号/密码错误";

    //项目创建临时索引
    public static final String PROJECT_TEMP_CHANGE_PREFIX = "project:temp:";

    //根据accessToken查询Member失败异常
    public static final String MEMBER_QUERY_EXCEPTION_MSG = "用户不存在。";

    //用户未登录
    public static final String USER_UNLOGIN_MSG = "用户未登录";


}
