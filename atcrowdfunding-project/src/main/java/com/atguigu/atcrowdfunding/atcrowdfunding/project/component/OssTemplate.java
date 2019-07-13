package com.atguigu.atcrowdfunding.atcrowdfunding.project.component;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author changchen
 * @create 2019-06-18 下午 5:28
 */

@ConfigurationProperties(prefix = "aliyun.oss")
@Slf4j
@Configuration
@Data
public class OssTemplate {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String upload(byte[] content,String fileName) throws FileNotFoundException {

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        fileName = UUID.randomUUID().toString().replace("-","")+"_"+fileName;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromat = format.format(new Date());

        ossClient.putObject(bucketName, "img/"+dateFromat+"/"+fileName, new ByteArrayInputStream(content));
        // 关闭OSSClient。
        ossClient.shutdown();
        //https://atcrowdfunding-chen.oss-cn-shanghai.aliyuncs.com/9.jpg
        String url = "https://"+bucketName+"."+endpoint.substring(7)+"/img/"+dateFromat+"/"+fileName;

        return url;
    }
}
