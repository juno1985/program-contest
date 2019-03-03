package com.contest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.contest.mapper")
@SpringBootApplication
public class ProgramContestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramContestApplication.class, args);
	}

}
