package com.Multithreading.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {
        SharedResource sr=new SharedResource();
        ReadWriteLock lock=new ReentrantReadWriteLock();
        Thread t1=new Thread(()->{
            sr.producer(lock);
        });
        Thread t2=new Thread(()->{
            sr.producer(lock);
        });
        SharedResource sr1=new SharedResource();
        Thread t3=new Thread(()->{
            sr1.consume(lock);
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
