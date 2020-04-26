package com.itheima.factory;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }


    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取Service的代理对象
     * @return
     */
    public IAccountService getAccountService(){
        // 织入：返回代理对象的过程中加入了事务增强
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    @Override // 整个invoke是一个环绕通知
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //不是所有的业务层的方法都需要事务支持
                        if("test".equals(method.getName())){
                            return method.invoke(accountService, args);
                        }

                        Object rtValue = null;
                        try {
                            txManager.beginTransaction(); //前置通知
                            rtValue = method.invoke(accountService, args);//切入点方法调用
                            txManager.commit();//后置通知
                            return rtValue;
                        }catch (Exception e){
                            txManager.rollback();//异常通知
                            throw new RuntimeException(e);
                        }finally {
                            txManager.release();//最终通知
                        }
                    }
                });

    }

}
