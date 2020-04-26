package com.itheima.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类。包括 开启事务，提交事务，回滚事务，释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction() throws SQLException {
        connectionUtils.getThreadConnection().setAutoCommit(false);
    }
    /**
     * 提交事务
     */
    public void commit() throws SQLException {
        connectionUtils.getThreadConnection().commit();
    }
    /**
     * 回滚事务
     */
    public void rollback() throws SQLException {
        connectionUtils.getThreadConnection().rollback();
    }
    /**
     * 释放连接
     * 并未关闭，而是还回连接池中
     */
    public void release() throws SQLException {
        connectionUtils.getThreadConnection().close();
        connectionUtils.removeConnection();
    }
}
