package com.bootdo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bootdo.common.interceptor.MemberInterceptor;

@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

	@Autowired
	MemberInterceptor memberInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(memberInterceptor).addPathPatterns("/member/**");
	}
}
