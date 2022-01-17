package com.mashibing.csrf1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/getHi")
    public String getHi(){
        System.out.println("我是调用了 getHi");
        return "hello";
    }
}
