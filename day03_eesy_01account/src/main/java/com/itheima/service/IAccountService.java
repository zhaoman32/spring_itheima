package com.itheima.service;

import com.itheima.domain.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount() throws SQLException;

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId) throws SQLException;

    /**
     * 保存操作
     */
    void saveAccount(Account account) throws SQLException;

    /**
     * 更新操作
     * @param account
     */
    void updateAccount(Account account) throws SQLException;

    /**
     * 删除操作
     * @param accountId
     */
    void deleteAccount(Integer accountId) throws SQLException;

    /**
     * 转账
     * @param sourceName 转出账户名称
     * @param targetName 目标账户名称
     * @param money 金额
     */
    void transfer(String sourceName, String targetName, Float money) throws SQLException;

    //只是连接点，不是切入点，因为没有被增强
    void test();
}
