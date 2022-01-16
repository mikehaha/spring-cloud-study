package com.mashibing.userconsumer;

import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients    // 打开Feign 的客户端功能，多个客户端
@EnableHystrix // 打开断路器 for Hystrix,Feign 是自带断路器,这个注解包含了 @EnableCircuitBreaker
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
    @LoadBalanced // restTemplate 的负载均衡策略，通过restTemplate调用Eureka中的服务名称，来动态调用服务
    RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
