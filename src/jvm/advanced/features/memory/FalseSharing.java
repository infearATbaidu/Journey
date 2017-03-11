package jvm.advanced.features.memory;

/**
 * Created by infear on 2017/3/11.
 * Cache是由很多个cache line组成的。每个cache line通常是64字节，并且它有效地引用主内存中的一块儿地址。
 * 一个Java的long类型变量是8字节，因此在一个缓存行中可以存8个long类型的变量。
 * 当没有太多关联的对象被放到一个cache line上，因为其中单个对象的更新而导致cache line失效时，cache line的作用就无法体现，称为伪共享
 * 可以使用拉大数组件元素的间隔来避免共享cache line,在java8中可以通过@Contended注解来完成
 */
public class FalseSharing implements Runnable {
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValueNoPadding[] longs;

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println("Thread num " + i + " duration = " + (System.currentTimeMillis() - start));
        }

    }

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValueNoPadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValueNoPadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }


    /* Use padding:
    Thread num 1 duration = 718
    Thread num 2 duration = 699
    Thread num 3 duration = 729
    Thread num 4 duration = 827
    Thread num 5 duration = 1201
    Thread num 6 duration = 1233
    Thread num 7 duration = 1532
    Thread num 8 duration = 1729
    Thread num 9 duration = 1962
    使用p1,p2等作为padding，让每个线程对应的ValuePadding都尽可能能拥有自己的cache line，当发生更新时就只会使自己的cache line失效，不会影响其他线程的ValuePadding。
    */
    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }


    /* while not use padding:
    Thread num 1 duration = 676
    Thread num 2 duration = 1591
    Thread num 3 duration = 2582
    Thread num 4 duration = 2767
    Thread num 5 duration = 3054
    Thread num 6 duration = 3641
    Thread num 7 duration = 3971
    Thread num 8 duration = 4201
    Thread num 9 duration = 4003
     如果不使用padding，那么整个longs数组就很有可能共享几个cache line，导致每个线程在更新自己的ValuePadding时，所属的整个cache line失效，那么其他线程就只能从主存访问，耗时就增加了。
    */
    public final static class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }
}
