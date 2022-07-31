package com.example.demo.controller;

import com.example.demo.util.IpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
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
public class FilePreviewController {

    @Value("${win.base-path.photo}")
    private String photoPath;

    @Value("${win.base-path.video}")
    private String videoPath;

    @Value("${server.port}")
    private String port;

    /**
    * @Description 通过返回流的形式预览图片
    * @param fileName
    * @param response
    * @return void
    */
    @GetMapping("preview/photo")
    public void previewPhoto(String fileName, HttpServletResponse response) throws IOException {
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            // 获得待下载文件所在文件夹的绝对路径
            String realPath = System.getProperty("user.dir") + photoPath;
            System.out.println(realPath);
            // 获得文件输入流
            inputStream = new FileInputStream(new File(realPath, fileName));
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert outputStream != null;
            outputStream.close();
            inputStream.close();
        }
    }

    /**
     * @Description 通过返回路径的形式预览图片
     * @param fileName
     * @return void
     */
    @GetMapping("preview/photo/2")
    public String previewPhoto2(@RequestParam("fileName") String fileName) {
        // 获得待下载文件所在文件夹的绝对路径
        String realPath = System.getProperty("user.dir") + photoPath;
        System.out.println(realPath);
        //获取当前ip
        IpUtil ipUtil = new IpUtil();
        System.out.println(ipUtil.getIp());
        System.out.println(ipUtil.getPort());
        //获取端口号
        //查询当前地址下的文件
        String ultimatePath = ipUtil.getIp() +":"+ port + "/static/" + fileName;
        //拼接返回地址
        return ultimatePath;
    }

    /**
     * @Description 预览视频，前端通过video标签解析。
     * @param fileName
     * @param response
     * @return void
     */
    @GetMapping("preview/video")
    public void previewVideo(String fileName, HttpServletResponse response) throws IOException {
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        ByteArrayOutputStream bos = null;
        try {
            // 获得待下载文件所在文件夹的绝对路径
            String realPath = System.getProperty("user.dir") + videoPath;
            System.out.println(realPath);
            // 获得文件输入流
            inputStream = new FileInputStream(new File(realPath, fileName));
            bos = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(data)) != -1) {
                bos.write(data, 0, len);
            }
            byte[] buffer = bos.toByteArray();
            response.setContentType("application/octet-stream");
            response.setHeader("Accept-Ranges", "bytes");
            response.setContentLength(buffer.length);
            response.getOutputStream().write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * @Description 预览视频2，前端通过video标签解析。
     * @param fileName
     * @param response
     * @return void
     */
    @GetMapping("preview/video/2")
    public void previewVideo2(String fileName, HttpServletResponse response) throws IOException {
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            // 获得待下载文件所在文件夹的绝对路径
            String realPath = System.getProperty("user.dir") + videoPath;
            System.out.println(realPath);
            // 获得文件输入流
            inputStream = new FileInputStream(new File(realPath, fileName));
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert outputStream != null;
            outputStream.close();
            inputStream.close();
        }
    }

}
