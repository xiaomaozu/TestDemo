package com.chang.assembly.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 常培兵
 * @Description: 定时任务调度配置类
 * @date 2018/4/23 14:17
 * 通过 @Configuration注解，指出该类是Bean配置的信息源，再通过@EnableScheduling开启定时调度，
 * 一般情况下实际项目中都是多线程执行定时任务的，所以我们可以通过实现SchedulingConfigurar接口，
 * 访问底层的ScheduledRegistrar实例，产生一个Executer对象，然后根据系统的承受能力调整线程池中工作线程的数目，
 * 这里我启了4个线程。注意上面使用的@bean(destroyMethod="shutdown")是为了确保当Spring应用上下文关闭的时候任务执行者也被正确地关闭。
 */
@Configuration
@EnableScheduling
@ComponentScan(value = {"com.chang.assembly.scheduled"})
public class ScheduledConfig implements SchedulingConfigurer {
   private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        logger.info("配置任务寄存器: {}", taskRegistrar);
        taskRegistrar.setScheduler(taskExecutor());
    }


    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(4);
    }
}
