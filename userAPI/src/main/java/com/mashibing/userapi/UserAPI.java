package com.mashibing.userapi;

import org.springframework.web.bind.annotation.*;

/**
 *
 * 因为这个接口被ConsumerAPI 接口实现了，且ConsumerAPI接口有 @FeignClient, 默认此接口同样有了Feign的功能
 */

public interface UserAPI {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/getScore")
    public String getScore();

    @GetMapping("/addOil")
    public String addOil();

    @GetMapping("/goHome") //  @RequestParam 适用于拼接URL 使用
    public String goHome(@RequestParam(name="name") String name);

    @GetMapping("/getMap")
    public String getMap(@RequestParam(name="name") String name);


}
