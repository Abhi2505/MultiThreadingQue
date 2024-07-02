package com.Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
  //  private static volatile int sharedcounter=0;
    //during compund operation on shared variable then we have to use atomic and if you use synchronized perfomance is impacted
      private static AtomicInteger atomicint =new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {

        Runnable  task=()->{
            for(int i=1;i<=100000;i++){
               atomicint.incrementAndGet();
            }





        };



        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicint.get());

        //volatile -
        //ensures visibliy of changes to variable across threads
        //applicaable to variable only
        //implements a non blocking algo
        //performance is high wrt synchronized keyword

//        synchronized
//        provides mutual exclusion and ensures both visibiity and atmoicity
//    applicable to only block or methods
//    implement a lock based algo
//    performance is low compare to volatile and atomc keyword due to lock

//        Atomic
//    Provides atomic operaiton for variables without using locks
//    applicable to variables only
//   implements non blocking algo
//   performance high wrt to volatile and sync keyword

    }




}
