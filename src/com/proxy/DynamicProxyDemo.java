package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * DynamicProxyDemo JDK 动态代理的使用
 * author:张冠之
 * time: 2017/7/24 15:12
 * e-mail: guanzhi.zhang@sojex.cn
 */

public class DynamicProxyDemo {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        ProxyHandler handler = new ProxyHandler(realSubject);
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(),handler);
        proxySubject.request();
    }

}
/**
 * 接口
 */
interface Subject{
    void request();
}

/**
 * 委托类
 */
class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("========= RealSubject Request =========");
    }
}

class ProxyHandler implements InvocationHandler{
    private Subject mSubject;

    public ProxyHandler(Subject subject){
        mSubject = subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(mSubject,args);
        System.out.println("after");
        return result;
    }
}
