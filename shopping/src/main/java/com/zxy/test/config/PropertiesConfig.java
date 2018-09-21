package com.zxy.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * description: 把应用需要的属性映射出来在该类中，注入容器。
 * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
 * @version 1.0
 * @date 2018/9/20 20:50
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

        @Value("${spring.datasource.url}")
        private String url;      //注入url地址
        @Value("${spring.datasource.driver}")
        private String driver;  //注入数据库的驱动driver
        @Value("${spring.datasource.user}")
        private String user;    //注入数据库用户名
        @Value("${spring.datasource.password}")
        private String password;  //注入数据库密码
        @Value("${spring.web.view.prefix}")
        private String webViewPrefix;   //注入webView地址的前缀
        @Value("${spring.web.view.suffix}")
        private String webViewSuffix;  //注入webView地址的后缀
        @Value("${spring.web.static.handler}")
        private String webStaticHandler;  //web内置资源地址
        @Value("${spring.web.static.resource}")
        private String webStaticResource;
        @Value("${spring.web.static.cache.period}")
        private Integer webStaticCachedPeriod;
        @Value("${mybatis.type.alias.package}")
        private String mybatisTypeAliasPackage;
        @Value("${mybatis.configLocation}")
        private String mybatisConfigLocation;
        public String getWebViewPrefix() {
            return webViewPrefix;
        }

        public String getWebViewSuffix() {
            return webViewSuffix;
        }

        public String getWebStaticHandler() {
            return webStaticHandler;
        }

        public String getWebStaticResource() {
            return webStaticResource;
        }

        public Integer getWebStaticCachedPeriod() {
            return webStaticCachedPeriod;
        }

        public String getMybatisTypeAliasPackage() {
            return mybatisTypeAliasPackage;
        }

        public String getUrl() {
            return url;
        }

        public String getDriver() {
            return driver;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }

        public String getMybatisConfigLocation() {
            return mybatisConfigLocation;
        }

        public void setMybatisConfigLocation(String mybatisConfigLocation) {
            this.mybatisConfigLocation = mybatisConfigLocation;
        }

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
}
