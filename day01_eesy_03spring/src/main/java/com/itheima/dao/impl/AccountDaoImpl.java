package com.itheima.dao.impl;
import com.itheima.dao.IAccountDao;

/**
 * 账户的持久层实现类
 * @author zhaoman
 */
public class AccountDaoImpl implements IAccountDao {


    @Override
    public void saveAccount() {
        System.out.println("保存了账户");

    }
}
