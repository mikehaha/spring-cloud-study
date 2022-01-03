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

    @Autowired
    RestService service;

    /**
     *  URL: http://localhost:7003/alive2
     *  请求这个接口，alive2,
     *      1. 如果调用到的服务正常相应，那么就会正常返回，
     *      2. 如果调用服务有问题，那么就会调用  RestService--》alive--》@HystrixCommand(fallbackMethod = "back") 的方法
     *
     * @return
     */
    @GetMapping("/alive2")
    public String alive2(){

        return  service.alive();
    }

    /**
     *  URL: http://localhost:7003/alive
     *  调用这个类，因为 consumerAPI 带有FeignClient 注解，会自动执行负载均衡，同时有 fallbackFactory，如果调用失败
     *  会使用失败的页面。
     * @return
     */
    public String alive(){

        return  consumerAPI.alive();
    }


    // http://localhost:7003/getScore
    public String getScore() {

        return consumerAPI.getScore();
    }

    //http://localhost:7003/addOil
    @Override
    public String addOil() {
        System.out.println("addOil--------------");
        return consumerAPI.addOil();
    }

    /**
     *  URL: http://localhost:7003/goHome?name=mike
     *  如果有请求参数，使用 ？name=mike
     * @param name
     * @return
     */
    public String goHome( String name) {
        return consumerAPI.goHome(name);
    }

    /**
     * URL: http://localhost:7003/getMap?name=mike
     * 返回值： mike get map
     * @param name
     * @return
     */
    @Override
    public String getMap(String name) {
        return consumerAPI.getMap(name);
    }

    /**
     *  URL: http://localhost:7003/testLocal
     *  返回值：this is testLocal, 测试端口7002
     * @return
     */
    @GetMapping("/testLocal")
    public String testLocal() {
        return consumerAPI.testLocal();
    }

    /**
     * http://localhost:7003/map
     * @return
     */
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
    public Map<Integer, String> map(@RequestParam("id") Integer id, @RequestParam("name") String name) {
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
