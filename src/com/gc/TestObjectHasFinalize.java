package com.gc;

/**
 * 重写object的finalize的方法会增加创建和销毁对象的开销，尤其是增加垃圾回收的时间，最终导致内存溢出
 * Created by infear on 2017/3/8.
 */
public class TestObjectHasFinalize {
    public static void main(String[] args) {
        while (true) {
            TestObjectHasFinalize heap = new TestObjectHasFinalize();
            System.out.println("memory address=" + heap);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize.");
    }
}
