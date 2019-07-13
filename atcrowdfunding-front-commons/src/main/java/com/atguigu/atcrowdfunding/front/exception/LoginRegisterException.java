package com.atguigu.atcrowdfunding.front.exception;

/**
 * @author changchen
 * @create 2019-06-18 下午 12:56
 */

/**
 * 登录注册 异常类
 */
public class LoginRegisterException extends RuntimeException{

    public LoginRegisterException(String msg){
        super(msg);
    }
}
