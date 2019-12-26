package com.thread;

public class DeathLockTest {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        Thread t1=new Thread(new LockA());
        Thread t2=new Thread(new LockB());
        t1.start();
        t2.start();
    }


}
class LockA implements  Runnable{
    String lockA="lockA";
    String lockB="lockB";
    @Override
    public void run() {
         synchronized (lockA){
             try {
                 Thread.sleep(30);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             synchronized (lockB){
                 System.out.println("LockA。。。");
             }
         }
    }
}
class LockB implements  Runnable{
    String lockA="lockA";
    String lockB="lockB";
    @Override
    public void run() {
        synchronized (lockB){
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockA){
                System.out.println("LockB。。。");
            }
        }
    }
}