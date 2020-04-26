package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    //业务层在调用持久层时应当避免new对象
    private IAccountDao accountDao = new AccountDaoImpl();

    /**
     * 构造函数
     */
    public AccountServiceImpl(){
        System.out.println("accountService 对象创建了");
    }


    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }


}
