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
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean restApiFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new RestSecurityFilter());
        registration.addUrlPatterns("/rest/*");
        return registration;
    }

    /**
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean mvcSecutiryFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MvcSecurityFilter());
        registration.addUrlPatterns("/mvc/secure/*");
        return registration;
    }
}
