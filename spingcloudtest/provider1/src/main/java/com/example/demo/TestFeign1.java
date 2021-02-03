package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TODO
 *
 * @author Zangy
 * @version 1.0
 * @date 2021/1/25 0025 上午 9:18
 */
@FeignClient(name="test1",url="https://www.baidu.com/")
public interface TestFeign1 {

    @GetMapping
    String test();
}
