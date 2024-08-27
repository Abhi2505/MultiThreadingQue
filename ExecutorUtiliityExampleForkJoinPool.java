package com.Multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ExecutorUtiliityExampleForkJoinPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // Executors.newWorkStealingPool();
        ForkJoinPool pool=ForkJoinPool.commonPool();
        Future<Integer>fo=pool.submit(new ComputeSumTaskForkJoinPool(0,100));
        System.out.println(fo.get());
    }
}
