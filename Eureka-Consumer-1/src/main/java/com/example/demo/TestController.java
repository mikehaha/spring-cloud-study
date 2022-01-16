package com.example.demo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    // 自动注入，获取到服务器的注册信息
    @Autowired
    DiscoveryClient client;
    //netflix 原生的类
    @Autowired
    EurekaClient client2;
//    //0000000000000000000000000000000000000

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    //_____________________________________________________-
    /**
     * // http://localhost:1801/getServices
     *
     *  已经注册的服务包含eurekaserver;provider;consumer;
     *  使用 DiscoveryClient 自动注入，调用Eureka 服务中已经注册了哪些服务内容。
     * @return
     *
     */
    @RequestMapping("/getServices")
    public String getServices(){
        StringBuilder stringBuilder = new StringBuilder("已经注册的服务包含");
        for (String service : client.getServices()) {
            stringBuilder.append(service+";");
            System.out.println("service= "+service);
        }
        return stringBuilder.toString();
    }

    // 获取单个服务

    /**
     * URL: http://localhost:1801/getService
     *  返回单个服务的实例信息，这个是返回了所有名字为 PROVIDER 的实例信息集合，
     * @return
     */
    @RequestMapping("/getService")
    public Object getService(){
        List<ServiceInstance> instances = client.getInstances("PROVIDER");
        return instances;
    }

    // 获取单个服务

    /**
     *  client2 是 EurekaClient 获取服务
     *  获取到实例之后，然后根据实例拼接处URL, 加上使用 restTemplate 来实现调用服务，
     *  会调用2个现在已经注册的 PROVIDER
     *  http://localhost:1801/getURL
     *
     *  null 我是 hi哦 ,我的端口号是90-------Sun Jan 16 21:46:22 CST 2022 我是 hi哦 ,我的端口号是100-------Sun Jan 16 21:46:22 CST 2022
     *
     * @return
     */
    @RequestMapping("/getURL")
    public Object getURL(){
        List<InstanceInfo> instanceInfoList = client2.getInstancesByVipAddress("provider", false);
        String url = null;
        String result=null;
        for (InstanceInfo instanceInfo : instanceInfoList) {
            System.out.println("instanceinfo="+instanceInfo);
            if(instanceInfo.getStatus()== InstanceInfo.InstanceStatus.UP){
                String hostName=instanceInfo.getHostName();
                url= "http://"+hostName+":"+instanceInfo.getPort()+"/getHi";
                System.out.println("url="+url);
            }

            RestTemplate restTemplate = new RestTemplate();
            result += restTemplate.getForObject(url, String.class);
        }
        return result;
    }

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

    //   http://localhost:1801/loadBalancer2
    // 在项目中多创建1个 provider 项目，会发现得到的URL回轮询切换IP地址和端口号，证明负载均衡生效了

    /**
     *  http://localhost:1801/loadBalancer2
     *  如下如果是对于单个的服务调用，需要创建 Restemplate 对象，然后调用 拼接的URL, 这个是适用于调用单个服务的，即 host + port / 服务名
     * @return
     */
    @GetMapping("/loadBalancer2")
    public String loadBalancerClientTest2(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/getHi";
        RestTemplate restTemplate = new RestTemplate();
        String forObject ="请求返回的结果是="+ restTemplate.getForObject(url, String.class);
        return "请求的URL 是"+url+"\n ; 返回结果是"+forObject;
    }

    // 通过服务名称，来获取到服务实例，加上负载均衡测试，会轮训调用

    /**
     *  http://localhost:1801/loadBalancer3
     *  下面使用的是 系统的 restTemplate， 带有注解 @LoadBalanced 的
     *      @Bean
     *     @LoadBalanced
     *     RestTemplate getRestTemplate(){
     *         RestTemplate restTemplate =new RestTemplate();
     *         // 添加拦截器，用于拦截http 的请求
     * //        restTemplate.getInterceptors().add(new LoggingInterceptor());
     *         return restTemplate;
     *     }
     *
     * @return
     */
    @RequestMapping("/loadBalancer3")
    public String loadBalancerClientTest3(){
        String url="http://provider/getHi";
        String forObject1 = restTemplate.getForObject(url, String.class);

        return "请求的URL 是"+url+"\n ; 返回结果是"+forObject1;
    }

    // 通过服务名称，来获取到服务实例，加上负载均衡测试，会轮训调用

    /**
     * http://localhost:1801/getPerson
     * 请求的URL 是http://provider/getPerson ; 返回结果是Person{id='1', name='yhq-1'}
     * @return
     */
    @RequestMapping("/getPerson")
    public String loadBalancerClientTest4(){
        String url="http://provider/getPerson";

        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity(url, Person.class);
        Person body = personResponseEntity.getBody();
        return "请求的URL 是"+url+"\n ; 返回结果是"+body.toString();
    }

    // 通过服务名称，来获取到服务实例，加上负载均衡测试，会轮训调用

    /**
     * http://localhost:1801/postParam
     * 会自动跳转到  https://www.baidu.com/s?wd=abc
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/postParam")
    public Object postParam(HttpServletResponse response) throws IOException{
        String url="http://provider/postParam";
        Map<String, String> map = Collections.singletonMap("name", "abc");
        URI location = restTemplate.postForLocation(url, map, Person.class);
        response.sendRedirect(location.toURL().toString());
        return null;
    }
}
