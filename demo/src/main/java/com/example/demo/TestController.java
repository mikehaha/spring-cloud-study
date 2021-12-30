package com.example.demo;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;



    @RequestMapping("/test")
    public String test(){

        for (int i=0;i<10;i++){
            ServiceInstance provider = loadBalancerClient.choose("provider");
            System.out.println(provider.getPort());
        }



        return "ddddddd";
    }

    //http://localhost/loadBalancer   2021/12/26
    // 在项目中多创建1个 provider 项目，会发现得到的URL回轮询切换IP地址和端口号，证明负载均衡生效了
    @GetMapping("/loadBalancerss")
    public String loadBalancerClientTest(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        int port = serviceInstance.getPort();
        String host = serviceInstance.getHost();
        String url="http://"+host+":"+port+"/getHi";
        RestTemplate restTemplate = new RestTemplate();
        String forObject ="请求返回的结果是="+ restTemplate.getForObject(url, String.class);
        return "请求的URL 是"+url+"\n ; 返回结果是"+forObject;
    }

    //   http://localhost/loadBalancer2   2021/12/26
    // 在项目中多创建1个 provider 项目，会发现得到的URL回轮询切换IP地址和端口号，证明负载均衡生效了
    @RequestMapping("/loadBalancer2")
    public String loadBalancerClientTest2(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/getHi";
        String forObject ="请求返回的结果是="+ restTemplate.getForObject(url, String.class);
        return "请求的URL 是"+url+"\n ; 返回结果是"+forObject;
    }

    // 通过服务名称，来获取到服务实例，加上负载均衡测试，会轮训调用
    @RequestMapping("/loadBalancer3")
    public String loadBalancerClientTest3(){
        String url="http://provider/getHi";

        String forObject1 = restTemplate.getForObject(url, String.class);

        return "请求的URL 是"+url+"\n ; 返回结果是"+forObject1;
    }

    // 通过服务名称，来获取到服务实例，加上负载均衡测试，会轮训调用
    @RequestMapping("/getPerson")
    public String loadBalancerClientTest4(){
        String url="http://provider/getPerson";

        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity(url, Person.class);
        Person body = personResponseEntity.getBody();
        return "请求的URL 是"+url+"\n ; 返回结果是"+body.toString();
    }

    // 通过服务名称，来获取到服务实例，加上负载均衡测试，会轮训调用
    @GetMapping("/postParam")
    public Object postParam(HttpServletResponse response) throws IOException{
        String url="http://provider/postParam";
        Map<String, String> map = Collections.singletonMap("name", "abc");
        URI location = restTemplate.postForLocation(url, map, Person.class);
        response.sendRedirect(location.toURL().toString());
        return null;
    }
}
