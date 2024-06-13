package com.Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounterClass {
static int counter=0;
    public static void main(String[] args) {
        AtomicInteger counter=new AtomicInteger(0);
        //if we do it with static variable then for the larger inputs we won't be getting counter as zero at the end
        //earlier the increment and decrement happpens in three steps read ,modify and write

        Runnable incrementalTask=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                   counter.incrementAndGet();
                    //counter++;
                }
            }
        };

        Runnable decrementalTask=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
//                    counter--;
                    counter.decrementAndGet();
                }
            }
        };
        Thread inc=new Thread(incrementalTask);
        Thread dec=new Thread(decrementalTask);
        inc.start();
        dec.start();
        try{
            inc.join();
            dec.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("count "+ counter);

    }
}
