package com.zy.pmk.somefunction;

import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class TestDefaultConversionService {
	@Test
	public void testconvert() {
		DefaultConversionService defaultConversionService = new DefaultConversionService();
		if(defaultConversionService.canConvert(String.class, Boolean.class)) {
			System.out.println(defaultConversionService.convert("true", Boolean.class));
		}
	}
}
