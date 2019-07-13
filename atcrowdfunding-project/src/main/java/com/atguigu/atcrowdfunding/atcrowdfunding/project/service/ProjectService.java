package com.atguigu.atcrowdfunding.atcrowdfunding.project.service;

import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.req.BaseVo;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.req.ProjectReturnVo;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.req.ProjectBaseInfoVo;
import com.atguigu.atcrowdfunding.front.bean.TTag;
import com.atguigu.atcrowdfunding.front.bean.TType;

import java.util.List;

/**
 * @author changchen
 * @create 2019-06-18 下午 3:34
 *
 */
public interface ProjectService {

    /**
     * 项目初始化 生成projectToken
     * @param accessToken
     * @return
     */
    String initProject(String accessToken);

    /**
     * 项目创建 第二步 保存基础数据
     * @param baseInfoVo
     * @return
     */
    boolean saveTempBaseInfo(ProjectBaseInfoVo baseInfoVo);

    /**
     * 获取项目系统标签
     * @return
     */
    List<TTag> getSysTags();

    /**
     * 获取项目系统分类
     * @return
     */
    List<TType> getSysTypes();

    /**
     * 添加项目 回报档位
     * @param returns
     * @return
     */
    boolean saveTempReturn(List<ProjectReturnVo> returns);

    /**
     * 项目提交审核申请
     * @param vo
     */
    void submitProjectToDb(BaseVo vo);

    /**
     * 项目草稿保存
     * @param vo
     */
    void tempProjectToDb(BaseVo vo);
}
