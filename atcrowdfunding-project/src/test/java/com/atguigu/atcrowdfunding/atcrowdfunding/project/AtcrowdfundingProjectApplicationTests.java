package com.atguigu.atcrowdfunding.atcrowdfunding.project;

import com.aliyun.oss.OSSClient;
import com.atguigu.atcrowdfunding.atcrowdfunding.project.component.OssTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtcrowdfundingProjectApplicationTests {

    @Autowired
    OssTemplate ossTemplate;


    //文件流的方式
    @Test
    public void testOssTemplate() throws FileNotFoundException {

        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "LTAIAZYtHlm4Aae3";
        String accessKeySecret = "ZezKNY1KVdkziwaKYvVUH5GlDuyzNE";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = new FileInputStream("D:\\其他\\桌面壁纸\\1.jpg");
        ossClient.putObject("atcrowdfunding-chen", "1.jpg", inputStream);
        ossClient.shutdown();
    }

    //byte数组
    @Test
    public void contextLoads() throws IOException {
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "LTAIAZYtHlm4Aae3";
        String accessKeySecret = "ZezKNY1KVdkziwaKYvVUH5GlDuyzNE";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = new FileInputStream("D:\\其他\\桌面壁纸\\2.jpg");
        byte[] content = new byte[inputStream.available()];
        int read = inputStream.read(content);
        ossClient.putObject("atcrowdfunding-chen", "cccccc.jpg", new ByteArrayInputStream(content));
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //调用写的工具类进行测试
    @Test
    public void testOssUtil() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\其他\\桌面壁纸\\23.jpg");
        byte[] content = new byte[fileInputStream.available()];
        int read = fileInputStream.read(content);

        String url = ossTemplate.upload(content, "helloworld23.jpg");
        System.out.println("上传成功"+url);
    }

}
