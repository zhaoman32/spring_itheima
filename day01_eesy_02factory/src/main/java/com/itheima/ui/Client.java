package com.itheima.ui;


import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层
 * @author zhaoman
 */
public class Client {
    public static void main(String[] args) {
        //表现层调用业务层，耦合性强。
        //IAccountService as = new AccountServiceImpl() ;

        for (int i = 0; i < 5; i++) {
            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
            /**
             * 此时的对象as是多例的,对象创建多次，执行效率不如单例
             * com.itheima.service.impl.AccountServiceImpl@c818063
             * com.itheima.service.impl.AccountServiceImpl@3f0ee7cb
             * com.itheima.service.impl.AccountServiceImpl@75bd9247
             * com.itheima.service.impl.AccountServiceImpl@7d417077
             * com.itheima.service.impl.AccountServiceImpl@7dc36524
             */
            as.saveAccount();
        }

    }

    /**
     * 这种强耦合的调用方式，一旦文件错误，会在编译时就报错
     *
     * 解决方案： 工厂模式解耦
     */
}
