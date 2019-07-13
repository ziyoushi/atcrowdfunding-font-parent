package com.atguigu.atcrowdfunding.front.vo.req;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author changchen
 * @create 2019-06-18 下午 11:23
 */
@Data
public class BaseVo {

    @ApiModelProperty(value = "访问令牌",required = true)
    private String accessToken;
    @ApiModelProperty(value = "项目临时令牌",required = true)
    private String projectToken;
}
