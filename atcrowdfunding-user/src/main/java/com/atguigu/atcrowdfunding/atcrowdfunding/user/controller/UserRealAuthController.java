package com.atguigu.atcrowdfunding.atcrowdfunding.user.controller;

import com.atguigu.atcrowdfunding.front.common.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changchen
 * @create 2019-06-17 下午 8:33
 */
@RequestMapping("/user/auth")
@RestController
@Api(tags = "用户实名审核模块")
public class UserRealAuthController {


    //认证提交申请 第二步 提交基本信息
    @ApiOperation("认证提交申请 第二步 提交基本信息")
    @ApiModelProperty
    @PostMapping("/baseInfo")
    public AppResponse<String> baseInfo(){
        return null;
    }


    @ApiOperation("认证提交申请 第3步 上传资质信息")
    @ApiModelProperty
    @PostMapping("/certs")
    public AppResponse<String> certs(){
        return null;
    }

    //获取要上传的资质信息
    @ApiOperation("获取要上传的资质信息")
    @ApiModelProperty
    @GetMapping("/certs2upload")
    public AppResponse<String> certs2upload(){
        return null;
    }

    @ApiOperation("认证提交申请 第4步 确认邮箱信息")
    @ApiModelProperty
    @PostMapping("/email")
    public AppResponse<String> email(){
        return null;
    }


    @ApiOperation("认证提交申请 第1步 用户认证申请开始")
    @ApiModelProperty
    @GetMapping("/start")
    public AppResponse<String> start(){
        return null;
    }

    @ApiOperation("认证提交申请 第5步 提交实名认证申请")
    @ApiModelProperty
    @PostMapping("/submit")
    public AppResponse<String> submit(){
        return null;
    }


}
