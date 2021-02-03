package com.example.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthIndidator implements HealthIndicator {

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status = true;

    @Override
    public Health health() {
        if (status) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }


    }


}
