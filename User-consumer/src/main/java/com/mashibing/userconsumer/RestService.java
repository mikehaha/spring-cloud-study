package com.mashibing.userconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
@RestController
public class RestService {

    @Autowired
    RestTemplate template;


    /**
     *  fallbackMethod 失败时调用的方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "back")
    public String alive() {
        String url="http://User-Provider/alive";
        String str = template.getForObject(url, String.class);
        System.out.println("str="+str);
        return str;
    }

    public String back(){
        return "请求失败，呵呵，我是请求失败之后的调用的 fallbackMethod=back 方法";
    }


    /**
     *   URL: http://localhost:7003/testFallback
     *   如果成功： ok 7002 第19次调用哦
     *   如果失败： 会调用 方法：testFallbackMethod(), 并且显示  testFallback--请求失败，呵呵，我是请求失败之后的调用的 fallbackMethod=back 方法
     * @return
     */
    @GetMapping("/testFallback")
    @HystrixCommand(fallbackMethod = "testFallbackMethod")
    public String testFallback() {
        String url="http://User-Provider/alive";
        String str = template.getForObject(url, String.class);
        System.out.println("str="+str);
        return str;
    }

    public String testFallbackMethod(){
        return "testFallback--请求失败，呵呵，我是请求失败之后的调用的 fallbackMethod=back 方法";
    }
}
