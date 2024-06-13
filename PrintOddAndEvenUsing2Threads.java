package com.Multithreading;

public class PrintOddAndEvenUsing2Threads implements Runnable{
    static int count = 1;
    private static boolean printletter = true;
    Object obj;

    public PrintOddAndEvenUsing2Threads(Object o) {
        this.obj = o;
    }

    @Override
    public void run() {
        while (count <= 10) {
            if (count % 2 == 1 && Thread.currentThread().getName().equals("odd")) {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    count++;
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (count % 2 == 0 && Thread.currentThread().getName().equals("even")) {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    count++;
                    obj.notify();
                }
            }
        }
    }
    // print the sequence 1 A 2 B 3 C --------- 26 Z using two threads
    //print even and odd number in sequence in java

    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2=new Object();
        Runnable r1 = new PrintOddAndEvenUsing2Threads(lock2);
           Runnable r2 = new PrintOddAndEvenUsing2Threads(lock1);
        Thread e = new Thread(r1, "even");
        Thread o = new Thread(r2, "odd");
        e.start();
        o.start();
    }
}

