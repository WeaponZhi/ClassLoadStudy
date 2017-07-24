package com.classloader;

/**
 * com.classloader.ClassLoaderTree 演示类加载器的树状组织结构
 * author:张冠之
 * time: 2017/7/24 10:22
 * e-mail: guanzhi.zhang@sojex.cn
 */

public class ClassLoaderTree {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}
