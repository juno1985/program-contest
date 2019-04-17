package com.contest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.contest.mapper")
@ComponentScan("com.contest")
@SpringBootApplication
public class ProgramContestApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProgramContestApplication.class, args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ProgramContestApplication.class);
	    }

}
