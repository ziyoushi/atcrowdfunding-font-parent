package com.atguigu.atcrowdfunding.app.service.feign;

import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.resp.ProjectTempVo;
import com.atguigu.atcrowdfunding.front.bean.TTag;
import com.atguigu.atcrowdfunding.front.bean.TType;
import com.atguigu.atcrowdfunding.front.common.AppResponse;
import com.atguigu.atcrowdfunding.front.vo.req.ProjectBaseInfoVo;
import com.atguigu.atcrowdfunding.front.vo.req.ProjectReturnVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @create 2019-06-20 19:35
 *
 * 远程调用项目的接口
 */
@RequestMapping("/project")
@FeignClient(value = "ATCROWDFUNDING-PROJECT")
public interface ProjectServiceFeign {

    //初始化项目 根据accessToken 返回projectToken
    @PostMapping("/create/init")
    public AppResponse<ProjectTempVo> init(@RequestParam("accessToken") String accessToken);

    //获取项目标签
    @GetMapping("/sys/tags")
    public AppResponse<List<TTag>> sysTags();

    //获取项目分类
    @GetMapping("/sys/type")
    public AppResponse<List<TType>> sysType();

    //上传图片 弃用 不调用远程的project服务
    @PostMapping("/update")
    public AppResponse<List<String>> update(@RequestParam("file") MultipartFile[] file,
                                            @RequestParam("accessToken") String accessToken) throws IOException;
    //进入到step2 保存基本信息
    @PostMapping("/create/savebaseinfo")
    public AppResponse<String> saveBaseInfo(@RequestBody ProjectBaseInfoVo baseInfoVo);

    //添加项目回报
    @PostMapping("/create/return")
    public AppResponse<String> addReturn(@RequestBody List<ProjectReturnVo> returns);


}
