package jvm.advanced.features.classLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhanggang02 on 2016/8/7.
 */
public class DynamicProxyTest {
    interface IHello {
        void say();
    }

    static class Hello implements IHello {
        @Override
        public void say() {
            System.out.println("hello");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object obj;

        Object bind(Object obj) {
            this.obj = obj;
            return Proxy.newProxyInstance(this.obj.getClass().getClassLoader(),
                    this.obj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(obj, args);
        }
    }

    public static void main(String args[]) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.say();
        // 只是测试下toString是否也被代理，答案是肯定的
        // 其实从源码上就是如此，而且生成的proxy0文件同样证明了这一点，toString,equals,hashcode均发生了代理
        hello.toString();
    }
}
