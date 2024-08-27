package com.Multithreading.LockLearnRentrant;

public class EXtwo {
    public static void main(String[] args) throws InterruptedException {
        final EXtwoRunner eXtwoRunner=new EXtwoRunner();
        Thread t1=new Thread(()-> {
            try {
                eXtwoRunner.firstThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2=new Thread(()-> {
            try {
                eXtwoRunner.secondThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        eXtwoRunner.finished();
    }
}
