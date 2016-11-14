package com.ms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by burgl on 2016/8/13.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public Application() {
        super();
        setRegisterErrorPageFilter(true); // <- this one
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8189);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public  ErrorPageFilter initErrorPageFilter() {
        ErrorPageFilter filter = new ErrorPageFilter();
        filter.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
        filter.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500"));
        filter.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400"));
        filter.addErrorPages(new ErrorPage(RuntimeException.class,"/error/500"));
        return filter;
    }

    //显示声明CommonsMultipartResolver为mutipartResolver
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
        return resolver;
    }
}
