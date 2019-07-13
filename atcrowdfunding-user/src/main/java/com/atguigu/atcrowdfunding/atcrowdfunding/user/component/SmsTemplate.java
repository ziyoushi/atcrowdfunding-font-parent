package com.atguigu.atcrowdfunding.atcrowdfunding.user.component;

import com.atguigu.atcrowdfunding.atcrowdfunding.user.utils.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author changchen
 * @create 2019-06-17 下午 9:26
 * 配置发送短信的参数
 */
@ConfigurationProperties(prefix = "aliyun.sms")
@Component
@Data
@Slf4j
public class SmsTemplate {

    private  String host;
    private  String path;
    private  String method;
    private  String appCode;

    public  boolean sendCodeSms(String mobile,String code){
        log.debug("短信的配置信息：host={}，path={}。method={}。appcode={}",host,path,method,appCode);

        log.debug("给手机号：{}；发送了一个【{}】验证码",mobile,code);
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("phone", mobile);
        querys.put("templateId", "TP18040314");
        querys.put("variable", "code:"+code);
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode==200){
                System.out.println("短信发送成功");
                return true;
            }

            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
