package com.lzn.userservice;

import com.lzn.feign.clients.DietClient;
import com.lzn.feign.clients.DiseaseClient;
import com.lzn.feign.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: UserApplication
 * Description:
 *
 * @author lzn
 * @date 2023/05/28 20:58
 */
@SpringBootApplication
@EnableFeignClients(clients = {DietClient.class, DiseaseClient.class},defaultConfiguration = DefaultFeignConfiguration.class)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
