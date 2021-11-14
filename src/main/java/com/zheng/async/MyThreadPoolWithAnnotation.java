package com.zheng.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: ZhengTianLiang
 * @date: 2021/11/14  23:04
 * @desc:
 */

@Configuration
public class MyThreadPoolWithAnnotation {

    @Bean
    public ThreadPoolExecutor getMyThreadPoolExecutor(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        return executor;
    }
}
