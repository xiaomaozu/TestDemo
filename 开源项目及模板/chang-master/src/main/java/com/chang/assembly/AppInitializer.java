package com.chang.assembly;

import com.alibaba.druid.support.http.WebStatFilter;
import com.chang.assembly.scheduled.ScheduledConfig;
import com.chang.assembly.db.DataSourceConfig;
import com.chang.assembly.mvc.AppWebConfiguration;
import com.chang.assembly.security.SecurityConfig;
import com.chang.assembly.websocket.WebSocketConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author 常培兵
 * @Description: 应用程序初始化期
 * @date 2018/3/19 19:47
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataSourceConfig.class,SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppWebConfiguration.class, WebSocketConfig.class,ScheduledConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistration.Dynamic webStatServlet = servletContext.addFilter("druidWebStatFilter", webStatFilter);
        webStatServlet.setInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        webStatServlet.addMappingForUrlPatterns(null, true, "/*");

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic encodingServlet = servletContext.addFilter("encodingFilter", encodingFilter);
        encodingServlet.addMappingForUrlPatterns(null, true, "/*");

        return super.registerServletFilter(servletContext, filter);
    }

    /**
     * 把异常抛出去,交给GlobalExceptionController处理
     *
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

}
