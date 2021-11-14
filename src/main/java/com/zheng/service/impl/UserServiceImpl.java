package com.zheng.service.impl;

import com.zheng.async.AsyncJob4Annotation;
import com.zheng.async.MyRunable;
import com.zheng.async.MyThreadPoolWithAnnotation;
import com.zheng.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: ZhengTianLiang
 * @date: 2021/11/14  22:52
 * @desc:
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    // 为了线程池而注入的
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    // 为了注解而注入的
    @Autowired
    private AsyncJob4Annotation asyncJob4Annotation;

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:57
     * @desc: 测试1
     */
    @Override
    public void test1() {
        // 假设这个接口一共做三件事
        try {
            this.do1();

            this.do2();
            this.do3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:57
     * @desc: 测试2
     */
    @Override
    public void test2() {
        // 测试2还是执行这三方法，但是用了线程池了

        try {
            this.do1();
            threadPoolExecutor.execute( new MyRunable());
            this.do3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:16
     * @desc: 测试3
     */
    @Override
    public void test3() {
        // 测试3还是执行这三方法，但是用了线程池了

        try {
            this.do1();
            asyncJob4Annotation.do2();
            this.do3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test4() {
        // 测试3还是执行这三方法，但是用了线程池了

        try {
            this.do1();
            asyncJob4Annotation.doException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:32
     * @desc: 测试5_带返回结果
     */
    @Override
    public void test5() {
        // 测试3还是执行这三方法，但是用了线程池了

        try {
            this.do1();
            Future<String> future = asyncJob4Annotation.do2WithResult();
            this.do3();

            // future 的get方法会阻塞，所以我们可以把他放在最后面(放在do3方法的后面，就又有了好几秒的时间)去，
            String s = future.get();
            log.info("结果是："+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void do1() throws InterruptedException {
        log.info("do1 start ");
        Thread.sleep(2000L);
        log.info("do1 end ");
    }

    public void do2() throws InterruptedException {
        log.info("do2 start ");
        Thread.sleep(2000L);
        log.info("do2 end ");
    }

    public void do3() throws InterruptedException {
        log.info("do3 start ");
        Thread.sleep(2000L);
        log.info("do3 end ");
    }
}
