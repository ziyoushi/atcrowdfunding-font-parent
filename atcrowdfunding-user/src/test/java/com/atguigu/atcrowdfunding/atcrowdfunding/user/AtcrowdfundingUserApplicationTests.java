package com.atguigu.atcrowdfunding.atcrowdfunding.user;

import com.atguigu.atcrowdfunding.atcrowdfunding.user.component.SmsTemplate;
import com.atguigu.atcrowdfunding.atcrowdfunding.user.mapper.TMemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtcrowdfundingUserApplicationTests {

    @Autowired
    TMemberMapper memberMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    SmsTemplate smsTemplate;

    //测试发送短信
    @Test
    public void testSmsTemplate(){
        boolean bo = smsTemplate.sendCodeSms("18013001598","99999");
        System.out.println("短信发送成功："+bo);
    }

    //测试redis
    @Test
    public void testRedis(){

        ValueOperations<String, String> str = stringRedisTemplate.opsForValue();
         str.append("msg", "hello");
        String msg = str.get("msg");


        System.out.println("设置成功"+msg);

    }


    @Test
    public void testMember(){
        long count = memberMapper.countByExample(null);
        System.out.println("总的记录树"+count);

    }


}
