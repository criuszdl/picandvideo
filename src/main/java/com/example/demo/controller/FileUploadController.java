package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author ZDL
 * @ClassName FileUploadController
 * @description: 文件上传控制器
 * @datetime 2022年 07月 28日 23:18
 * @version: 1.0
 */

@RestController
public class FileUploadController {

    @Value("${win.base-path.photo}")
    private String photoPath;

    @Value("${win.base-path.video}")
    private String videoPath;

    /**
    * @Description MultipartFile 变量名要与 form 表单中的的 type="file" name="file" name 属性名一致
    * @param file
    * @param request
    * @return void
    */
    @PostMapping("upload")
    @ResponseBody
    public void upload(MultipartFile file, HttpServletRequest request) throws IOException {
        // 文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        if (originalFilename.isBlank()) {
            System.out.println("上传文件为空。");
            return;
        }
        // 文件类型
        System.out.println(file.getContentType());
        // 文件大小
        System.out.println(file.getSize());
        // 获得文件输入流
        System.out.println(file.getInputStream());
        // 获得web应用中 files文件夹的绝对路径
        String realPath = "";
        if (originalFilename.endsWith("mp4")) {
            realPath = System.getProperty("user.dir") + videoPath;
        }else {
            realPath = System.getProperty("user.dir") + photoPath;
        }

        System.out.println(realPath);
        File newFile = new File(realPath);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        // 上传
        file.transferTo(new File(newFile, originalFilename));
    }


}
