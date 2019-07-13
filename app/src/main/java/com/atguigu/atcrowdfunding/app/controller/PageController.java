package com.atguigu.atcrowdfunding.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @create 2019-06-21 10:42
 */
@Controller
public class PageController {


    @GetMapping("/member.html")
    public String toMember(HttpSession session, RedirectAttributes redirectAttributes){

        Object loginUser = session.getAttribute("loginUser");
        if(loginUser == null){

            redirectAttributes.addAttribute("msg","还没登录,请登录");
            return "redirect:/login.html";
        }else {
            //如果成功 --member 失败 --》login
            return "protected/member";
        }

    }


    @GetMapping("/minecrowdfunding.html")
    public String minecrowdfunding(){

        return "protected/member/minecrowdfunding";
    }

}
