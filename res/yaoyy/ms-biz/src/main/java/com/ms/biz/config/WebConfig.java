package com.ms.biz.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Configuration
//@ComponentScan(
//        basePackages = "com.ms",
//        useDefaultFilters = false,
////        excludeFilters = {@ComponentScan.Filter(classes = {Service.class})},
//        includeFilters = {@ComponentScan.Filter(classes = {Controller.class})}
//)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {



//    @Value("${spring.mvc.view.prefix}")
//    private String prefix;
//    @Value("${spring.mvc.view.suffix}")
//    private String suffix;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(viewObjectAddingInterceptor());
        super.addInterceptors(registry);
    }


    @Bean
    public HandlerInterceptor viewObjectAddingInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return true;
            }

        };
    }





    /**
     * 配置多视图
     * @return
     */
    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setOrder(1);
        List<View> defaultViews  = new ArrayList<View>();
        defaultViews.add( new MappingJackson2JsonView());
        viewResolver.setDefaultViews(defaultViews);
        return viewResolver;

    }

//    @Bean
//    public InternalResourceViewResolver defaultViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setOrder(2);
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix(prefix);
//        viewResolver.setSuffix(suffix);
//        return viewResolver;
//    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Override
    public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public FilterRegistrationBean encodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new org.springframework.web.filter.CharacterEncodingFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(50 * 1024L * 1024L);
        return factory.createMultipartConfig();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createGsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    private GsonHttpMessageConverter createGsonHttpMessageConverter() {
        Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setGson(gson);

        return gsonConverter;
    }

//    //显示声明CommonsMultipartResolver为mutipartResolver
//    @Bean(name = "multipartResolver")
//    public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//        resolver.setMaxInMemorySize(40960);
//        resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
//        return resolver;
//    }

//    /**
//     * Filter 的声明和注册
//     */
//    @Bean
//    public Filter shiroFilter() {
//        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
//        delegatingFilterProxy.setBeanName("shiroFilter");
//        return delegatingFilterProxy;
//    }
//
//    @Bean
//    public FilterRegistrationBean myFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(shiroFilter());
//        registration.addUrlPatterns("/*");
//        return registration;
//    }

}
