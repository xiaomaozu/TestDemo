package com.chang.assembly.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 常培兵
 * @Description: 数据库配置
 * @date 2018/3/19 19:33
 */
@Configuration                                              //注解表明这个类是一个Bean的配置类。
@EnableAspectJAutoProxy                                     //注解激活Aspect自动代理  等同于<aop:aspectj-autoproxy/>
@PropertySources({//扫描配置文件
        @PropertySource("classpath:config/${spring.profiles.active}/jdbc.properties")
})
@MapperScan(value = "com.chang.dal.dao",annotationClass = Mapper.class)
@ComponentScan(basePackages ="com.chang.adapter.impl")      //@ComponentScan注解表示启用组件扫描，等同于xml文件中的
@EnableAsync                                                //开启对异步任务的支持，并通过在实际执行的Bean的方法中使用@Async注解来声明其是一个异步任务。
@EnableTransactionManagement                                //@EnableTransactionManagement注解开启注解式事务的支持。
public class DataSourceConfig {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.driverClassName}")
    String driverClassName;

    @Value("${spring.datasource.filters}")
    String filters;

    @Value("${spring.datasource.connectionProperties}")
    String connectionProperties;

    @Value("${spring.datasource.initialSize}")
    String initialSize;
    @Value("${spring.datasource.minIdle}")
    String minIdle;
    @Value("${spring.datasource.maxActive}")
    String maxActive;
    @Value("${spring.datasource.maxWait}")
    String maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    String timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    String minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    String testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    String testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    String testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    String poolPreparedStatements;
    @Value("${spring.datasource.maxOpenPreparedStatements}")
    String maxOpenPreparedStatements;

    private static String MAPPER_PATH = "/mybatis/*.xml";

    @Bean
    public DataSource getDataSource() throws Exception{
        logger.info("数据库配置初始化开始××××××××××××××××××××××××××××××××××××××××");
        Map<String,Object> properties=new HashMap<>();
        properties.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,driverClassName);
        properties.put(DruidDataSourceFactory.PROP_URL,dbUrl);
        properties.put(DruidDataSourceFactory.PROP_USERNAME,userName);
        properties.put(DruidDataSourceFactory.PROP_PASSWORD,password);
        properties.put(DruidDataSourceFactory.PROP_INITIALSIZE, initialSize);
        properties.put(DruidDataSourceFactory.PROP_MINIDLE, minIdle);
        properties.put(DruidDataSourceFactory.PROP_MAXACTIVE, maxActive);
        properties.put(DruidDataSourceFactory.PROP_MAXWAIT, maxWait);
        properties.put(DruidDataSourceFactory.PROP_TIMEBETWEENEVICTIONRUNSMILLIS, timeBetweenEvictionRunsMillis);
        properties.put(DruidDataSourceFactory.PROP_MINEVICTABLEIDLETIMEMILLIS, minEvictableIdleTimeMillis);
        properties.put(DruidDataSourceFactory.PROP_VALIDATIONQUERY, validationQuery);
        properties.put(DruidDataSourceFactory.PROP_TESTWHILEIDLE, testWhileIdle);
        properties.put(DruidDataSourceFactory.PROP_TESTONBORROW, testOnBorrow);
        properties.put(DruidDataSourceFactory.PROP_TESTONRETURN, testOnReturn);
        properties.put(DruidDataSourceFactory.PROP_POOLPREPAREDSTATEMENTS, poolPreparedStatements);
        properties.put(DruidDataSourceFactory.PROP_MAXOPENPREPAREDSTATEMENTS, maxOpenPreparedStatements);
        //添加统计、SQL注入、日志过滤器
        properties.put(DruidDataSourceFactory.PROP_FILTERS, filters);
        //sql合并，慢查询定义为5s
        properties.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES, connectionProperties);
        logger.info("数据库配置初始化结束××××××××××××××××××××××××××××××××××××××××");
        return DruidDataSourceFactory.createDataSource(properties);

    }
    @Bean
    public DataSourceTransactionManager transactionManager()throws Exception{
        return new DataSourceTransactionManager(this.getDataSource());
    }
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean()throws Exception{
        logger.info("初始化SqlSessionFactoryBean开始××××××××××××××××××××××××××××××××××××××××");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.getDataSource());//数据源
        sqlSessionFactoryBean.setTypeAliasesPackage("com.chang.dal.model");
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_URL_PREFIX+MAPPER_PATH;//数据库层xml文件地址
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        logger.info("初始化SqlSessionFactoryBean结束××××××××××××××××××××××××××××××××××××××××");
        return sqlSessionFactoryBean;
    }
}
