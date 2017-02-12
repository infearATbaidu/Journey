package jvm.advanced.features.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by infear on 2016/7/9.
 * 隐式的使用链表实现的自旋锁
 */
public class CLHLock {
    private static class CLHNode {
        private volatile boolean isLocked = true;
    }

    private volatile CLHNode tail;
    private static ThreadLocal<CLHNode> node = new ThreadLocal<>();

    private AtomicReferenceFieldUpdater<CLHLock, CLHNode> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
        CLHNode clhNode = new CLHNode();
        node.set(clhNode);
        CLHNode pre = referenceFieldUpdater.getAndSet(this, clhNode);
        // 前驱为空，也就意味着锁当前是空闲的，就可以直接占据锁
        if (pre == null) {
            return;
        }
        // 轮询前驱
        while (pre.isLocked) ;

    }

    public void unlock() {
        CLHNode clhNode = node.get();
        // 释放锁的时候:
        // 1.如果当前tail是自己，意味着没有后续线程在等待，则直接更新成tail，也就无需把isLocked置为false
        // 2.否则CAS就会失败，这时把isLocked置为false，后续线程在轮询时就会收到它已经完成的消息
        if (!referenceFieldUpdater.compareAndSet(this, clhNode, null)) {
            clhNode.isLocked = false;
        }
    }
}
