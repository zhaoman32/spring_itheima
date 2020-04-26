package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 该类是一个配置类，它的作用和bean.xml的作用是一样的
 * spring中的新注解
 * @Configuration ：指定当前类是一个配置类
 *                  细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，可以不写
 * @ComponentScan : 指定spring在创建容器时要扫描的包.
 *                  属性：basePackages value 都是用于指定创建容器时要扫描的包
 *                  等同于在xml中配置：
 *                  <context:component-scan base-package="com.itheima"></context:component-scan>
 * @Bean ：用于把当前方法的返回值作为bean对象存入spring的IOC容器中
 *         属性：name用于指定bean的id。默认当前方法名
 *         细节：当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象
 *              查找的方式和Autowired的注解作用一样
 * @Import : 用于导入其他的配置类
 *          属性：value用于只等其他配置类的字节码。
 *               使用import注解后，有import注解的类就是父配置类，导入的都是子配置类
 * @PropertySource : 用于指定properties文件的位置
 *                   属性value指定文件的名称和路径。关键字classpath表示类路径下
 */
@Configuration
@ComponentScan(basePackages = {"com.itheima"})
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
