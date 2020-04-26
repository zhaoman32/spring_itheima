package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该都在业务层
 */
public class AccountServiceImpl_OLD implements IAccountService {
    private IAccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }



    @Override
    public List<Account> findAllAccount() throws SQLException {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            List<Account> accounts =  accountDao.findAllAccount();
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
            return accounts;
        }catch (Exception e){
            // 5、回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 6、释放链接
            txManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer accountId) throws SQLException {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            Account account = accountDao.findAccountById(accountId);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
            return account;
        }catch (Exception e){
            // 5、回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 6、释放链接
            txManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) throws SQLException {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.saveAccount(account);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e){
            // 5、回滚操作
            txManager.rollback();
        }finally {
            // 6、释放链接
            txManager.release();
        }

    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.updateAccount(account);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e){
            // 5、回滚操作
            txManager.rollback();
        }finally {
            // 6、释放链接
            txManager.release();
        }

    }

    @Override
    public void deleteAccount(Integer accountId) throws SQLException {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.deleteAccount(accountId);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e){
            // 5、回滚操作
            txManager.rollback();
        }finally {
            // 6、释放链接
            txManager.release();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) throws SQLException {
        /*
        // 1、根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        // 2、根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        // 3、转出账户减钱
        source.setMoney(source.getMoney()-money);
        // 4、转入账户加钱
        target.setMoney(target.getMoney()+money);
        // 5、更新转出账户
        accountDao.updateAccount(source);
        // 6、更新转入账户
        accountDao.updateAccount(target);
        //////////////////////注意这不满足事务一致性/////////////////////////
        // 1256步都会和mysql获取一次连接，完成后提交。会出错
        // 应该由同一个connection控制
        // 需要使用ThreadLocal对象把Connection和当前线程绑定，从而使一个线程中只有一个控制事务的对象

         */
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            source.setMoney(source.getMoney()-money);
            target.setMoney(target.getMoney()+money);
            accountDao.updateAccount(source);
            int i = 1/0;
            accountDao.updateAccount(target);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e){
            // 5、回滚操作
            txManager.rollback();
            e.printStackTrace();
        }finally {
            // 6、释放链接
            txManager.release();
        }
    }
}
