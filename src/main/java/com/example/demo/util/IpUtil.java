package com.example.demo.util;

import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author ZDL
 * @ClassName IpUtil
 * @description: TODO
 * @datetime 2022年 07月 31日 15:02
 * @version: 1.0
 */
public class IpUtil {

    @Resource
    Environment environment;

    /**
    * @Description 获取ip
    * @param
    * @return java.lang.String
    */
    public String getIp() {
        String address = null;
        try {
            address = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
    * @Description 获取端口号
    * @param
    * @return java.lang.String
    */
    public String getPort(){
        String port ="";
        try {
            port = environment.getProperty("server.port");
        } catch (Exception e) {
        }
        return port;
    }

}
