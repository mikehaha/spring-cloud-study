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

/**
 * @author  yhq
 * @Date  2021/12/25
 */
@RestController
public class MainController {

    // 自动注入，获取到服务器的注册信息
    @Autowired
    DiscoveryClient client;
    //netflix 原生的类
    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getServices")
    public void getServices(){
        for (String service : client.getServices()) {
            System.out.println("service= "+service);
        }
    }

    // 获取单个服务
    @RequestMapping("/getService")
    public Object getService(){
        List<ServiceInstance> instances = client.getInstances("PROVIDER");
        return instances;
    }

    // 获取单个服务
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
            result = restTemplate.getForObject(url, String.class);
        }
        return result;
    }

    //http://localhost/loadBalancer   2021/12/26
    // 在项目中多创建1个 provider 项目，会发现得到的URL回轮询切换IP地址和端口号，证明负载均衡生效了
    @GetMapping("/loadBalancerss")
    public String loadBalancerClientTest(){
        ServiceInstance serviceInstance = lb.choose("provider");
        int port = serviceInstance.getPort();
        String host = serviceInstance.getHost();
        String url="http://"+host+":"+port+"/getHi";
        RestTemplate restTemplate = new RestTemplate();
        String forObject ="请求返回的结果是="+ restTemplate.getForObject(url, String.class);
        return "请求的URL 是"+url+"\n ; 返回结果是"+forObject;
    }

    @RequestMapping("/loadBalancer3")
    public String loadBalancerClientTest3(){
        String url ="http://provider/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr;
    }
}
