package com.atguigu.atcrowdfunding.app.service.feign;

import com.atguigu.atcrowdfunding.front.bean.TMember;
import com.atguigu.atcrowdfunding.front.common.AppResponse;
import com.atguigu.atcrowdfunding.front.vo.req.MemberRegisterVo;
import com.atguigu.atcrowdfunding.front.vo.resp.MemberResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @create 2019-06-20 16:34
 * 远程调用 user服务
 */
@FeignClient(value = "ATCROWDFUNDING-USER")
public interface UserServiceFeign {

    //调用user的登录方法 将登录信息返回 用户登录必须的参数带上
    @PostMapping("/user/login")
    public AppResponse<MemberResponseVo> logn(@RequestParam(value = "loginacct",required = true) String loginacct,
                                              @RequestParam(value = "userpswd",required = true) String userpswd);

    //远程调用user服务的发送短信 将数据取出
    @PostMapping("/user/sendsms")
    public AppResponse<String> sendCode(@RequestParam(value = "mobile",required = true)String mobile);

    //远程调用user服务的注册
    @PostMapping("/user/register")
    public  AppResponse<TMember> register(@RequestBody MemberRegisterVo registerVo);

}
