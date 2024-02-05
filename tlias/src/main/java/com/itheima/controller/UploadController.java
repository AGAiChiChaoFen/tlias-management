package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传接口
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    AliOSSUtils aliOSSUtils;

/*    @RequestMapping("/upload")
    public Result upload(String name , Integer age , MultipartFile image) throws IOException {
        log.info("文件上传：{}，{}，{}",name , age , image);

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一文件名（不能够重复） -->UUID
        int index = originalFilename.lastIndexOf(".");
        String externName = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + externName;

        image.transferTo(new File("F:\\image\\" + newFileName));

        return Result.success();
    }*/

    /**
     * 其实就是上传一个文件，通过这个方法获得他的访问url
     * 前端在新增员工时，先发送这个请求来获取url
     * 然后把url放在image中以字符串的方式发送给新增员工请求
     * @param image
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传：文件名：{}" , image.getOriginalFilename());

        String url = aliOSSUtils.upload(image);

        log.info("文件上传成功！url为：{}",url);

        return Result.success(url);
    }

}
