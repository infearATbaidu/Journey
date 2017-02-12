package jvm.advanced.features.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by infear on 2016/7/9.
 * Ticket锁
 */
public class TicketClock {
    private AtomicInteger serviceNum = new AtomicInteger(0);
    private AtomicInteger ticketNum = new AtomicInteger(0);

    private static final ThreadLocal<Integer> NUM = new ThreadLocal<Integer>();

    public void lock() {
        // 上锁前排队取号，所以先调用的人会先获取到锁，是公平锁
        int ticket = ticketNum.getAndIncrement();
        // 设置各自线程的号
        NUM.set(ticket);
        // 轮询当前的服务号
        while (ticket != serviceNum.get()) ;
    }

    public void unlock() {
        int ticket = NUM.get();
        // 释放锁，服务号+1
        serviceNum.compareAndSet(ticket, ticket + 1);
    }
}
