package com.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

/**
 * Created by burgl on 2016/8/13.
 */

@SpringBootApplication
//@ComponentScan(
//        basePackages = "com.ms",
//        useDefaultFilters = true,
//        excludeFilters = {@ComponentScan.Filter(classes = {Controller.class})}
//)
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {


    @Autowired
    ErrorPageFilter errorPageFilter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8189);
//        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
//        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500"));
//        container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400"));

        errorPageFilter.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
        errorPageFilter.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500"));
        errorPageFilter.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400"));
    }


    public Application() {
        super();
        setRegisterErrorPageFilter(true); // <- this one
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
