package com.atguigu.atcrowdfunding.atcrowdfunding.user.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.atcrowdfunding.atcrowdfunding.user.component.SmsTemplate;
import com.atguigu.atcrowdfunding.atcrowdfunding.user.service.MemberService;
import com.atguigu.atcrowdfunding.atcrowdfunding.user.vo.req.MemberRegisterVo;
import com.atguigu.atcrowdfunding.atcrowdfunding.user.vo.resp.MemberResponseVo;
import com.atguigu.atcrowdfunding.front.bean.TMember;
import com.atguigu.atcrowdfunding.front.common.AppResponse;
import com.atguigu.atcrowdfunding.front.constant.AppConstant;
import com.atguigu.atcrowdfunding.front.exception.LoginRegisterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author changchen
 * @create 2019-06-17 下午 8:33
 */
@Api(tags = "用户登录注册模块")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserLoginRegistController {


    @Autowired
    SmsTemplate smsTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    MemberService memberService;

    //用户登录
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginacct", value = "登录账号或者手机号", required = true),
            @ApiImplicitParam(name = "userpswd", value = "密码", required = true)
    })
    @PostMapping("/login")
    public AppResponse<MemberResponseVo> login(String loginacct, String userpswd) {

        //即根据loginacct去查询
        TMember member = memberService.login(loginacct,userpswd);
        if (member == null){
            return AppResponse.fail().msg(AppConstant.LOGIN_FAIL_MSG);
        }else{
            //登录成功 将查询出来的数据 存到redis中
            MemberResponseVo memberResponseVo = new MemberResponseVo();
            //将查询出来的TMember中的属性复制到memberResponseVo
            BeanUtils.copyProperties(member,memberResponseVo);
            //生成一个token封装 到memberResponseVo
            //并将这个对象封装成json字符串保存到redis中
            String token = UUID.randomUUID().toString().replace("-","");
            memberResponseVo.setAccessToken(token);

            //将普通对象转成json字符串 并存到redis中
            String jsonString = JSON.toJSONString(memberResponseVo);
            stringRedisTemplate.opsForValue().set(AppConstant.MEMBER_LOGIN_CHANGE_PREFIX+token,jsonString);

            return AppResponse.success(memberResponseVo);

        }

    }

    //用户注册
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public AppResponse<TMember> register( MemberRegisterVo memberRegistVo) {
        log.debug("{}用户正在注册,",memberRegistVo.getMobile());
        //对前台传递的数据进行校验
        if (StringUtils.isEmpty(memberRegistVo.getCode())){
            return AppResponse.fail().msg("注册码必须填写");
        }

        if (StringUtils.isEmpty(memberRegistVo.getMobile())){
            return AppResponse.fail().msg("手机号必须填写");
        }

        if (StringUtils.isEmpty(memberRegistVo.getEmail())){
            return AppResponse.fail().msg("邮箱必须填写");
        }

        if (StringUtils.isEmpty(memberRegistVo.getPassword())){
            return AppResponse.fail().msg("密码不能为空");
        }

        //校验验证码是否过期  根据手机号码到redis中查询的code
        String code = stringRedisTemplate.opsForValue().get(AppConstant.CODE_CHANGE_PREFIX + memberRegistVo.getMobile());

        if (StringUtils.isEmpty(code)){
            //验证码过期 重新发送
            return AppResponse.fail().msg("验证码过期,请重新获取！");
        }else {

            if(!code.equalsIgnoreCase(memberRegistVo.getCode())){
                return AppResponse.fail().msg("验证码错误。");
            }else {
                //如果匹配 将redis中的code删除 进行注册操作
                stringRedisTemplate.delete(AppConstant.CODE_CHANGE_PREFIX+memberRegistVo.getMobile());

                try {
                    memberService.register(memberRegistVo);
                }catch (LoginRegisterException e){
                    e.getMessage();

                }



            }

        }

        return AppResponse.success("").msg("注册成功");
    }

    //重置密码
    @ApiOperation("重置密码")
    @PostMapping("/reset")
    public AppResponse<TMember> reset() {
        return null;
    }

    //发送短信验证码
    @ApiOperation("发送短信验证码")
    @PostMapping("/sendsms")
    public AppResponse<String> sendsms(@RequestParam("mobile") String mobile) {

        String code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        smsTemplate.sendCodeSms(mobile,code);

        //发送短信成功后将 code存入redis 为后面登录进行校验 并且设置5分钟过期
        stringRedisTemplate.opsForValue().set(AppConstant.CODE_CHANGE_PREFIX+mobile,code,5, TimeUnit.MINUTES);

        return AppResponse.success(code).msg("短信发送完成");
    }

    //验证短信验证码
    @ApiOperation("验证短信验证码")
    @PostMapping("/valide")
    public AppResponse<String> valide() {
        return null;
    }


}
