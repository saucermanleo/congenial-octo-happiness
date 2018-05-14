package com.zy.pmk.somefunction;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * @author Zangy
 *ISO 639制定了语言代号
 *ISO 3166定义了国家代号
 */
public class LocaleTest {
	public void test(Locale[] locales) {
		String s = "getLanguage:%S getCountry:%S  getDisplayName:%S getDisplayLanguage:%S getDisplayCountry:%S getISO3Country:%S getISO3Language:%S";
		//locales = locales==null?Locale.getAvailableLocales():locales;
		for (Locale l : locales) {
			System.out.println(l);
			System.out.println(l.getDisplayName(Locale.JAPANESE));
			String print = String.format(s, l.getLanguage(), l.getCountry(), l.getDisplayName(),
					l.getDisplayLanguage(), l.getDisplayCountry(), l.getISO3Country(), l.getISO3Language());
			System.out.println(print);
			
		}
		
		
	}
	
	//@Test
	/**
	 * 区域敏感型的类一般支撑getAvailableLocales方法 , locale[]
	 */
	public void NumberFormatTest() {
		this.test(NumberFormat.getAvailableLocales());
	}
	/**
	 * 本地化
	 */
	@Test
	public void localization() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("res/res", Locale.US);
		String cancel = resourceBundle.getString("cancelKey");
		String hello = resourceBundle.getString("hello");
		hello = MessageFormat.format(hello, "Amy");
		System.out.println(cancel);
		System.out.println(hello);
		resourceBundle = ResourceBundle.getBundle("res/res", Locale.CHINA);
		cancel = resourceBundle.getString("cancelKey");
		hello = resourceBundle.getString("hello");
		hello = MessageFormat.format(hello, "张阳");
		System.out.println(cancel);
		System.out.println(hello);
	}
}
