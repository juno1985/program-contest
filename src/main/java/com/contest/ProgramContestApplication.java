package com.contest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.contest.mapper")
@ComponentScan("com.contest")
@SpringBootApplication
public class ProgramContestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramContestApplication.class, args);
	}

}
