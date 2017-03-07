package com.thread;

/**
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 * Created by infear on 2017/2/26.
 */
public class JoinTest {
    public static void main(String args[]) throws InterruptedException {
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 finished!");
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // wait t1 at most 300ms
                    t1.join(300);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 finished!");
            }
        });
        t2.start();

        // wait for t2 at most 1s
        t2.join(1000);

        /* main thread wait for t2 1s, while t2 wait for t1 300ms and run probably 500ms.
        As t1 need 10s to run, so the actual order is t2->main->t1 */
        System.out.println("main thread finished!");
    }
}
