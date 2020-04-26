package com.itheima.jdbctemplate;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JdbcTemplate的CRUD操作
 */
public class JdbcTemplateDemo4 {
    public static void main(String[] args) {
       ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
       IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
       Account account = accountDao.findAccountById(1);
       System.out.println(account);
       account.setMoney(10000f);
       accountDao.updateAccount(account);

    }
}

