package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云OSS工具类
 */
@Component
public class AliOSSUtils {
    /**
     * 配置基本参数：endpoint , bucketName , AccessKeyId , AccessSecret
     */
    private String endpoint = "https://oss-cn-hongkong.aliyuncs.com";
    private String bucketName = "sample-of-tlias";
    private String AccessKeyId = "LTAI5tHxNnHANQCmDdwRmRP7";
    private String AccessSecret = "zmGH8iQXo9hQpOxERccTC813UgKPAc";

    /**
     * 实现文件上传功能
     * 并返回url
     */
    public String upload(MultipartFile file) throws IOException {
        //获取文件上传的输入流
        InputStream inputStream  = file.getInputStream();

        //构造唯一的文件名
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传到OSS
        OSS oss = new OSSClientBuilder().build(endpoint , AccessKeyId , AccessSecret);
        oss.putObject(bucketName ,  fileName , inputStream);

        //返回文件访问的url
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        //关闭OSS
        oss.shutdown();
        return url;
    }

}
