package com.itheima.factory;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 创建Bean对象的工厂，用工厂模式解耦
 *
 * Bean：可重用组件，比如service、dao
 * JavaBean: 用java语言编写的可重用组件，创建service和dao对象的
 *          JavaBean的范围 > 实体类
 *
 * 创建方法：
 *         1、用配置文件来配置service和dao。配置内容：唯一标识==全限定类名（key-value形式）
 *         2、通过读取配置文件中的内容 反射 创建对象
 * 配置文件：xml / properties
 *
 *
 * @author zhaoman
 */
public class BeanFactory {

    /**
     * 定义一个Propreties对象
     */
    private static Properties props;

    /**
     * 定义一个map，用于存放创建的对象，称之为 容器
     */
    private static Map<String,Object> beans;

    //使用静态代码块为Properties对象赋值,在类加载时只执行一次
    static {
        try {
            //实例化对象
            props = new Properties();
            //获取properties文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration keys = props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出key的值
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //把key-value存入容器之中,只有accountDao，accountService两个单例对象
                beans.put(key, value);
            }
        } catch (Exception e) {
           throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return
     */
    public static  Object getBean(String beanName) {
        return beans.get(beanName);
    }
    /*
    public static  Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            //用反射的方式创建对象，每次都会调用默认构造函数创建对象
            bean = Class.forName(beanPath).newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;

    }
     */
}
