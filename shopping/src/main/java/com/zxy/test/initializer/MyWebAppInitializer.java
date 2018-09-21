package com.zxy.test.initializer;

import com.zxy.test.config.RootConfig;
import com.zxy.test.config.WebAppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * description:原理查看note.txt 文件。  初始化Spring容器和SpringMVC的搭建
 *
 *
 * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
 * @version 1.0
 * @date 2018/9/20 16:46
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * description:配置文件上传的具体参数，如保存路径、文件大小等等
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 21:25
     * @param
     * @return
     **/
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // Optionally also set maxFileSize, maxRequestSize, fileSizeThreshold
        registration.setMultipartConfig(new MultipartConfigElement("/"));
    }
}
