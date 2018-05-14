package com.zy.pmk.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.Test;

public class TestLocalDateTime {
	
	//得到当前时间
	LocalDate today = LocalDate.now();
	LocalDateTime time = LocalDateTime.now();
	//生成特定日期
	LocalDate myBirthday = LocalDate.of(1990, 01, 9);
	
	DateTimeFormatter datetimeFormate =DateTimeFormatter.ofPattern("yyyy MM dd - HH mm ss");
	
	@Test
	public void testLocalDate() {
		System.out.println("转换时间为特定格式的String");
		String time1 = time.format(DateTimeFormatter.BASIC_ISO_DATE);
		time1 = time.format(DateTimeFormatter.ISO_DATE);
		System.out.println(time1);
		System.out.println("获取年月日信息");
		System.out.println(today.getDayOfWeek().getValue());
		System.out.println(today.getDayOfMonth());
		System.out.println(today.getDayOfYear());
		System.out.println(today.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CHINA));
		
		System.out.println(myBirthday);
		System.out.println("判断两个日期是否相等");
		System.out.println(time.toLocalDate().equals(today));
		
	}
	
}
