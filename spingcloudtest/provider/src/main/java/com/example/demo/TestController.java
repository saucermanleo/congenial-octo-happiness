package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author Zangy
 * @version 1.0
 * @date 2021/2/4 0004 下午 2:21
 */
@RestController
public class TestController {

    @Value("${test}")
    private String  name;


    @GetMapping
    public String test2() {
        return name;
    }

}
