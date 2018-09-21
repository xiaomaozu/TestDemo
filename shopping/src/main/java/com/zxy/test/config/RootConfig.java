package com.zxy.test.config;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@ComponentScan(value="com.zxy.test",excludeFilters={
        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Controller.class})
})
@Configuration //表明此类是配置类
@ComponentScan // 扫描自定义的组件(repository service component controller)
@PropertySource("classpath:db.properties") // 读取application.properties
@MapperScan("com.mapper") //扫描Mybatis的Mapper接口
@EnableTransactionManagement //开启事务管理
public class RootConfig {

    @Autowired
    private Environment env;  //注入环境变量获取实例
    
    /**
     * description: 配置数据源
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 20:11
     * @param
     * @return ProxoolDataSource 实现自DataSource
     *
     **/
    @Bean(name="dataSource")
    public ProxoolDataSource dataSource(){
        ProxoolDataSource proxoolDataSource = new ProxoolDataSource();
        proxoolDataSource.setDriver(env.getProperty("proxool.alias"));
        proxoolDataSource.setDriverUrl(env.getProperty("proxool.driver"));
        proxoolDataSource.setUser(env.getProperty("proxool.user"));
        proxoolDataSource.setPassword(env.getProperty("proxool.password"));
        return proxoolDataSource;
    }
    
    /**
     * description: 配置mybatis的SqlSessionFactoryBean
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 20:37
     * @param  dataSource 数据源, PropertiesConfig 自定义的属性类
     * @return 
     **/
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, PropertiesConfig propertiesConfig) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(propertiesConfig.getMybatisTypeAliasPackage());
        // 动态获取SqlMapper
        PathMatchingResourcePatternResolver classPathResource = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(classPathResource.getResources(propertiesConfig.getMybatisConfigLocation()));
        return sqlSessionFactoryBean;
    }

    /**
     * description: 配置声明式事务
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 21:09
     * @param dataSource 数据源
     * @return PlatformTransactionManager
     **/
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    /*
     * description: 使用spring提供的PropertyPlaceholderConfigurer 读取数据库配置信息.properties
     * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
     * @date 2018/9/20 21:11
     * @param
     * @return org.springframework.context.support.PropertySourcesPlaceholderConfigurer
     **/
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

}
