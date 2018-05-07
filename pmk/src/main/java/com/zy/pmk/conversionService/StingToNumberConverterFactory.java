package com.zy.pmk.conversionService;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class StingToNumberConverterFactory implements ConverterFactory<String, Number> {

	@Override
	public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
		
		return new StringtoNumberConverter<T>(targetType);
	}
	
	private final class StringtoNumberConverter<T extends Number> implements Converter<String, T> {
		Class<T> targetClass;
		
		public StringtoNumberConverter(Class<T> targetClass) {
			this.targetClass = targetClass;
		}

		@Override
		public T convert(String source) {
		 T  t= 	TestparseNumber.parese(source, this.targetClass);
			return t;
		}
		
	}

}
