package com.itheima.test;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 * spring 整合Junit配置
 *      1、导入spring整合Junit的jar包
 *      2、使用Junit提供的一个注解把原有的main方法替换成spring提供的 @RunWith
 *      3、告知spring的运行器，spring的创建是基于xml还是注解的，并且说明位置 @ContextConfiguration
 *         locations:指定xml文件的位置，加上classpath关键字，表示类路径下
 *         classes:指定注解类所在的位置
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {
//    private ApplicationContext ac;
    @Autowired
    private IAccountService as;

//    @Before
//    public void init (){
//        //获取容器
//        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        //得到业务层对象
//        as = ac.getBean("accountService", IAccountService.class);
//    }

    @Test
    public void testFindAll(){
        //执行方法
        List<Account> accounts = as.findAllAccount();
        for (Account account: accounts ) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindOne() {
        //执行方法
        Account account = as.findAccountById(9);
        System.out.println(account);
    }
    @Test
    public void testSave() {
        //执行方法
        Account account = new Account();
        account.setName("zhaoman");
        account.setMoney(12345f);
        as.saveAccount(account);
    }
    @Test
    public void testUpdate() {
        //执行方法
        Account account = as.findAccountById(8);
        account.setMoney(23456f);
        as.updateAccount(account);
    }
    @Test
    public void testDelete() {
        //执行方法
        as.deleteAccount(8);
    }
}
