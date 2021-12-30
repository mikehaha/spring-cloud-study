package com.mashibing.eurekaprovider1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Date;

@RestController
public class MainController {



    @Value("${server.port}")
    String port;

    @GetMapping("/getHi")
    public String getHi(){
        return " 我是 hi哦 ,我的端口号是"+port+"-------"+new Date();
    }

    @GetMapping("/getPerson")
    public Person getPerson(){


        return  new Person("1","yhq-1");


    }

    @PostMapping("/postParam")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws Exception { //
        System.out.println("-----------------------");
        System.out.println(person.getName().toString());
        URI uri = new URI("https://www.baidu.com/s?wd=" + person.getName().toString());
        response.addHeader("location", uri.toString());
        return uri;
    }

 }
