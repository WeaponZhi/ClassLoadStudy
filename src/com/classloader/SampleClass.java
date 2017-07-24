package com.classloader;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.lang.reflect.Method;

/**
 * com.classloader.SampleClass 不用类加载器读取同一个字节码文件，定义出的实例是不同的
 * author:张冠之
 * time: 2017/7/24 10:35 
 * e-mail: guanzhi.zhang@sojex.cn
 */

public class SampleClass {
    private SampleClass instance;

    /**
     * 不同类加载器对同一字节码加载后不能转型，抛出 ClassCastException
     * @param instance
     */
    public void setSample(Object instance){
        this.instance = (SampleClass)instance;
    }

    public static void testClassIdentity() {
        ClassLoader loader1 = new ClassLoader();
        ClassLoader loader2 = new ClassLoader();
        try {
            Class<?> class1 = Class.forName("com.classloader.SampleClass");
            Class<?> class2 = Class.forName("com.classloader.SampleClass");

            Object object1 = class1.newInstance();
            Object object2 = class2.newInstance();

            Method setSampleMethod = class1.getMethod("setSample",Object.class);
            setSampleMethod.invoke(object1,object2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testClassIdentity();
    }
}
