package com.mashibing.userconsumer;

import com.mashibing.userapi.Person;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
 *  2022年1月3日14:19:50
 * 1. 将当前类纳入spring 管理，使用@Component
 * 2. 实现接口 FallBackFacotry<>，并实现 Create 方法，参数 Throwable 是远端API 或本地API 返回的错误信息
 *      2.1 在Create 方法中，创建每个接口，并有实现。
 *      2.2 可以根据逻辑判断
 */
@Component
public class UserProviderBackFactory implements FallbackFactory<ConsumerAPI> {
    @Override
    public ConsumerAPI create(Throwable cause) {
        return new ConsumerAPI() {
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
             *
             * @return
             */

            @Override
            public String alive() {
                System.out.println("-------------------------------");
//                System.out.println(ToStringBuilder.reflectionToString(cause));
                String localizedMessage=null;
                String exceptionInfo = null;
                String message=null;
                try {
                    cause.printStackTrace();
                    if(cause instanceof ArithmeticException){
                        exceptionInfo="ArithmeticException 调用异常了";
                    }
                    localizedMessage = cause.getLocalizedMessage();
                    message = cause.getMessage();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return exceptionInfo+"\n \r UserProviderBackFactory-->alive-->降级了, localizedMessage="+localizedMessage+"----,message="+message;
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
        };
    }
}
