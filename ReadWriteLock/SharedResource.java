package com.Multithreading.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {
    boolean isAvailable=true;
    public void producer(ReadWriteLock lock){
        try{
            lock.readLock().lock();
            System.out.println("read lock acquired by: "+Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            lock.readLock().unlock();
            System.out.println("read lock release by "+Thread.currentThread().getName());
        }
    }
    public void consume(ReadWriteLock lock){
        try{
        lock.writeLock().lock();
        System.out.println("Write lock acquired by "+ Thread.currentThread().getName());
        isAvailable=false;
        }
        finally {
            lock.writeLock().unlock();
            System.out.println("Write lock release by "+ Thread.currentThread().getName());

        }
    }
}
