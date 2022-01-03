package com.mashibing.userconsumer;

import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker // 打开断路器 for Hystrix,Feign 是自带断路器，如果用Restemplate, 需要带上这个值
@EnableHystrixDashboard // 开启断路器的面板
public class UserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class, args);
    }

//    @Bean
//    Retryer feignRetryer() {
//        return  new Retryer.Default();
//    }

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
