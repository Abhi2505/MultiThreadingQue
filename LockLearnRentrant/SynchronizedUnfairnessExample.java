package com.Multithreading.LockLearnRentrant;

public class SynchronizedUnfairnessExample {
    private static final Object lock = new Object();
//You may see the same thread acquiring the lock multiple times in a row,
// and other threads might starve (wait for long periods) because there is no guarantee of fairness.
//You may notice that Thread-1 acquired the lock twice consecutively before Thread-3 could acquire it.
// this behaviour shows the lack of fairness in the synchronized keyword
//The synchronized keyword does not guarantee fairness in terms of which thread gets to execute the
//critical section next. This can lead to scenarios where certain threads may be favored over others,
//causing potential starvation of other threads.
    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " acquired the lock");
                    try {
                        // Simulate some work
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " released the lock");
                }
            }
        };

        // Creating multiple threads
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        t1.start();
        t2.start();
        t3.start();
    }
}
