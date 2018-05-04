package com.zy.pmk.conversionService;

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
		User u = new User();
		u.setUsername("zy");
		A a = new A();
		a.setUser(u);
		User result = service.convert(a, User.class);
		System.out.println(result.getUsername());
		
	}
}
