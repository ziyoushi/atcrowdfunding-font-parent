package com.atguigu.atcrowdfunding.atcrowdfunding.user.service;

import com.atguigu.atcrowdfunding.atcrowdfunding.user.vo.req.MemberRegisterVo;
import com.atguigu.atcrowdfunding.front.bean.TMember;

/**
 * @author changchen
 * @create 2019-06-19 下午 7:27
 */
public interface MemberService {

    /**
     * 用户注册
     *
     * @param memberRegistVo
     */
    void register(MemberRegisterVo memberRegistVo);

    /**
     * 用户登录
     * @param loginacct
     * @param userpswd
     * @return
     */
    TMember login(String loginacct, String userpswd);
}
