package com.mashibing.userconsumer;

import com.mashibing.userapi.StudentAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController implements StudentAPI {

    @Autowired
    UserAPIA userAPIA;

    //-- http://localhost:7003/alive
    public String alive(){
        return  userAPIA.alive();
    }

    public String getScore() {

        return userAPIA.getScore();
    }

    @Override
    public String addOil() {
        System.out.println("addOil--------------");
        return userAPIA.addOil();
    }

    @Override
    public String goHome( String name) {
        return userAPIA.goHome(name);
    }

}
