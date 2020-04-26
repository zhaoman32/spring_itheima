package com.itheima.ui;


import com.itheima.dao.IAccountDao;
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
     * 获取spring的IOC核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext：加载类路径下的配置文件，要求配置文件在类路径下(常用)
     *      FileSystemXmlApplicationContext：加载磁盘任意路径下的配置文件（必须有访问权限）
     *      AnnotationConfigApplicationContext：读取注解创建容器
     *
     * 核心容器的两个接口引发的问题：
     *      ApplicationContext：单例对象适用  （更常用）
     *              构建核心容器时，创建对象采取的策略是采用立即加载的方式。一读取完配置文件就创建配置的对象。
     *      BeanFactory：多例对象适用
     *              构建核心容器时，创建对象采取的策略是延迟加载的方式。什么时候根据id获取对象，什么时候真正创建对象。
     *
     * @param zhaoman
     */
    public static void main(String[] args) {



        //---------ApplicationContext----------
        // 1、获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //ApplicationContext ac = new FileSystemXmlApplicationContext("/src/main/resources/bean.xml");
        // 2、根据id获取Bean对象，两种方式
        IAccountService as = (IAccountService) ac.getBean("accountService");
        IAccountDao adao =  ac.getBean("accountDao", IAccountDao.class);
        System.out.println(as);
        System.out.println(adao);
        as.saveAccount();



        /*

        //---------BeanFactory----------
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        IAccountService as = (IAccountService) beanFactory.getBean("accountService");
        IAccountDao adao = (IAccountDao) beanFactory.getBean("accountDao");
        System.out.println(as);
        System.out.println(adao);

         */



    }


}
