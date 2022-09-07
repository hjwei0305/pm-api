package com.donlim.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <strong>实现功能:</strong>
 * <p>REST服务主程序</p>
 *
 * @author 王锦光 wangj
 * @version 1.0.1 2019-12-18 10:41
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.donlim.pm.service","com.changhong.sei.basic.api"})
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
