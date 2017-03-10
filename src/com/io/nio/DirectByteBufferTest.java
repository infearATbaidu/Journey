package com.io.nio;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 设置-Xmx100，就会内存溢出，因为如果没设置-XX:MaxDirectMemorySize，则默认与-Xmx参数值相同
 * 使用cleaner.clean之后
 * Created by infear on 2017/3/9.
 */
public class DirectByteBufferTest {
    public static void main(String args[]) throws InterruptedException {
        System.out.println(Runtime.getRuntime().maxMemory());
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 128);
        System.out.println("Allocate OK");
        TimeUnit.SECONDS.sleep(10);
        ((DirectBuffer) directByteBuffer).cleaner().clean();
        System.out.println("Clean OK");
        TimeUnit.SECONDS.sleep(60);
    }
}
