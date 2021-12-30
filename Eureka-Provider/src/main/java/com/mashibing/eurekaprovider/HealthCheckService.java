package com.mashibing.eurekaprovider;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService implements HealthIndicator {
    public boolean status= true;

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        if(status){
            System.out.println("检查检查="+status);
            return  new Health.Builder().up().build();
        }else {
            System.out.println("检查检查="+status);
            return  new Health.Builder().down().build();
        }
    }
}
