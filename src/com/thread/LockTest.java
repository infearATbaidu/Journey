package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by infear on 2017/2/26.
 */
public class LockTest {
    public static void main(String args[]) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    lock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 finished!");
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join(1000);
                    lock.lock();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 finished!");
                lock.unlock();
            }
        });
        t2.start();

        System.out.println("main thread finished!");
    }
}
