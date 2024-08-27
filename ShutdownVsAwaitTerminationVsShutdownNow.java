package com.Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownVsAwaitTerminationVsShutdownNow {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex= Executors.newFixedThreadPool(5);
        ex.submit(()->{
            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task completed by "+Thread.currentThread().getName());
        });
        //ex.shutdown();
        ex.shutdownNow();
        //even after shutting down task would continue
        //boolean isTerminated=ex.awaitTermination(2, TimeUnit.SECONDS); //calling thread ie the main thread is blocked
        //for 2 seconds till ex service is shutdown completely by completing task if it does not return faslse;
        //await termination is used when u have a use case where u have to perform a task after shutdown
       // System.out.println("isTerminated "+isTerminated);
        System.out.println("Main thread completed "+Thread.currentThread().getName());
    }

}
