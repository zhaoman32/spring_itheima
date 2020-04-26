package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    //业务层在调用持久层时应当避免new对象
    //private IAccountDao accountDao = new AccountDaoImpl();

    private int i = 1;

    @Override
    public void saveAccount() {
        IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }

    /**
     * 何为IOC控制反转？
     *
     * IAccountDao accountDao = new AccountDaoImpl();
     * 采用new的方式创建对象，是主动的accountService(APP) 依赖于 accountDao(资源)
     * APP --> 多个资源
     *
     * IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
     * APP和资源之间解耦
     * APP --> BeanFactory --> 资源
     * 工厂控制资源，APP向工厂要资源
     *
     * 控制反转：控制权的转移。
     * 放弃自己控制对象（new）的权利，把控制权交给工厂（框架），来帮我们创建对象。
     * 好处：降低程序间的依赖关系，解耦
     * 这个过程完全交给 spring 去做！！
     *
     */
}
