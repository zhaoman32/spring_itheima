package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 账户的业务层实现类
 *
 * 曾经xml的配置:
 *     <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 *     scope="" init-method="" destroy-method="">
 *         <property name="" value="" ref=""></property>
 *     </bean>
 *
 *
 * 注解分类：
 *      用于创建对象的: 和xml配置文件中的<bean>标签的实现功能一样
 *          @Component : 把当前类对象存入spring对象中。
 *                       value用于指定bean的id。不写的时候，默认是当前类名，首字母小写
 *          @Controller 控制器。用在表现层
 *          @Service 服务器。用在业务层
 *          @Repository 存储库。用在持久层
 *          以上三个注解与Component一模一样，这三个是spring提供的明确的三层使用的注解，使三层对象更清晰
 *
 *      用于注入数据的: 和xml配置文件中的<property>标签的实现功能一样
 *          @Autowired ：自动按照类型注入。只要容器中有唯一一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                       出现位置：可以是变量上，也可以是方法上
 *                       细节：使用注解注入时，set方法就不是必须的了
 *                       先byType再byName
 *          @Qualifier ：按照类型注入的基础之上再按名称注入。在类成员注入时，不能单独使用，在给方法参数注入时可以
 *                       属性：value用于注入bean的id。需要和Autowired配合使用
 *          @Resource ：直接按照bean的id注入，可以独立使用。需要import javax.annotation.Resource;
 *                      属性name用于指定bean的id.
 *          以上三个注入只能注入bean类型数据，基本类型和string类型不能用。集合类型只能用XML类型实现
 *          @Value ：用于注入基本类型和string类型的数据
 *                   属性value：用于指定数据的值。可以使用spring中的SpEL（spring中的el表达式）
 *                   SpEL的写法：${表达式}
 *
 *      用于改变作用范围的: 和xml配置文件中的<scope>标签的实现功能一样
 *          @Scope ：用于指定bean的作用范围
 *                   属性value：指定范围的取值。singleton、prototype 默认单例
 *
 *      和生命周期相关的: 和xml配置文件中的<init-method><destroy-method>标签的实现功能一样
 *          @PreDestory : 用于指定销毁方法
 *          @PostConstruct ：用于指定初始化方法
 *
 * @author zhaoman
 */

@Service(value = "accountService")
//@Scope("singleton")
public class AccountServiceImpl implements IAccountService {


    /**
     * spring IOC 容器中是Map结构。key-String value-Object
     * 根据注解，springIOC中已经有 accountService和accountDao两个对象
     * Autowired  自动按照类型注入，前往spring IOC 发现 accountDao 唯一一个与之相同
     *            没有与之匹配的类型时会报错
     *            多个与之匹配时，首先查类型，进而查名称，不一样的就报错
     */
    @Autowired
//    @Qualifier("accountDao")
//    @Resource(name = "accountDao")
    private IAccountDao accountDao ;

    @PostConstruct
    public void init(){
        System.out.println("初始化方法执行了");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法执行了");
    }



    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }


}
