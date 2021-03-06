package com.mashibing.userconsumer;

import com.mashibing.userapi.Person;
import com.mashibing.userapi.UserAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 直接使用下面这种方式，也可以调用
 *  @FeignClient(name = "abc",url = "http://localhost:7002")
 *
 * 直接使用服务名称，也可以调用，使用服务名称，在Eureka 中找到对应的服务名称进行调用，服务名对动态掉
 * @FeignClient(value = "User-Provider")
 *
 *
 * 调用下面的接口是，会自动有 负载均衡的节奏
 *  需要启动user provider 和 userprovider1
 * http://localhost:7003/alive
 *
 */

//@FeignClient(name = "abc",url = "http://localhost:7002")
// fallback , 容错机制，是在客户端执行，与服务端无关，谁调用，谁负责，在 当前类和父类的所有方法，异常都会调用UserProviderBack中
//@FeignClient(name = "User-Provider",fallback = UserProviderBack.class)
@FeignClient(name = "User-Provider",fallbackFactory = UserProviderBackFactory.class)
public interface ConsumerAPI extends UserAPI {
    /**
     *
     * 1. http://localhost:7003/testLocal
     *   通过
     *
     * @return
     */
    @GetMapping("/testLocal")
    public String testLocal();

    @GetMapping("/map")
    public String map();

    /**
     *
     * getMap2 是给Feign使用
     * 传输参数时，必须使用 @RequestParam
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/getMap2")
    public Map<Integer,String> getMap2(@RequestParam("id") Integer id,@RequestParam("name") String name);

    @GetMapping("/getMap3")
    Map<String, String> getMap3(@RequestParam Map<String, String> map);

    /**
     *  2022年1月2日08:31:57
     * 下面的这些   @PostMapping("/postPerson")  和  @RequestBody Person person  主要是为了提供给Feign ，
     *  用于拼接请求的URL，提供给Feign 使用
     * @param person
     * @return
     */
    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person);
}
