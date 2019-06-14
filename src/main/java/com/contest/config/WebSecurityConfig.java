package com.contest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.contest.security.ContestUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Bean
	UserDetailsService contestUserService() { // 注册UserDetailsService 的bean
		return new ContestUserService();
	}
	
   /* @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }*/
	
	//自定义加密方式
	@Bean
	public static PasswordEncoder passwordEncoder(){
		return new ContestPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(contestUserService()); //user Details Service验证
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

		  http.authorizeRequests()
		  //访问controller权限控制
		  //这里有陷阱,数据库角色表中存"ROLE_ADMIN",而这里使用"ADMIN",因为springsecurity会自动在前面增加"ROLE_"
		 .antMatchers("/mgt/**").hasAnyRole("ADMIN")
          .anyRequest().authenticated() //任何请求,登录后可以访问
          .and()
          .formLogin()
          .successForwardUrl("/main")
          .loginPage("/login")
          .failureUrl("/login?error")
          .permitAll() //登录页面用户任意访问
          .and()
          .logout().permitAll() //注销行为任意访问
		  .and()
		  .exceptionHandling()
		  .accessDeniedHandler(accessDeniedHandler);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		  //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/index.html","/portal","/course/**"
        		,"/img/**","/css/**","/js/**","/pic/**/*.jpg","/pic/**/*.gif","/pic/**/*.png","/video/**","/page/**"
        		,"/fonts/**","/regist","/user/regist","/com/**");
	}
	
	

}
