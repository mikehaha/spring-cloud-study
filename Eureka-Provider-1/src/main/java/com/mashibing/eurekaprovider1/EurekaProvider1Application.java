package com.mashibing.eurekaprovider1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class EurekaProvider1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProvider1Application.class, args);
    }



}
