package com.zy.pmk.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zy.pmk.util.PDFCreator;

@RestController
public class TestController {
	
	@Autowired
	private PDFCreator pdf;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="test",method= RequestMethod.GET)
	public void sayHello(HttpServletResponse response) {
		Map<String , Map> map1 = new HashMap<>();
		Map<String , Object> map = new HashMap<>();
		map.put("name", "zy");
		map.put("time", new Date());
		map1.put("pdf1.pdf", map);
		try {
			pdf.createPDFZIP("test.zip", response, map1);
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
}
