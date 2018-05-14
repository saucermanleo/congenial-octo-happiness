package com.zy.pmk.conversionService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DatetoLoacalDateTimeConverter implements Converter<Date,LocalDateTime> {


	@Override
	public LocalDateTime convert(Date source) {
		
		Instant instant = source.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime result = instant.atZone(zoneId).toLocalDateTime();
		LocalDateTime.ofInstant(source.toInstant(), zoneId);
		return result;
	}

}
