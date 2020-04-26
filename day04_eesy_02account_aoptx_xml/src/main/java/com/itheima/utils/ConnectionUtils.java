package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，从数据源中获得一个链接，并和线程绑定
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * 获取当前线程上的链接
     *
     * @return
     */
    public Connection getThreadConnection() {
        try {
            // 1、先从ThreadLocal上获取
            Connection conn = tl.get();
            // 2、判断当前线程上是否有链接
            if (conn == null) {
                // 3、从数据源中获取一个链接，和线程绑定，存入ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            // 4、返回当前线程的链接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把链接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }



}
