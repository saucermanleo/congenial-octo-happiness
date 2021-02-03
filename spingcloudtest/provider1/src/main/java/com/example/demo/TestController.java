package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    HealthIndidator healthIndidator;


    @Autowired
    HystrixTestService hystrixTestService;


    @Autowired
    TestFeign1 testFeign1;


    @GetMapping("/services")
    public List<String> getServices() {
        List<ServiceInstance> providers = discoveryClient.getInstances("provider");
        ServiceInstance provider1 = loadBalancerClient.choose("provider");
        System.out.println(provider1);
        for (ServiceInstance provider : providers) {
            Map<String, String> metadata = provider.getMetadata();
            System.out.println(metadata);
            System.out.println(provider);
        }
        return discoveryClient.getServices();
    }


    @GetMapping("/updown")
    public void updown() {
        healthIndidator.setStatus(!healthIndidator.isStatus());
    }


    @GetMapping("/test1")
    public String testHystrix() {
        return hystrixTestService.test();
    }

    @GetMapping("/test2")
    public String test2() {
        return testFeign1.test();
    }
}
