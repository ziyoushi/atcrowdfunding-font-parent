package com.atguigu.atcrowdfunding.front.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author changchen
 * @create 2019-06-17 下午 8:07
 */


@ApiModel
@Data
public class AppResponse<T> {

    @ApiModelProperty("请求是否成功的状态码 0表示成功 1-其他 失败" )
    private Integer code;

    @ApiModelProperty("成功或失败的提示")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    //快速返回成功
    public static<T> AppResponse<T> success(T data){
        AppResponse<T> appResponse = new AppResponse<>();
        appResponse.setCode(0);
        appResponse.setMsg("操作成功");
        appResponse.setData(data);
        return appResponse;
    }

    public static<T> AppResponse<T> fail(){
        AppResponse<T> appResponse = new AppResponse<>();
        appResponse.setCode(1);
        appResponse.setMsg("操作失败");
        return appResponse;
    }

    public AppResponse msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public AppResponse code(Integer code){
        this.setCode(code);
        return this;
    }

    public AppResponse data(T data){
        this.setData(data);
        return this;
    }

}
