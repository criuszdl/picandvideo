package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ZDL
 * @ClassName FileDownloadController
 * @description: 文件下载控制器
 * @datetime 2022年 07月 28日 23:18
 * @version: 1.0
 */
@RestController
public class FileDownloadController {

    @Value("${win.base-path.photo}")
    private String photoPath;

    @Value("${win.base-path.video}")
    private String videoPath;

    /**
    * @Description 图片下载
    * @param fileName
    * @param response
    * @return void
    */
    @GetMapping("download/photo")
    public void downloadPhoto(String fileName, HttpServletResponse response) throws IOException {
        download(fileName, response, photoPath);
    }

    /**
     * @Description 视频下载
     * @param fileName
     * @param response
     * @return void
     */
    @GetMapping("download/video")
    public void downloadVideo(String fileName, HttpServletResponse response) throws IOException {
        download(fileName, response, videoPath);
    }

    private void download(String fileName, HttpServletResponse response, String photoPath) throws IOException {
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            // 获得待下载文件所在文件夹的绝对路径
            String realPath = System.getProperty("user.dir") + photoPath;
            System.out.println(realPath);
            // 获得文件输入流
            inputStream = new FileInputStream(new File(realPath, fileName));
            // 设置响应头、以附件形式打开文件  /  当不设置时候，为不用下载 - 即在线预览。
            response.setHeader("content-type","application/octet-stream");
            response.setHeader("content-disposition", "attachment; fileName=" + fileName);
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
        } catch (IOException e) {

        } finally {
            assert outputStream != null;
            outputStream.close();
            inputStream.close();
        }
    }

}
