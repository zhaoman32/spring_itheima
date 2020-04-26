package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该都在业务层
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAllAccount() throws SQLException {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) throws SQLException {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) throws SQLException {
            accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) throws SQLException {
            accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) throws SQLException {
            accountDao.deleteAccount(accountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) throws SQLException {
        System.out.println("transfer ...");
            //不支持事务
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            source.setMoney(source.getMoney()-money);
            target.setMoney(target.getMoney()+money);
            accountDao.updateAccount(source);
            //int i = 1/0;
            accountDao.updateAccount(target);
    }

    @Override
    public void test() {

    }
}
