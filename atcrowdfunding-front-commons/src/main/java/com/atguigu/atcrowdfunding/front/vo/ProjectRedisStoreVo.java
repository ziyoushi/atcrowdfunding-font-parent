package com.atguigu.atcrowdfunding.front.vo;

import com.atguigu.atcrowdfunding.front.bean.TReturn;
import com.atguigu.atcrowdfunding.front.vo.req.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * @author changchen
 * @create 2019-06-18 下午 3:18
 */
@Data
public class ProjectRedisStoreVo extends BaseVo {

    private Integer memberid;//会员id
    private List<Integer> typeids; //项目的分类id
    private List<Integer> tagids; //项目的标签id
    private String name;//项目名称
    private String remark;//项目简介
    private Integer money;//筹资金额
    private Integer day;//筹资天数
    private String headerImage;//项目头部图片
    private List<String> detailsImage;//项目详情图片
    private List<TReturn> projectReturns;//项目回报
    //发起人信息：自我介绍，详细自我介绍，联系电话，客服电话

    //0 - 即将开始， 1 - 众筹中， 2 - 众筹成功， 3 - 众筹失败
    private String status;

    //发布日期
    private String deploydate;

    //支持金额
    private Long supportmoney;

    //支持者数量
    private Integer supporter;

    //完成度
    private Integer completion;

    //创建日期
    private String createdate;

    //关注者数量
    private Integer follower;

}
