package com.atguigu.atcrowdfunding.atcrowdfunding.user.controller;

import com.atguigu.atcrowdfunding.front.bean.TMember;
import com.atguigu.atcrowdfunding.front.common.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author changchen
 * @create 2019-06-17 下午 7:56
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户个人信息模块")
public class UserInfoController {


    //获取个人信息
    @ApiOperation("获取个人信息")
    @ApiModelProperty
    @GetMapping("/info")
    public AppResponse<TMember> get(){
        return null;
    }

    @ApiOperation("获取个人信息")
    @ApiModelProperty
    @PostMapping("/info")
    public AppResponse<TMember> post(){
        return null;
    }

    //
    @ApiOperation("获取用户收货地址")
    @ApiModelProperty
    @GetMapping("/info/address")
    public AppResponse<TMember> addressGet(){
        return null;
    }

    @ApiOperation("获取用户收货地址")
    @ApiModelProperty
    @PostMapping("/info/address")
    public AppResponse<TMember> addressPost(){
        return null;
    }

    @ApiOperation("修改用户收货地址")
    @ApiModelProperty
    @PutMapping("/info/address")
    public AppResponse<TMember> addressPut(){
        return null;
    }

    @ApiOperation("修改用户收货地址")
    @ApiModelProperty
    @DeleteMapping("/info/address")
    public AppResponse<TMember> addressDelete(){
        return null;
    }

    //获取我发起的项目
    @ApiOperation("获取我发起的项目")
    @ApiModelProperty
    @GetMapping("/info/create/project")
    public AppResponse<TMember> createProject(){
        return null;
    }

    //获取我关注的项目
    @ApiOperation("获取我关注的项目")
    @ApiModelProperty
    @GetMapping("/info/star/project")
    public AppResponse<TMember> starProject(){
        return null;
    }

    //获取我支持的项目
    @ApiOperation("获取我支持的项目")
    @ApiModelProperty
    @GetMapping("/info/support/project")
    public AppResponse<TMember> supportProject(){
        return null;
    }


    //获取我的系统信息
    @ApiOperation("获取我的系统信息")
    @ApiModelProperty
    @GetMapping("/info/message")
    public AppResponse<TMember> message(){
        return null;
    }

    //查看我的订单
    @ApiOperation("查看我的订单")
    @ApiModelProperty
    @GetMapping("/info/order")
    public AppResponse<TMember> getOrder(){
        return null;
    }

    //删除我的订单
    @ApiOperation("删除我的订单")
    @ApiModelProperty
    @DeleteMapping("/info/order")
    public AppResponse<TMember> deleteOrder(){
        return null;
    }


}
