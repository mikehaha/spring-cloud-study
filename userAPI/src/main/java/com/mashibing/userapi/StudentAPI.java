package com.mashibing.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RequestMapping("/student")
public interface StudentAPI {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/getScore")
    public String getScore();

    @GetMapping("/addOil")
    public String addOil();

    @GetMapping("/goHome")
    public String goHome(@RequestParam(name="name") String name);

}
