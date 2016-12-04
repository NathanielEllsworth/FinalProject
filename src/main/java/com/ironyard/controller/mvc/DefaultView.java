package com.ironyard.controller.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * On the "localhost:8080" startup, this will redirect the path to the login.jsp instead of getting an error
 *
 * (it looks for the slash "/" and forwards to the login.jsp)
 *
 * Created by nathanielellsworth on 11/12/16.
 */

@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers( ViewControllerRegistry registry ){
        registry.addViewController("/").setViewName("forward:/mvc/open/login.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
