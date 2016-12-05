package com.ironyard.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by nathanielellsworth on 11/12/16.
 */

@Configuration
public class FilterRegistrations {

    /**
     * Apply RestSecurityFilter filter to any request that matches --> "/rest/*"
     * @return
     */
    @Bean
    public FilterRegistrationBean restApiFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new RestSecurityFilter());
        registration.addUrlPatterns("/rest/*");
        return registration;
    }

    /**
     * Apply MvcSecurityFilter filter to any request that matches --> "/mvc/secure/*"
     * @return
     */
    @Bean
    public FilterRegistrationBean mvcSecutiryFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MvcSecurityFilter());
        registration.addUrlPatterns("/mvc/secure/*");
        return registration;
    }
}
