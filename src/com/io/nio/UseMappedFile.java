package com.io.nio;// $Id$

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class UseMappedFile {
    static private final int start = 0;
    static private final int size = 1024;

    static public void main(String args[]) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("usemappedfile.txt", "rw");
        FileChannel fc = raf.getChannel();

        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,
                start, size);

        // mbb.putChar(0, 'a');
        mbb.put("test".getBytes("utf-8"));
        mbb.put("MappedByteBuffer".getBytes("utf-8"));
        mbb.force();

        // mbb.put(1023, (byte) 122);
        System.out.println((byte) 97);
        System.out.print((byte) 33);

        fc.close();
        raf.close();
    }
}
