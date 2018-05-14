package com.zy.pmk.conversionService;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import com.zy.pmk.pojo.A;
import com.zy.pmk.pojo.User;

/**
 * @author Zangy
 *DefaultConversionService是转换器的默认实现
 */
public class TestDefaultConversionService {
	@Test
	public void testconvert() {
		DefaultConversionService service = new DefaultConversionService();
		if(service.canConvert(String.class, Boolean.class)) {
			System.out.println(service.convert("true", Boolean.class));
		}
		service.addConverter(new MyConverter());
		service.addConverter(new DatetoLoacalDateTimeConverter());
		service.addConverter(new LocalDateTimetoDateCnverter());
		User u = new User();
		u.setUsername("zy");
		A a = new A();
		a.setUser(u);
		User result = service.convert(a, User.class);
		System.out.println(result.getUsername());
		
		Date date = new Date ();
		LocalDateTime localDateTime = service.convert(date, LocalDateTime.class);
		System.out.println(localDateTime);
		date = service.convert(localDateTime, Date.class);
		System.out.println(date);
	}
	
	@Test
	public void testparseNumber() {
		String intvalue = "33";
		int result = TestparseNumber.parese(intvalue, Integer.class);
		System.out.println(result);
		
		
	}
}

























