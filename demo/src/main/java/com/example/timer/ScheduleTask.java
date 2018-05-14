package com.example.timer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Zangy 定时任务
 */
 @Component 
public class ScheduleTask implements InitializingBean {

	@Override
	@Test
	public void afterPropertiesSet() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		/*
		 * calendar.set(Calendar.AM_PM, Calendar.PM); calendar.set(Calendar.HOUR, 10);
		 * calendar.set(Calendar.MINUTE,40); calendar.set(Calendar.SECOND, 0);
		 * calendar.add(Calendar.DAY_OF_YEAR, 1);
		 */
		calendar.add(Calendar.MINUTE, 1);
		//一分钟后启动
		Date startime = calendar.getTime();
		//一分钟后启动第二种实现
		LocalDateTime ltime1 = LocalDateTime.now().plusMinutes(1);
		startime = Date.from(ltime1.atZone(ZoneId.systemDefault()).toInstant());
		
		//设置为系统启动时间点的第二天早上8点启动
		LocalDate ltime = LocalDate.now().plusDays(1);
		LocalTime localtime = LocalTime.of(8, 0, 0);
		LocalDateTime time = ltime.atTime(localtime);
		startime = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
		
		System.out.println(format.format(startime));
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				System.out.println(format.format(new Date()));
			}
		}, startime, 1000);
	}

}
