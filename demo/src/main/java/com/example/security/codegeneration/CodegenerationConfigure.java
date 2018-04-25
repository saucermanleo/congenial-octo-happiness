package com.example.security.codegeneration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodegenerationConfigure {
	
	@Bean
	@ConditionalOnMissingBean
	public CodeGenerator createCodeGenerator() {
		CodeGenerator codeGenerator = new ImageCodeGenerator();
		return codeGenerator;
	}
}
