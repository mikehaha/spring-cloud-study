package com.mashibing.userprovider;

import com.mashibing.userapi.StudentAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProvider implements StudentAPI {

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

    @Override
    public String goHome(String name) {
        return  name+" 放学回家";
    }


}
