package com.mashibing.userapi;

import org.springframework.web.bind.annotation.*;


public interface UserAPI {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/getScore")
    public String getScore();

    @GetMapping("/addOil")
    public String addOil();

    @GetMapping("/goHome")
    public String goHome(@RequestParam(name="name") String name);

    @GetMapping("/getMap")
    public String getMap(@RequestParam(name="name") String name);

    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person);

}
