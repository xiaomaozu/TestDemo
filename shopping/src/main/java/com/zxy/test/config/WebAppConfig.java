package com.zxy.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * description:
 *
 * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
 * @version 1.0
 * @date 2018/9/20 16:46
 */
@Configuration
@ComponentScan(value="com.zxy.test",excludeFilters={
        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Controller.class})
})
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private PropertiesConfig propertiesConfig;

    /**
     * description: 配置内部资源视图解析器的前缀后缀
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 21:19
     * @param
     * @return org.springframework.web.servlet.view.InternalResourceViewResolver
     **/
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix(propertiesConfig.getWebViewPrefix());
        internalResourceViewResolver.setSuffix(propertiesConfig.getWebViewSuffix());
        return internalResourceViewResolver;
    }

   /**
    * description: 备用，是否支持异步处理
    * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
    * @date 2018/9/20 21:14
    * @param asyncSupportConfigurer
    * @return void
    **/
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
        defaultServletHandlerConfigurer.enable();
    }

    /**
     * description:设置统一错误处理要跳转的视图
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 21:16
     * @param
     * @return SimpleMappingExceptionResolver
     **/
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.getProperty("java.lang.Exception", "error");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }

    /**
     * description:添加静态资源
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 21:17
     * @param  registry
     * @return void
     **/
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(propertiesConfig.getWebStaticHandler()).addResourceLocations(propertiesConfig.getWebStaticResource()).setCachePeriod(propertiesConfig.getWebStaticCachedPeriod());
    }

    /**
     * description:配置multipart解析器具体参数的配置在AbstractAnnotationConfigDispatcherServletInitializer的方法
     *              customizeRegistration(Dynamic registration)方法或web.xml中设置
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 16:05
     * @param
     * @return MultipartResolver
     **/
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        return new StandardServletMultipartResolver();
    }

}
