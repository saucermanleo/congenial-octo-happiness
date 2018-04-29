package com.example.interceptor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*@Component*/
/*@WebFilter*/
public class TimeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start"); 
		long start = new Date().getTime();
		chain.doFilter(request, response);
		long end = new Date().getTime() - start;
		System.out.println(end);
		System.out.println("time filter finish"); 
		
	}

	@Override
	public void destroy() {
		System.out.println("filter destroy");
	}

}
