package com.Multithreading.LockLearnRentrant;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EXtwoRunner {
    private Lock lock=new ReentrantLock();
    Condition cond=lock.newCondition();
    int count=0;
    private void increment(){
        for(int i=0;i<1000;i++) {
            count++;
        }

    }
    public void firstThread() throws InterruptedException {
        System.out.println("lets apply retentrant lock on first thread");
        for(int i=0;i<50;i++)System.out.println("ff "+i);
        lock.lock();
        cond.await();//similar to wait
        System.out.println("i am notified by other thread");
        try {
            increment();
        }
        //there can be chances that increment would return an exception so handle the unlock thing using finally block
        finally {
            lock.unlock();
        }
    }
    public void secondThread() throws InterruptedException {
        System.out.println("lets apply retentrant lock on second thread");
        for (int i = 0; i < 20; i++) System.out.println("ss " + i);
        Thread.sleep(2000);
        lock.lock();
        Scanner sc=new Scanner(System.in);
        System.out.println("input num ");
        int a=sc.nextInt();
        System.out.println("now will signal the waiting thread  ");
        cond.signal();//similar to notify
        try {
            for (int i = 0; i < 20; i++)
                System.out.println("hwllo  ");
            increment();
        }
        finally {
            lock.unlock();

        }
    }
    public void finished(){
        System.out.print("count is l "+ count);
    }
}
