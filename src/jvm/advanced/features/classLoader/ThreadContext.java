package jvm.advanced.features.classLoader;

/**
 * Created by zhanggang02 on 2016/8/7.
 */
public class ThreadContext {
    public static void main(String args[]) {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
