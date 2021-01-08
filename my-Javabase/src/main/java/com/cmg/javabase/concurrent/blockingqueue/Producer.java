package com.cmg.javabase.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    protected BlockingQueue queue = null;
    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            for (int i=0;i<100;i++){
                queue.put(String.valueOf(i));
//                Thread.sleep(1000);/
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
