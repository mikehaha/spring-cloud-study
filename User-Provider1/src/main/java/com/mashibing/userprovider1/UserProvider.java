package com.mashibing.userprovider1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProvider {

    @Value("${server.port}")
    String port;

    @RequestMapping("/alive")
    public String alive(){
        System.out.println("调用 UserProvider--> alive");
        return "ok "+port;
    }

}
