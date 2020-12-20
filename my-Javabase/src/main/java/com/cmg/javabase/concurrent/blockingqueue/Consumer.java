package com.cmg.javabase.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    protected BlockingQueue queue = null;
    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            for (int i=0;i<100;i++){
                System.out.println(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
