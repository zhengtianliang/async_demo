package com.zheng.async;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * @author: ZhengTianLiang
 * @date: 2021/11/14  23:15
 * @desc:
 */
@Configuration
@EnableAsync
public class MyThreadPool4Annotation {

    @Bean
    public Executor threadPoolConfig() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(5);
        //设置最大线程数
        executor.setMaxPoolSize(10);
        //设置队列容量
        executor.setQueueCapacity(1000);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(600);
        //设置默认线程名称
        executor.setThreadNamePrefix("test_zheng_");
        // 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，
        // 当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待所有任务结束后再关闭线程池
        //executor.setWaitForTasksToCompleteOnShutdown(true);
        //进行初始化线程池
        executor.initialize();
        return executor;
    }
}
