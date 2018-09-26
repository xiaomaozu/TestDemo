package com.chang.assembly.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/4/23 14:30
 * @Scheduled(fixedDelay=5000) 每隔 5 秒就会被调用，5 秒钟的间隔是指从上一次调用的完成之时开始算起。
 * @Scheduled(fixedRate=5000) 例如，下面这个方法每隔 5 秒就会被调用。此时的 5 秒就是从上一次调用之始开始算起了。
 *
 */
@Component
public class Test {

//    @Scheduled(cron="*/2 * * * * ?")
//    public void tempUserTaskDelete() throws InterruptedException {
//        Thread thread=Thread.currentThread();
//        System.out.println("+++++++++++++自动任1111:"+thread.getName()+"务执行中+++++++++++++++");
//        Thread.sleep(5000);
//    }
//
//    @Scheduled(cron="*/2 * * * * ?")
//    public void tempUserTaskDelete2() throws InterruptedException {
//        Thread thread=Thread.currentThread();
//        System.out.println("+++++++++++++自动任1111:"+thread.getName()+"务执行中+++++++++++++++");
//        Thread.sleep(5000);
//    }

}
