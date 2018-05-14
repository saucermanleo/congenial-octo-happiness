package com.zy.pmk.conversionService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class LocalDateTimetoDateCnverter implements Converter<LocalDateTime, Date> {

	@Override
	public Date convert(LocalDateTime source) {
		Instant instant = source.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		return date;
	}

}
