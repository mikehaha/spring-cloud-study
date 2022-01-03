package com.mashibing.userprovider1;

import com.mashibing.userapi.Person;
import com.mashibing.userapi.UserAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserProvider implements UserAPI {

    @Value("${server.port}")
    String port;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public String alive(){

        int a=1/0;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = atomicInteger.incrementAndGet();
        System.out.println("调用 UserProvider--> alive,第"+i+"次调用， 端口是="+port);
        return "ok "+port+" 第"+i+"次调用哦";
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



    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person) {
        Person p = new Person();
        p.setId(person.getId()+"--------我的端口是="+port);
        p.setName(person.getName()+"-----我的端口是="+port);
        return p;
    }
}