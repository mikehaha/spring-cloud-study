//package com.mashibing.eurekaconsumer;
//
//import com.mashibing.eurekaconsumer.bean.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URI;
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//public class MainController3 {
//
//
//
//    //      /User          资源 事先定义
//
//    /*
//     *     http://xxx/User
//     *
//     *
//     *     http://xxx/User/getUserList  Get
//     *     http://xxx/users  	        Get   约定  像对于到数据的一张表
//     *     http://xxx/v1/User/getUserList
//     *
//     * 	   http://xxx/v1/User/deleteById     Get/Post
//     *
//     * 		http://xxx/v1/users/1             Get= 获取id=1的这个用户  Delete请求 = 删除 put=修改
//     * 	   http://xxx/v2/users/1
//     *
//     *     针对单表 不再重复crud SpringData Rest
//     *
//     *
//     */
//
//
//    @Autowired
//    // 抽象
//    DiscoveryClient client;
//
////	@Autowired
////	EurekaClient client2;
//
//    @Autowired
//    LoadBalancerClient lb;
//
//
//
//    @Autowired
//    RestTemplate restTemplate;
//
//
//
//    /**
//     * 如果单独使用服务名调用的时候，需要在启动类的 添加注解 @LoadBalanced,
//     * 如果采用拼接URL的方式，就不需要添加 @LoadBalanced。
//     *
//     *    @Bean
//     *     @LoadBalanced
//     *     RestTemplate restTemplate(){
//     *         return new RestTemplate();
//     *     }
//     *
//     * @return
//     */
//    @GetMapping("/client10")
//    public Object client10() {
//        // 自动处理URL,Provider 就是服务名称
//        String url ="http://provider/getHi";
//
//        String respStr = restTemplate.getForObject(url, String.class);
//        System.out.println("respsstr="+respStr);
//        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
//
//        System.out.println(respStr);
//        System.out.println("forEntity="+forEntity);
//        return respStr;
//    }
//
//    @GetMapping("/client11")
//    public Object client11() {
//        // 自动处理URL,Provider 就是服务名称
//        String url ="http://provider/getMap";
//        System.out.println(url);
//
//        String respStr = restTemplate.getForObject(url, String.class);
//        System.out.println("forEntity="+respStr);
//        return respStr;
//    }
//
//    // 添加参数的形式，注意Person.class 需要与 Provider 的字段一模一样
//    // 占位符的形式 {1}
//    @GetMapping("/client12")
//    public Object client12() {
//        // 自动处理URL,Provider 就是服务名称
//        String url ="http://provider/getPerson2?name={1}";
//        System.out.println(url);
//        Person person = restTemplate.getForObject(url, Person.class,"豆丁");
//        System.out.println("forEntity="+person);
//        return person;
//    }
//
//    // 使用Map 的形式，需要确认 {name} 中的名字与 map 中的 key 一致才可以。
//    @GetMapping("/client14")
//    public Object client14() {
//        // 自动处理URL,Provider 就是服务名称
//        String url ="http://provider/getPerson2?name={name}";
//
//        Map<String, String> stringStringMap = Collections.singletonMap("name", "苑贺强");
//        System.out.println(url);
//        Person person = restTemplate.getForObject(url,Person.class,stringStringMap);
//        System.out.println("forEntity="+person);
//        return person;
//    }
//
//    // 使用Map 的形式，需要确认 {name} 中的名字与 map 中的 key 一致才可以。
//    @GetMapping("/client15")
//    public Object client15() {
//        // 自动处理URL,Provider 就是服务名称
//        String url ="http://provider/getPerson2";
//
//        Map<String, String> stringStringMap = Collections.singletonMap("name", "苑贺强");
//        System.out.println(url);
//        Person person = restTemplate.postForObject(url,"name=abc",Person.class);
//        System.out.println("forEntity="+person);
//        return person;
//    }
//
//    @GetMapping("/postPosition")
//    public Object postPosition(HttpServletResponse response) throws IOException {
//        // 自动处理URL,Provider 就是服务名称
//        String url ="http://provider/postPosition";
//
//        Map<String, String> stringStringMap = Collections.singletonMap("name", "msb");
//        System.out.println(url);
//        URI uri=restTemplate.postForLocation(url,stringStringMap,Person.class);
//        response.sendRedirect(uri.toURL().toString());
//        return null;
//    }
//}
