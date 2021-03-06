package com.itheima.ui;



import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 * @author zhaoman
 */
public class Client {

    /**
     *
     * @param zhaoman
     */
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();

        IAccountService as2 = (IAccountService) ac.getBean("accountService2");
        as2.saveAccount();

        IAccountService as3 = (IAccountService) ac.getBean("accountService3");
        as3.saveAccount();








    }


}
