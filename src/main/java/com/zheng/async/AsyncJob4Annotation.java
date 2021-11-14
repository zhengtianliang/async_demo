package com.zheng.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author: ZhengTianLiang
 * @date: 2021/11/14  23:13
 * @desc: 为了注解异步的类，这个类，提供的是  要异步做的事
 */
@Slf4j
@Component  // 因为待会要通过@Autowired 注入进来，所以需要添加到spring容器中去
public class AsyncJob4Annotation {

    @Async(value = "threadPoolConfig") // 加上异步的注解
    public void do2() throws InterruptedException {
        log.info("do2 start ");
        Thread.sleep(2000L);
        log.info("do2 end ");
    }

    @Async("threadPoolConfig")
    public void doException() {
        if (1 == 1) {
            throw new RuntimeException("123");
        }
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  23:29
     * @desc: 有返回结果的异步
     */
    @Async("threadPoolConfig")
    public Future<String> do2WithResult() throws InterruptedException {
        log.info("do2WithResult start ");
        Thread.sleep(2000L);
        log.info("do2WithResult end ");

        return new AsyncResult<>("do2WithResult----SUCCESS");
    }
}
