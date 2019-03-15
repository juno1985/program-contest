package com.contest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

//springMVC 配置，注册访问 /login 转向 login.html 页面
public class WebMvcConfig implements  WebMvcConfigurer  {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		WebMvcConfigurer.super.addViewControllers(registry); 
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/403").setViewName("403");
	}

}
