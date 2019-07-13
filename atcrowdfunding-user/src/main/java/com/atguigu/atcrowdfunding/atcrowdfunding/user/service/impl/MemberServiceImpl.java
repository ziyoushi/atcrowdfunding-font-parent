package com.atguigu.atcrowdfunding.atcrowdfunding.user.service.impl;

import com.atguigu.atcrowdfunding.atcrowdfunding.user.mapper.TMemberMapper;
import com.atguigu.atcrowdfunding.atcrowdfunding.user.service.MemberService;
import com.atguigu.atcrowdfunding.atcrowdfunding.user.vo.req.MemberRegisterVo;
import com.atguigu.atcrowdfunding.front.bean.TMember;
import com.atguigu.atcrowdfunding.front.bean.TMemberExample;
import com.atguigu.atcrowdfunding.front.constant.AppConstant;
import com.atguigu.atcrowdfunding.front.enums.AcctTypeEnum;
import com.atguigu.atcrowdfunding.front.enums.AuthStatusEnum;
import com.atguigu.atcrowdfunding.front.enums.UserTypeEnum;
import com.atguigu.atcrowdfunding.front.exception.LoginRegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author changchen
 * @create 2019-06-19 下午 8:05
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    TMemberMapper memberMapper;

    @Override
    public void register(MemberRegisterVo memberRegistVo) {

        //注册使用BCryptPasswordEncoder 对明文密码进行加密
        //校验 登录名(手机号)是否唯一 邮箱是否唯一 唯一则进行添加 不唯一则抛出异常

        String mobile = memberRegistVo.getMobile();
        String email = memberRegistVo.getEmail();
        boolean checkLoginAcct = checkLoginAcct(mobile);
        if (checkLoginAcct){
            throw new LoginRegisterException(AppConstant.USER_LOGINACCT_EXCEPTION_MSG);
        }
        boolean checkEmail = checkEmail(email);
        if (checkEmail){
            throw new LoginRegisterException(AppConstant.USER_EMAIL_EXCEPTION);
        }
        //校验成功后对其进行 注册
        TMember member = new TMember();
        member.setEmail(email);
        member.setLoginacct(mobile);
        member.setUsername("会员");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(memberRegistVo.getPassword());
        member.setUserpswd(encode);
        member.setAccttype(AcctTypeEnum.PERSON.getCode());
        member.setAuthstatus(AuthStatusEnum.UNAUTH.getCode());
        member.setUsertype(UserTypeEnum.PERSON.getCode());
        member.setRealname("xxxx");

        memberMapper.insertSelective(member);

    }

    @Override
    public TMember login(String loginacct, String userpswd) {
        TMemberExample example = new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(loginacct);

        List<TMember> tMembers = memberMapper.selectByExample(example);
        if (tMembers!=null&&tMembers.size()==1){

            TMember member = tMembers.get(0);

            //匹配密码 如果密码相同 则返回 否则 返回null
            String password = member.getUserpswd();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean matches = passwordEncoder.matches(userpswd, password);
            if(matches){
                return  member;
            }else {
                return null;
            }
        }else {
            return null;
        }

    }

    //根据loginacct Email查出Member 如查出来则说明 已存在 不能进行注册
    public boolean checkLoginAcct(String loginacct){
        TMemberExample example = new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(loginacct);
        long count = memberMapper.countByExample(example);
        return  count>0?true:false;
    }

    public boolean checkEmail(String email){
        TMemberExample example = new TMemberExample();
        example.createCriteria().andEmailEqualTo(email);
        long count = memberMapper.countByExample(example);
        return  count>0?true:false;
    }


}
