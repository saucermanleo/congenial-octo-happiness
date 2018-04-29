package com.example.security;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.pojo.ImageCode;
import com.example.security.codegeneration.CodeGenerator;

@RestController
public class ValidateCodeController {
	
	
	@Autowired
	private CodeGenerator codegenerator;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@GetMapping("/code/image")
	public void createImage(HttpServletRequest request, HttpServletResponse response) {
		ImageCode imagecode = codegenerator.createImageCode();
		sessionStrategy.setAttribute(new ServletWebRequest(request), ImageCode.SESSION_KEY, imagecode);
		try {
			ImageIO.write(imagecode.getImage(), "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
