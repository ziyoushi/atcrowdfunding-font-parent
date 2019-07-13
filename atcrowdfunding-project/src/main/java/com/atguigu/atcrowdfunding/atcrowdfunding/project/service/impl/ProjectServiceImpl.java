package com.atguigu.atcrowdfunding.atcrowdfunding.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.mapper.*;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.service.ProjectService;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.ProjectRedisStoreVo;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.req.BaseVo;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.req.ProjectBaseInfoVo;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.vo.req.ProjectReturnVo;
import com.atguigu.atcrowdfunding.front.bean.*;
import com.atguigu.atcrowdfunding.front.constant.AppConstant;
import com.atguigu.atcrowdfunding.front.enums.ProjectImageTypeEnume;
import com.atguigu.atcrowdfunding.front.enums.ProjectStatusEnume;
import com.atguigu.atcrowdfunding.front.exception.LoginRegisterException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author changchen
 * @create 2019-06-18 下午 3:34
 *
 * 注意 最终的目的就是 将数据 逐渐封装 到 ProjectRedisStoreVo
 * 中心思想 就通过redis来进行数据共享
 * 项目的流程 以projectToken为主线 只要知道projectToken就能从数据库中查出经过封装的数据
 */
@Service
public class ProjectServiceImpl  implements ProjectService {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    TProjectMapper projectMapper;

    @Autowired
    TProjectImagesMapper imagesMapper;

    @Autowired
    TProjectTypeMapper projectTypeMapper;

    @Autowired
    TProjectTagMapper projectTagMapper;

    @Autowired
    TReturnMapper returnMapper;

    @Autowired
    TTagMapper tagMapper;

    @Autowired
    TTypeMapper typeMapper;

    @Override
    public String initProject(String accessToken) {

        //根据accessToken 随机生成 projectToken给前端
        //根据accessToken到redis中查出Member
        String jsonString = redisTemplate.opsForValue().get(AppConstant.MEMBER_LOGIN_CHANGE_PREFIX + accessToken);
        //将json对象转换成Member对象
        TMember member = JSON.parseObject(jsonString, TMember.class);
        if (member==null){
            throw new LoginRegisterException(AppConstant.MEMBER_QUERY_EXCEPTION_MSG);
        }
        String projectToken = UUID.randomUUID().toString().replace("-","");
        //将这三个属性存到ProjectRedisStoreVo 后在存到 redis中
        ProjectRedisStoreVo projectRedisStoreVo = new ProjectRedisStoreVo();
        projectRedisStoreVo.setAccessToken(accessToken);
        projectRedisStoreVo.setMemberid(member.getId());
        projectRedisStoreVo.setProjectToken(projectToken);
        //将projectRedisStoreVo对象转成json字符串 并且存入redis
        String toJsonString = JSON.toJSONString(projectRedisStoreVo);
        redisTemplate.opsForValue().set(AppConstant.PROJECT_TEMP_CHANGE_PREFIX+projectToken,toJsonString);

        return projectToken;
    }

    @Override
    public boolean saveTempBaseInfo(ProjectBaseInfoVo baseInfoVo) {

        //根据projectToken进行增量负责
        String projectToken = baseInfoVo.getProjectToken();
        //从redis中查出已经有的数据
        String jsonString = redisTemplate.opsForValue().get(AppConstant.PROJECT_TEMP_CHANGE_PREFIX + projectToken);
        //将json字符串转换成对象
        ProjectRedisStoreVo projectRedisStoreVo = JSON.parseObject(jsonString, ProjectRedisStoreVo.class);
        //进行增量复制
        BeanUtils.copyProperties(baseInfoVo,projectRedisStoreVo);
        String toJSONString = JSON.toJSONString(projectRedisStoreVo);
        //再将toJSONString存到redis中
        redisTemplate.opsForValue().set(AppConstant.PROJECT_TEMP_CHANGE_PREFIX+projectToken,toJSONString);

        return true;
    }

    @Override
    public List<TTag> getSysTags() {
        TTagExample example = new TTagExample();
        List<TTag> tags = tagMapper.selectByExample(example);
        return tags;
    }

    @Override
    public List<TType> getSysTypes() {
        TTypeExample example = new TTypeExample();
        List<TType> types = typeMapper.selectByExample(example);
        return types;
    }

    @Override
    public boolean saveTempReturn(List<ProjectReturnVo> returns) {

        //根据projectToken查出redis中存的 ProjectRedisStoreVo
        //因为每个return中都有相同的projectToken
        String projectToken = returns.get(0).getProjectToken();
        String projectRedisStoreVoString = redisTemplate.opsForValue().get(AppConstant.PROJECT_TEMP_CHANGE_PREFIX + projectToken);
        ProjectRedisStoreVo projectRedisStoreVo = JSON.parseObject(projectRedisStoreVoString, ProjectRedisStoreVo.class);

        List<TReturn> returnList = new ArrayList<>();
        for (ProjectReturnVo returnVo : returns) {
            TReturn tReturn = new TReturn();
            BeanUtils.copyProperties(returnVo,tReturn);
            returnList.add(tReturn);
        }

        projectRedisStoreVo.setProjectReturns(returnList);

        String toJsonString = JSON.toJSONString(projectRedisStoreVo);
        redisTemplate.opsForValue().set(AppConstant.PROJECT_TEMP_CHANGE_PREFIX+projectToken,toJsonString);

        return true;
    }

    @Override
    public void submitProjectToDb(BaseVo vo) {

        saveProject(vo,ProjectStatusEnume.SUBMIT_AUTH);
    }

    @Override
    public void tempProjectToDb(BaseVo vo) {
        saveProject(vo,ProjectStatusEnume.DRAFT);
    }

    //抽取公共的方法 根据传入的项目状态 来区别 是保存草稿 还是提交
    @Transactional
    public void saveProject(BaseVo vo, ProjectStatusEnume statusEnume){

        String projectToken = vo.getProjectToken();
        String projectJsonString = redisTemplate.opsForValue().get(AppConstant.PROJECT_TEMP_CHANGE_PREFIX + projectToken);
        ProjectRedisStoreVo projectRedisStoreVo = JSON.parseObject(projectJsonString, ProjectRedisStoreVo.class);

        //将基本数据取出后 操作到数据库中
        TProject project = new TProject();
        BeanUtils.copyProperties(projectRedisStoreVo,project);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        project.setCreatedate(format.format(new Date()));
        project.setStatus(statusEnume.getCode());

        //设置或者project的自增id
        projectMapper.insertSelective(project);
        Integer projectId = project.getId();

        //保存图片 imgtype 0-头部图片 1-详情图片
        TProjectImages projectImages = new TProjectImages();
        String headerImage = projectRedisStoreVo.getHeaderImage();
        projectImages.setImgurl(headerImage);
        projectImages.setProjectid(projectId);
        projectImages.setImgtype(ProjectImageTypeEnume.HEADER.getCode());
        imagesMapper.insertSelective(projectImages);

        //保存详图
        List<String> detailsImage = projectRedisStoreVo.getDetailsImage();
        detailsImage.forEach((url)->{
            projectImages.setImgurl(url);
            projectImages.setProjectid(projectId);
            projectImages.setImgtype(ProjectImageTypeEnume.DETAILS.getCode());
            imagesMapper.insertSelective(projectImages);
        });

        //保存项目分类
        List<Integer> typeids = projectRedisStoreVo.getTypeids();
        typeids.forEach((typeId)->{
            TProjectType projectType = new TProjectType();
            projectType.setProjectid(projectId);
            projectType.setTypeid(typeId);
            projectTypeMapper.insertSelective(projectType);
        });

        //保存项目标签
        List<Integer> tagids = projectRedisStoreVo.getTagids();
        tagids.forEach((tagId)->{
            TProjectTag projectTag = new TProjectTag();
            projectTag.setProjectid(projectId);
            projectTag.setTagid(tagId);
            projectTagMapper.insertSelective(projectTag);
        });

        //保存 回报
        List<TReturn> projectReturns = projectRedisStoreVo.getProjectReturns();
        projectReturns.forEach((tReturn)->{
            tReturn.setProjectid(projectId);
            returnMapper.insertSelective(tReturn);
        });

        //保存成功后 删除redis中的数据
        redisTemplate.delete(AppConstant.PROJECT_TEMP_CHANGE_PREFIX+projectToken);

    }


}
