package com.itheima.service.impl;


import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    /**
     * 构造函数
     */
    public AccountServiceImpl(){
        System.out.println("accountService 对象创建了");
    }


    @Override
    public void saveAccount() {
        System.out.println("service 中的 saveAccount 方法执行了");
    }

    public void init() {
        System.out.println("service 对象初始化了。。。");
    }
    public void destory() {
        System.out.println("service 对象销毁了。。。");
    }


}
