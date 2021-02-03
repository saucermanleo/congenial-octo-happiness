package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author Zangy
 * @version 1.0
 * @date 2021/1/20 0020 上午 9:33
 */
@Service
public class HystrixTestService {

    @HystrixCommand(fallbackMethod = "back")
    public String test(){

        //这里可以写ribioon的逻辑
        int i = 1/0;
        return "ok";
    }

    public String back(){
        return "降级了,备用方案";
    }
}
