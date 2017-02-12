package jvm.advanced.features.optimization.runtime;

/**
 * Created by zhanggang02 on 2016/7/26.
 */
public class MethodInlining {
    public static final int NUM = 5000000;

    public static int doubleValue(int i) {
        return i * i;
    }

    // doubleValue 被内联进calSum
    public static long calSum() {
        long num = 0;
        for (int i = 0; i != 5000000; i++) {
            num += doubleValue(i);
        }
        return num;
    }

    public static void main(String args[]) {
        for (int i = 0; i != NUM; i++) {
            calSum();
        }
    }
}
