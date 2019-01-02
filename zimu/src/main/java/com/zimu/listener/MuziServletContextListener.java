package com.zimu.listener;

import net.spy.memcached.compat.log.SLF4JLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MuziServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //memcached的日志切换至SLF4JLogger
        //net.spy.memcached.compat.log.LoggerFactory 127行
        System.setProperty("net.spy.log.LoggerImpl", SLF4JLogger.class.getName());
    }

}
