package com.atguigu.atcrowdfunding.front.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author changchen
 * @create 2019-06-18 下午 4:25
 *
 */
@ToString
@Data
public class ProjectBaseInfoVo extends BaseVo {

    private List<Integer> typeids; // 项目的分类id

    private List<Integer> tagids; // 项目的标签id

    private String name;// 项目名称

    private String remark;// 项目简介

    private Integer money;// 筹资金额

    private Integer day;// 筹资天数

    private String headerImage;// 项目头部图片

    private List<String> detailsImage;// 项目详情图片

    //发起人 该阶段没有做 后期进行维护 todo


}
