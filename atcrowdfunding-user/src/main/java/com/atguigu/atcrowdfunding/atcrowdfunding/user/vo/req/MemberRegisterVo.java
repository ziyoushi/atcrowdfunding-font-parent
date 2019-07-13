package com.atguigu.atcrowdfunding.atcrowdfunding.user.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author changchen
 * @create 2019-06-17 下午 7:59
 */

/**
 * 封装注册提交来的数据 5个以上就抽取vo
 *
 */
@ApiModel
@Data
public class MemberRegisterVo {

    @ApiModelProperty(value = "手机号",required = true)
    private String mobile;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "短信验证码",required = true)
    private String code;
}
