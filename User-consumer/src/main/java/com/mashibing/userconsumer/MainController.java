package com.mashibing.userconsumer;

import com.mashibing.userapi.Person;
import com.mashibing.userapi.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MainController implements UserAPI {

    @Autowired
    ConsumerAPI consumerAPI;

    //-- http://localhost:7003/alive
    public String alive(){
        return  consumerAPI.alive();
    }

    public String getScore() {

        return consumerAPI.getScore();
    }

    @Override
    public String addOil() {
        System.out.println("addOil--------------");
        return consumerAPI.addOil();
    }

    @Override
    public String goHome( String name) {
        return consumerAPI.goHome(name);
    }

    @Override
    public String getMap(String name) {
        return consumerAPI.getMap(name);
    }

    //http://localhost:7003/testLocal
    @GetMapping("/testLocal")
    public String testLocal() {
        return consumerAPI.testLocal();
    }

    @GetMapping("/map")
    public String map() {
        return consumerAPI.map();
    }

    /**
     *  URL: http://localhost:7003/getMap2?id=1&name=mike
     *  返回结果  {"1":"mike"}
     *
     *  在 getMap2 的接口中使用 参数  @RequestParam
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/getMap2")
    public Map<Integer, String> map(Integer id, String name) {
        return consumerAPI.getMap2(id,name);
    }

    /**
     * @Date: 2022年1月1日22:10:35
     *  URL: http://localhost:7003/getMap3?name=yhq&age=18&color=color&price=12
     *  返回结果  {"name":"yhq","age":"18","color":"color","price":"12"}
     *  注意输入的参数中，name 不要有个重复的参数，有重复的就会覆盖
     *  注意 Map<String,String>, Map 中的 key 需要是 String 类型，不要用 Integer 类型
     * @param
     * @param
     * @return
     */
    @GetMapping("/getMap3")
    public Map<String, String> getMap3(@RequestParam Map map) { // Map<Integer, String>
        return consumerAPI.getMap3(map);
    }

    /**
     *
     *  URL: http://localhost:7003/postPerson?name=mike&id=1
     *  返回记录：  {"id":"1--------我的端口是=7004","name":"mike-----我的端口是=7004"}
     * @param
     * @param map
     * @return
     */
    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map map){
        String id = map.get("id").toString();
        String name = map.get("name").toString();
        Person person = new Person(id, name);
        return consumerAPI.postPerson(person);
//        return person;
    }


}
