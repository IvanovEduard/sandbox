package cuncurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String []args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Runnable task = () -> {
            System.out.println("Thread1");
            lock.lock();
            System.out.println("Thread1 start");
            for (int i = 0; i < 5; i++) {
                System.out.println("a" + i);
            }
            System.out.println("Thread1 finish");
            lock.unlock();
        };
        lock.lock();

        Thread th = new Thread(task);
        th.start();
        System.out.println("main");
//        Thread.currentThread().sleep(2000);
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            Thread.currentThread().sleep(1000);
        }
        System.out.println("main2");
        lock.unlock();
    }
}
