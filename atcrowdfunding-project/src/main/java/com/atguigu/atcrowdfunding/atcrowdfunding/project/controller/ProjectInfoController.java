package com.atguigu.atcrowdfunding.atcrowdfunding.project.controller;

import com.atguigu.atcrowdfunding.atcrowdfunding.project.service.ProjectService;
import com.atguigu.atcrowdfunding.front.bean.TTag;
import com.atguigu.atcrowdfunding.front.bean.TType;
import com.atguigu.atcrowdfunding.front.common.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "项目信息模块")
@RequestMapping("/project")
@RestController
public class ProjectInfoController {


    @Autowired
    ProjectService projectService;

    @ApiOperation("获取首页广告项目")
    @GetMapping("/adv")
    public AppResponse<String> getIndexAdv() {
        return null;
    }

    @ApiOperation("获取项目总览列表")
    @GetMapping("/all/index")
    public AppResponse<String> getAllIndex() {
        return null;
    }

    @ApiOperation("获取项目详情")
    @GetMapping("/info/detail")
    public AppResponse<String> getDetail(){
        return null;
    }

    @ApiOperation("获取首页热门推荐")
    @GetMapping("/recommend/index")
    public AppResponse<String> recommendIndex(){
        return null;
    }

    @ApiOperation("获取首页分类推荐")
    @GetMapping("/recommend/type")
    public AppResponse<String> recommendType(){
        return null;
    }

    @ApiOperation("获取项目回报档位信息")
    @GetMapping("/return/info")
    public AppResponse<String> returnInfo(){
        return null;
    }



    @ApiOperation("获取项目系统标签信息")
    @GetMapping("/sys/tags")
    public AppResponse<List<TTag>> sysTags(){


        List<TTag> tags =  projectService.getSysTags();
        return AppResponse.success(tags);
    }

    @ApiOperation("获取项目系统分类信息")
    @GetMapping("/sys/type")
    public AppResponse<List<TType>> sysType(){

        List<TType> types =  projectService.getSysTypes();
        return AppResponse.success(types);
    }
}
