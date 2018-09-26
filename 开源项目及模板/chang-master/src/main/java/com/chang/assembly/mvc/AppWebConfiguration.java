package com.chang.assembly.mvc;

import com.chang.assembly.mvc.interceptor.RateLimitingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/3/19 19:43
 */
//注解表明这个类是一个Bean的配置类。
@Configuration
//@EnableWebMVC注解用来开启Web MVC的配置支持。
@EnableWebMvc
//@ComponentScan注解表示启用组件扫描，等同于xml文件中的
@ComponentScan(value = {"com.chang.web", "com.chang.swagger"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 允许跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
    /**
     * 允许直接访问的资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceLocations指的是文件放置的目录，addResoureHandler指的是对外暴露的访问路径
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/view/**")
                .addResourceLocations("/view/");
    }

    /**
     * 支持对静态资源的访问
     * @param configurer
     */
  /*  @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer
    }
*/
    /**
     * spring 方法参数验证
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return  new MethodValidationPostProcessor();
    }

    /**
     * 文件上传
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20000);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/view/pages/");
        bean.setSuffix(".jsp");
        return bean;
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitingInterceptor());
    }

    /**
     * 自定义拦截器
     * @return
     */
    @Bean
    public RateLimitingInterceptor rateLimitingInterceptor() {
        return new RateLimitingInterceptor();
    }
}
