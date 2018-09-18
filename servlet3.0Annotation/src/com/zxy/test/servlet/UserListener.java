package com.zxy.test.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听servlet的创建启动过程
 */
public class UserListener implements ServletContextListener {

    /**
     * 监听servlet启动
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("UserListener....contextListenering....");
    }

    /**
     * 监听servlet销毁
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("UserListener....contextDestroyed....");

    }
}
