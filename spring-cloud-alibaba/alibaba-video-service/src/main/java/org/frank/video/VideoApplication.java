package org.frank.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("org.frank.video.repositories")
@EnableDiscoveryClient // 开启服务发现
@EnableFeignClients  // 开启feign 支持
public class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}
