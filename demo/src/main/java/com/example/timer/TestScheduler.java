package com.example.timer;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
	@Scheduled(fixedRate = 1000)
	public void test() {
		System.out.println(new Date());
	}
}
