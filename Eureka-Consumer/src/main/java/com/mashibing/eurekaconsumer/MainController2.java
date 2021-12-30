package com.mashibing.eurekaconsumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

;

/**
 * @author  yhq
 * @Date  2021/12/25
 */
@RestController
public class MainController2 {

    @Autowired
    LoadBalancerClient loadBalancerClient;


    //http://localhost/loadBalancer   2021/12/26
    // 在项目中多创建1个 provider 项目，会发现得到的URL回轮询切换IP地址和端口号，证明负载均衡生效了
    @RequestMapping("/loadBalancerss2")
    public String loadBalancerClientTest(){
        for (int i=0;i<10;i++){
            ServiceInstance provider = loadBalancerClient.choose("provider");
            System.out.println(provider.getPort());
        }
        return "请求的URL 是";
    }


}
