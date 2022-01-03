package com.mashibing.userconsumer;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    RestTemplate template;


    // 发起请求
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

}
