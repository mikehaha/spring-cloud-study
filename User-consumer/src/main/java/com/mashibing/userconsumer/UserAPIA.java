package com.mashibing.userconsumer;

import com.mashibing.userapi.StudentAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

@FeignClient(name = "User-Provider")
public interface UserAPIA  extends StudentAPI {


}
