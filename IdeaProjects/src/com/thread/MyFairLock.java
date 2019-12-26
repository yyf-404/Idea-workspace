package com.thread;

import java.util.concurrent.locks.ReentrantLock;

public class MyFairLock {
    /**
     *     true 表示 ReentrantLock 的公平锁
     */
    private  ReentrantLock lock = new ReentrantLock(false);

            public   void testFail(){
                try {
                    lock.lock();
            try {
                if(Thread.currentThread().getName().equals("Thread-0"))
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"获得了锁");

        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyFairLock fairLock = new MyFairLock();
        Runnable runnable = () -> {
            //System.out.println(Thread.currentThread().getName()+"启动");
            fairLock.testFail();
        };
        Thread[] threadArray = new Thread[10];
        for (int i=0; i<10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i=0; i<10; i++) {
            threadArray[i].start();
            Thread.sleep(10);
        }
    }
}
