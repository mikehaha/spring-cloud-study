package com.mashibing.userprovider1;

import com.mashibing.userapi.Person;
import com.mashibing.userapi.UserAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserProvider implements UserAPI {

    @Value("${server.port}")
    String port;

    public String alive(){
        System.out.println("调用 UserProvider--> alive");
        return "ok "+port;
    }

    public String getScore() {
        return "100";
    }

    @Override
    public String addOil() {
        return "addOil";
    }

    // http://localhost:7003/goHome?name=mike
    @Override
    public String goHome(String name) {
        return  name+" 放学回家";
    }

    @RequestMapping("/getMap")
    public String getMap(@RequestParam("name") String name){
        return  name+" get map";
    }




    @RequestMapping("/getMap2/{name}")
    public String getMap2(@PathVariable("name") String name){
        return  name+" get map";
    }

    @GetMapping("/testLocal")
    public String testLocal(){
        return  " this is testLocal, 测试端口"+port;
    }


    @Override
    public Person postPerson(Person map) {



        return null;
    }


}