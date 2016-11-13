package com.ironyard.security;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by nathanielellsworth on 11/12/16.
 */
@Configuration
public class FilterRegistrations {

    @Bean
    public FilterRegistrationBean mvcSecurityFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean(new MvcSecurityFilter());
        registration.addUrlPatterns("/mvc/secure/*");
        return registration;
    }

}
