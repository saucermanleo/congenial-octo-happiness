package com.zy.pmk.somefunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TestGetFile {
	@Test
	public void getPropetyFile() {
		Properties p = new Properties();

		BufferedReader reader = null;
		try {
			p.load(this.getClass().getResourceAsStream("/my.properties"));
			File f = new File(this.getClass().getResource("/test.txt").getFile());
			Resource resouce = new ClassPathResource("/test.txt");
			// f = resouce.getFile();
			reader = new BufferedReader(new FileReader(f));
			String s;
			while ((s = reader.readLine()) != null) {
				System.out.println(s);
			}
			reader.close();
			String zy = p.getProperty("zy");
			System.out.println(zy);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
