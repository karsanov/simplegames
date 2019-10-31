package com.karsanov.simplegames.config;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.karsanov.simplegames.service.users.SimpleGamesLogoutSuccessHandler;

@Configuration
@EnableWebMvc
@ComponentScan("com.karsanov.simplegames")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer c) {
		c.enable();
	}

	@Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {

	      registry.addResourceHandler("/sql/**").addResourceLocations("/WEB-INF/sql/");
	      registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
	      registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	   }
	

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");

		return internalResourceViewResolver;
	}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleGamesLogoutSuccessHandler();
    }
    
    
    @Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}
