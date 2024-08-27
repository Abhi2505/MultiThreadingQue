package com.Multithreading.LockLearnRentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Car {

    ReentrantLock lock=new ReentrantLock();
    public void drive(String name) throws InterruptedException {
        System.out.println("Thread named "+Thread.currentThread().getName());
        lock.lock();
        for(int i=0;i<3;i++) {
            System.out.println("driving " + name + " " + Thread.currentThread().getName());
            Thread.sleep(3000);
        }
        lock.unlock();
        }
}
