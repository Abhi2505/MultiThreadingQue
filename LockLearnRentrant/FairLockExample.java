package com.Multithreading.LockLearnRentrant;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample {
    private static final ReentrantLock fairLock = new ReentrantLock(true);

    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                try {
                    fairLock.lock();
                    System.out.println(Thread.currentThread().getName() + " acquired the lock");
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " released the lock");
                    fairLock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
