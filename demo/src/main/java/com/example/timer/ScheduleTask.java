package com.example.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Zangy
 * 定时任务
 */
/*@Component*/
public class ScheduleTask implements InitializingBean {
	
	@Override
	public void afterPropertiesSet() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/DD hh:mm:ss");
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(new Date());
		/*calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 10);
		calendar.set(Calendar.MINUTE,40);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_YEAR, 1);*/
		calendar.add(Calendar.MINUTE, 1);
		Date startime = calendar.getTime();
		System.out.println(format.format(startime));
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				System.out.println(format.format(new Date()));
			}}, startime, 1000);
	}

}
