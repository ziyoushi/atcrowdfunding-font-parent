package com.atguigu.atcrowdfunding.app.controller;

import com.atguigu.atcrowdfunding.app.service.feign.UserServiceFeign;
import com.atguigu.atcrowdfunding.front.bean.TMember;
import com.atguigu.atcrowdfunding.front.common.AppResponse;
import com.atguigu.atcrowdfunding.front.vo.req.MemberRegisterVo;
import com.atguigu.atcrowdfunding.front.vo.resp.MemberResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.sound.midi.SoundbankResource;
import java.util.UUID;

/**
 * @author Administrator
 * @create 2019-06-20 13:18
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //远程调用 查询用户
    @Autowired
    UserServiceFeign userServiceFeign;

    //登录
    @PostMapping("/login")
    public String login(String loginacct, String userpwsd, HttpSession session, Model model){

        AppResponse<MemberResponseVo> appResponseMember = userServiceFeign.logn(loginacct, userpwsd);
        if (appResponseMember.getCode()==0){
            //如果登录成功 到index.html
            MemberResponseVo data = appResponseMember.getData();
            session.setAttribute("loginUser",data);
            return "redirect:/index.html";
        }else {
            model.addAttribute("msg",appResponseMember.getMsg());
            model.addAttribute("loginacct",loginacct);
            //登录失败到 login 继续登录 给错误提示
            return "login";
        }

    }

    //退出
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }

    //注册 todo
    @PostMapping("/register")
    public String regiser(MemberRegisterVo registerVo, RedirectAttributes redirectAttributes){

        System.out.println("获取表单数据"+registerVo);
        AppResponse<TMember> register = userServiceFeign.register(registerVo);
        if (register.getCode()==0){
            //注册成功

            redirectAttributes.addAttribute("msg","注册成功");
            //做数据回显
            redirectAttributes.addAttribute("vo",registerVo);
            //注册成功去 登录页
            return "redirect:/login.html";
        }else {
            redirectAttributes.addAttribute("msg",register.getMsg());
            redirectAttributes.addAttribute("vo",registerVo);
            return "redirect:/register";
        }

    }

    //发送短信
    @GetMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(String loginacct){

        System.out.println("准备进入到远程调用方法");
        //AppResponse<String> stringAppResponse = userServiceFeign.sendCode(loginacct);
        //String code = stringAppResponse.getData();
        //联调测试用 后面需要修改成远程调用的方式
        String code = UUID.randomUUID().toString().replace("-","").substring(0,6);
        return code;
    }


}
