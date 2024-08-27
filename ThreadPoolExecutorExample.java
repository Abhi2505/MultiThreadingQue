package com.Multithreading;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,3,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(2),new CustomFactory(),new CustomRejectHandler());
        for(int i=1;i<=6;i++){
            executor.submit(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task processed by "+Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}

//here if have four task two are handled by min size threads and the remaining two task are been put into the queue
//so if another new task comes then since maxpool size is 4  so now the third thread will be used as queue is full
class CustomFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread th=new Thread(r);
        th.setPriority(Thread.NORM_PRIORITY);
        th.setDaemon(false);
        return th;
    }
}
class CustomRejectHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected "+r.toString());
    }
}
