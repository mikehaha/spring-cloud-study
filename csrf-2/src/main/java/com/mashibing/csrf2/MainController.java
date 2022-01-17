package com.mashibing.csrf2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/list")
    public String list(){

        return "list";
    }
}
