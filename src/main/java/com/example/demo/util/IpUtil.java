package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author ZDL
 * @ClassName IpUtil
 * @datetime 2022年 07月 31日 15:02
 * @version: 1.0
 */
public class IpUtil {

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

}
