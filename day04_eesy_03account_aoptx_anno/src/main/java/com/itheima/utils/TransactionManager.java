package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类。包括 开启事务，提交事务，回滚事务，释放连接
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}

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

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws SQLException {
        Object rtValue = null;
        try {
            //获取参数
            Object[] args = pjp.getArgs();
            //开启事务
            this.beginTransaction();
            //执行方法
            rtValue = pjp.proceed(args);
            //提交事务
            this.commit();
            return rtValue;
        } catch (Throwable throwable) {
            //回滚事务
            this.rollback();
            throw new RuntimeException(throwable);
        } finally {
            //释放资源
            this.release();
        }
    }
}
