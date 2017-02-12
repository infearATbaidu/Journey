package jvm.advanced.features.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by infear on 2016/7/9.
 * 自旋锁
 */
public class SpinLock {
    private AtomicReference<Thread> reference = new AtomicReference<Thread>();

    /**
     * 如果没有占据锁，就获取锁
     */
    public void lock() {
        Thread current = Thread.currentThread();
        while (!reference.compareAndSet(null, current)) {
            System.out.println(current.getId());
        }
    }

    /**
     * 如果当前锁的拥有者是自己，就释放锁
     */
    public void unlock() {
        Thread current = Thread.currentThread();
        reference.compareAndSet(current, null);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String args[]) {
        SpinLock lock = new SpinLock();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println("Thread t1 get lock!");
            lock.unlock();
        });
        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println("Thread t2 get lock!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        });

        t1.start();
        t2.start();
    }
}
