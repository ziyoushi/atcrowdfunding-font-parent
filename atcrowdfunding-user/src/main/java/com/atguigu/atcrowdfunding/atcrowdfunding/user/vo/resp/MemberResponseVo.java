package com.atguigu.atcrowdfunding.atcrowdfunding.user.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author changchen
 * @create 2019-06-19 下午 7:42
 *
 */
@ApiModel
@Data
public class MemberResponseVo {

    @ApiModelProperty("访问令牌，登录以后所有请求都必须携带访问令牌")
    private String accessToken;

    private String loginacct;

    private String username;

    private String email;

    private String authstatus;

    private String usertype;

    private String realname;

    private String cardnum;

    private String accttype;

}
