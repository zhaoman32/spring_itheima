package com.itheima.service;

import com.itheima.domain.Account;

import java.sql.SQLException;

/**
 * 账户的业务层接口
 */
public interface IAccountService {
    /**
     * 根据Id查询账户信息
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId) throws SQLException;

    /**
     * 转账
     * @param sourceName
     * @param targetName
     * @param money
     */
    void transfer(String sourceName, String targetName, Float money) throws SQLException;
}
