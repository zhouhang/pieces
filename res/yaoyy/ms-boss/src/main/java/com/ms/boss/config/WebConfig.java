package com.ms.boss.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ms.tools.utils.gson.adapter.IntegerDefault0Adapter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
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
@EnableWebMvc
@Configuration
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
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .create();

        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setGson(gson);

        return gsonConverter;
    }


}
