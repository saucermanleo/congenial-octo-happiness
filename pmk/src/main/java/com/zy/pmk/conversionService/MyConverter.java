package com.zy.pmk.conversionService;

import org.springframework.core.convert.converter.Converter;

import com.zy.pmk.pojo.A;
import com.zy.pmk.pojo.User;

public class MyConverter implements Converter<A, User> {

	@Override
	public User convert(A source) {
		return source.getUser();
	}


}
