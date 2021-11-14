package com.zheng.controller;

import com.zheng.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhengTianLiang
 * @date: 2021/11/14  22:52
 * @desc:
 */

@RestController
@Slf4j
//@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:55
     * @desc: 不异步，顺序执行
     */
    @RequestMapping(value = "/test1")
    public String test1(){
        long l = System.currentTimeMillis();
        log.info("controller test1 start ...");
        userService.test1();
        log.info("controller test1 end ...");

        log.error("一共消耗的时间："+String.valueOf(System.currentTimeMillis()-l));
        // 一共消耗的时间：6029
        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:55
     * @desc: 异步_线程池方式
     */
    @RequestMapping(value = "/test2")
    public String test2(){
        long l = System.currentTimeMillis();
        log.info("controller test2 start ...");
        userService.test2();
        log.info("controller test2 end ...");

        log.error("一共消耗的时间："+String.valueOf(System.currentTimeMillis()-l));
        // 一共消耗的时间：4017
        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:55
     * @desc: 异步_@Async注解方式
     *
     *      1、启动类加注解 @EnableAsync
     *      2、把要异步的方法，提出去，到另一个类中，加上 @component、@Async注解
     */
    @RequestMapping(value = "/test3")
    public String test3(){
        long l = System.currentTimeMillis();
        log.info("controller test3 start ...");
        userService.test3();
        log.info("controller test3 end ...");

        log.error("一共消耗的时间："+String.valueOf(System.currentTimeMillis()-l));
        // 一共消耗的时间：4024
        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:55
     * @desc: 测试异步抛出异常会不会报错     不会报错
     */
    @RequestMapping(value = "/test4")
    public String test4(){
        long l = System.currentTimeMillis();
        log.info("controller test4 start ...");
        userService.test4();
        log.info("controller test4 end ...");

        log.error("一共消耗的时间："+String.valueOf(System.currentTimeMillis()-l)); // 一共消耗的时间：2014

        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/11/14  22:55
     * @desc: 测试异步_5  带返回结果
     *          注意：
     *          @Async 注解修饰的方法，必须返回void 或者 future
     *          建议把@Async修饰的方法放到独立的类中去，否则一旦this调用，会导致@Async失效
     *                  (因为@Async原理是用动态代理生成代理对象，用这个代理对象去异步的执行方法，
     *                  this调用的话是对象本身，而不是代理对象，所以会失效)
     */
    @RequestMapping(value = "/test5")
    public String test5(){
        long l = System.currentTimeMillis();
        log.info("controller test5 start ...");
        userService.test5();
        log.info("controller test5 end ...");

        log.error("一共消耗的时间："+String.valueOf(System.currentTimeMillis()-l));
        // 一共消耗的时间：4022
        return "SUCCESS";
    }
}
