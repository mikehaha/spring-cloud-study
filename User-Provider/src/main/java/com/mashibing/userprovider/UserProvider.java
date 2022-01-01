package com.mashibing.userprovider;

import com.mashibing.userapi.Person;
import com.mashibing.userapi.UserAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Override
    public Person postPerson(Person person) {
        Person p = new Person();
        p.setId(person.getId()+"--------");
        p.setName(person.getName()+"-----");
        return p;
    }

    @RequestMapping("/getMap2/{name}")
    public String getMap2(@PathVariable("name") String name){
        return  name+" get map";
    }

    @GetMapping("/testLocal")
    public String testLocal(){
        return  " this is testLocal, 测试端口"+port;
    }

    @GetMapping("/map")
    public String map(){
        return  " this is map, 测试端口"+port;
    }

    @GetMapping("/getMap2")
    public Map<Integer,String> getMap2(@RequestParam("id") Integer id, @RequestParam("name") String name){

        Map<Integer,String> map = new HashMap<Integer,String>();
        map.put(id,name);
        return map;
    }

    @GetMapping("/getMap3")
    public Map<String, String> getMap3(@RequestParam Map<String, String> map){
        return map;
    }
}