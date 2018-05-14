package com.zy.pmk.LocalDateTime;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.Test;

public class TestLocalDateTime {

	// 得到当前时间
	LocalDate today = LocalDate.now();
	LocalDateTime time = LocalDateTime.now();
	ZonedDateTime zonetime = ZonedDateTime.now();
	OffsetTime offsettime = OffsetTime.now();
	OffsetDateTime odt = OffsetDateTime.now();
	Year y = Year.now();
	YearMonth ym = YearMonth.now();
	MonthDay md = MonthDay.now();
	ZoneOffset offset = ZoneOffset.of("+05:30");
	OffsetDateTime date = OffsetDateTime.of(time, offset);

	Instant instant = Instant.now();

	// 生成特定日期
	LocalDate myBirthday = LocalDate.of(1990, 01, 9);

	DateTimeFormatter datetimeFormate = DateTimeFormatter.ofPattern("yyyy MM dd - HH mm ss");
	//通过格式化生产时间
	LocalDateTime ldt = LocalDateTime.parse("2018 05 14 - 00 00 00", datetimeFormate);

	@Test
	public void testLocalDate() throws InterruptedException {
		//设置属性
		zonetime = zonetime.withDayOfMonth(4);
		
		//组装在一起
		LocalDate ltime = LocalDate.now().plusDays(1);
		LocalTime localtime = LocalTime.of(8, 0, 0);
		LocalDateTime time = ltime.atTime(localtime);
		
		System.out.println(zonetime);
		System.out.println(date);
		

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

		// 判断是不是同一个月的同一天
		MonthDay monthday = MonthDay.from(myBirthday);
		MonthDay md = MonthDay.of(1, 9);
		System.out.println(md.equals(monthday));

		// 在原有时间上增加
		time.plusHours(2);
		time.plus(1, ChronoUnit.HOURS);
		time.minusHours(2);
		time.plus(Duration.ofHours(2));
		time.plus(Period.ofDays(2));

		// 时钟对应当前时间,一只在变化
		Clock c = Clock.systemDefaultZone();
		System.out.println(c.millis());
		Thread.sleep(1000);
		System.out.println(c.millis());

		// 时区的信息
		System.out.println(ZoneId.getAvailableZoneIds());
		ZoneId zoneid = ZoneId.systemDefault();
		System.out.println(zoneid.getId());
		System.out.println(zoneid.getRules());
		System.out.println(zoneid.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
		Locale.getAvailableLocales();

		// Period Duration ChronoUnit
		// 两个时间相隔多久的封装
		LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);// 年月日时分秒
		Duration duration = Duration.between(from, time);

		long huors = ChronoUnit.HOURS.between(from, time);
		System.out.println(huors);

		System.out.println(duration.toHours());
		System.out.println(duration.toMillis());
		System.out.println(duration.toDays());

		// 两个日期相隔的天,月,年
		Period p = Period.between(myBirthday, today);
		System.out.println(p.getMonths() + ";" + p.getYears() + ";" + p.getDays());

		// 判断时间
		time.isBefore(from);
		time.isAfter(from);
		time.isEqual(from);
		// 是否闰年
		today.isLeapYear();
	}
}
