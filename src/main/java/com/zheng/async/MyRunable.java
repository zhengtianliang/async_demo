package com.zheng.async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: ZhengTianLiang
 * @date: 2021/11/14  22:57
 * @desc: 自定义runable接口，重新run方法
 */

@Slf4j
public class MyRunable implements Runnable {
    @Override
    public void run() {
        log.info("run do2 start ");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("run do2 end ");
    }
}
