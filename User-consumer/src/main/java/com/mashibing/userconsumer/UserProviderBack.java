package com.mashibing.userconsumer;

import com.mashibing.userapi.Person;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Date: 2022年1月3日07:09:48
 * 1. 需要调用统一的接口，针对不同的接口出现的错误分别判断，实现
 * 2. 需要打开  feign.circuitbreaker.enabled=true， 默认是false
 * 3. 在启动的时候，会报错找不到 UserProviderBack.class, 默认是单例的，需要加入到spring 管理中，使用@Component
 *      报错信息
 *       No fallback instance of type class com.mashibing.userconsumer.UserProviderBack found for feign client User-Provider
 *  4. 这种方式只能是对于单个类的降级，缺少逻辑判断。
 * */

@Component
public class UserProviderBack implements ConsumerAPI{
    @Override
    public String testLocal() {
        return null;
    }

    @Override
    public String map() {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<String, String> getMap3(Map<String, String> map) {
        return null;
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }

    /**
     * 1. 如果调用 alive() 接口失败，就会调用下面的方法
     *    根据自己的实际业务处理数据
     *
     * @return
     */
    @Override
    public String alive() {

        return "alive() 方法, 我被降级了---";
    }

    @Override
    public String getScore() {
        return null;
    }

    @Override
    public String addOil() {
        return null;
    }

    @Override
    public String goHome(String name) {
        return null;
    }

    @Override
    public String getMap(String name) {
        return null;
    }
}
