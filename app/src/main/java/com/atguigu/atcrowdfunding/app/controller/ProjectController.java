package com.atguigu.atcrowdfunding.app.controller;

import com.atguigu.atcrowdfunding.app.component.OssTemplate;
import com.atguigu.atcrowdfunding.app.service.feign.ProjectServiceFeign;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.resp.ProjectTempVo;
import com.atguigu.atcrowdfunding.front.bean.TTag;
import com.atguigu.atcrowdfunding.front.bean.TType;
import com.atguigu.atcrowdfunding.front.common.AppResponse;
import com.atguigu.atcrowdfunding.front.vo.req.ProjectBaseInfoVo;
import com.atguigu.atcrowdfunding.front.vo.req.ProjectReturnVo;
import com.atguigu.atcrowdfunding.front.vo.resp.MemberResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @create 2019-06-21 10:49
 */
//@CrossOrigin //哪个请求需要调用远程服务 则直接在其上开启跨域
@Controller
@RequestMapping("/project")
@Slf4j
public class ProjectController {


    @Autowired
    ProjectServiceFeign projectServiceFeign;

    @Autowired
    OssTemplate ossTemplate;

    @GetMapping("/start.html")
    public String startProject(){
        return "protected/project/start";
    }

    @GetMapping("/start-step-1.html")
    public String startStep1(HttpSession session, Model model){

        MemberResponseVo loginUser = (MemberResponseVo) session.getAttribute("loginUser");
        //远程调用 初始化项目 ProjectTempVo
        AppResponse<ProjectTempVo> response = projectServiceFeign.init(loginUser.getAccessToken());
        ProjectTempVo data = response.getData();
        //将projectToken存入session中
        session.setAttribute("projectToken",data.getProjectToken());
        //在跳转页面前 将页面所需要的分类 标签 查询处理
        AppResponse<List<TTag>> tagResponse =projectServiceFeign.sysTags();
        AppResponse<List<TType>> typeResponse = projectServiceFeign.sysType();
        model.addAttribute("types",typeResponse.getData());

        //远程调用查询出来标签
        List<TTag> allTags = tagResponse.getData();
        List<TTag> tagList = new ArrayList<>();

        for (TTag tTag : allTags) {
            if (tTag.getPid()==0){
                tagList.add(tTag);
            }
        }

        for (TTag tTag : tagList) {
            //为每个父标签 找子标签
            //对所有的数据进行遍历
            for (TTag tag : allTags) {
                if (tag.getPid()==tTag.getId()){
                    tTag.getChild().add(tag);
                }

            }

        }

        model.addAttribute("tags",tagList);

        return "protected/project/start-step-1";
    }

    //@PostMapping("/start-step-2.html")
    @PostMapping("/start-step-2.html")
    public String startStep2(ProjectBaseInfoVo projectBaseInfoVo, @RequestParam("header") MultipartFile file,
                             @RequestParam("details") MultipartFile[] files, HttpSession session,
                             RedirectAttributes redirectAttributes) throws IOException {

        MemberResponseVo loginUser = (MemberResponseVo) session.getAttribute("loginUser");
        log.debug("从页面获取的参数{}",projectBaseInfoVo);
        log.debug("从redis中查出的loginUser，{}",loginUser);

        if (!file.isEmpty()){
            String headImgUrl = ossTemplate.upload(file.getBytes(), file.getOriginalFilename());
            log.debug("上传头图成功,{}",headImgUrl);
            projectBaseInfoVo.setHeaderImage(headImgUrl);
        }

        List<String> detailImg = new ArrayList<>();
        if (files != null){
            for (MultipartFile itemFile : files) {
                String itemImgUrl = ossTemplate.upload(itemFile.getBytes(), itemFile.getOriginalFilename());
                log.debug("上传详情图成功{}",itemImgUrl);
                detailImg.add(itemImgUrl);
            }
            projectBaseInfoVo.setDetailsImage(detailImg);
        }

        //将accessToken projectToken 封装到baseInfoVo 将baseInfoVo存放到session中
        projectBaseInfoVo.setAccessToken(loginUser.getAccessToken());
        projectBaseInfoVo.setProjectToken((String) session.getAttribute("projectToken"));

        //远程调用project服务的保存基本信息
        AppResponse<String> response = projectServiceFeign.saveBaseInfo(projectBaseInfoVo);

        //使用重定向可以防止表单重复提交
        if (response.getCode()==0){
            //如果调用成 到 step-2 否则回显
            return "redirect:/start-step-2.html";
        }else {
            //返回错误信息
            redirectAttributes.addAttribute("msg","请重新录入数据");
            redirectAttributes.addAttribute("vo",projectBaseInfoVo);
            return "redirect:/project/start-step-1.html";
        }

    }
    @GetMapping("/start-step-3.html")
    public AppResponse<String> startStep3(@RequestBody List<ProjectReturnVo> returns,HttpSession session){

        AppResponse<String> response = projectServiceFeign.addReturn(returns);


        return response;

//        return "protected/project/start-step-3";

    }
    @GetMapping("/start-step-4.html")
    public String startStep4(){
        return "protected/project/start-step-4";
    }

}
