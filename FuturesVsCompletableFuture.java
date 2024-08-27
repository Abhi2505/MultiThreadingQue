package com.Multithreading;

import java.util.concurrent.*;

public class FuturesVsCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String s1="as";
       s1="asd";
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        System.out.println("-----");
        Future<Integer>f=executorService.submit(()->{
            Thread.sleep(3000);
            for(int i=0;i<4;i++){
                System.out.println("executing in background by thread named "+Thread.currentThread().getName());
            }
            return 42;
        });

        //System.out.println(f);
        System.out.println("task submitted already and is executing in some other thread and main thread executing this line by thread named : "+Thread.currentThread().getName() );
       // int result=f.get(); //this blocks the main thread
       // System.out.println("result for the future is "+result);
        executorService.shutdown();
//        CompletableFuture<Integer>completableFuture=CompletableFuture.supplyAsync(()->{
//            try {
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return 44;
//        });
//        completableFuture.thenAccept(v->{
//            System.out.println(Thread.currentThread().getName());
//        });

        //check whether main thread is blocked during execution of above tasks
        for(int i=1;i<=5;i++){
            System.out.println(i+" task is executing by thread :"+Thread.currentThread().getName());
            Thread.sleep(1000);
        }

        //Limitations of future
        //1)when get is called on a future.object it blocks the current thread intil its execution is complete
        //2) get make handling excpetion difficult as it throws checked exception when dealing with multiple future at once
        //3) multipl operation on future object cannot be chained together.

        //completable future is introduced in java 8 util.concurent package
        //used for async programming task are run into seprate thread to avoid blocking the main app thread
        //exception can be hanfled locally within individual stages of computation or globally also for entire computation
        //allow to cancel async computation . timeout can be set to control max duration of computation
        //notified once the async compuation completes either successfully or exceptionalu

        CompletableFuture<Integer> c1=CompletableFuture.supplyAsync(()->{
            System.out.println("Task 1 Executed by thread named: "+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        },executorService);
        CompletableFuture<Integer> c2=CompletableFuture.supplyAsync(()->{
            System.out.println("Task 2 Executed by thread named: "+Thread.currentThread().getName());
            try {


                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5;
        });
        CompletableFuture<Integer> c3=CompletableFuture.supplyAsync(()->{
            System.out.println("Task 3 Executed by thread named: "+Thread.currentThread().getName());
            try {


                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 4;
        });

        CompletableFuture<Integer>combined=c1.thenCombine(c2,(i1,i2 ) ->{
            System.out.println(Thread.currentThread().getName()+" t1");
            return i1+i2;
        }).thenCombine(c3,(a, b) ->{
            System.out.println(Thread.currentThread().getName()+" t2");
            return a*b;
        }).thenApply(result->result*2).handle((r,ex)->{
            System.out.println(Thread.currentThread().getName()+" t3");
            if(ex!=null){
                System.out.println(ex.getMessage());
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return r;
        });
        for(int i=1;i<8;i++){
            System.out.println("Task executed by thread :"+i);
        }
        System.out.println(combined.get());

    }


}
