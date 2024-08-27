package com.Multithreading;

public class PrintSequenceOdd_EvenUsing2Threads implements Runnable {
PrintSequenceOdd_EvenUsing2Threads(int e){
    this.reminder=e;
}

   int reminder=0;
   static Object lock=new Object() ;
    static int counter=1;
    static boolean printodd=true;
    public static void main(String[] args) {
        PrintSequenceOdd_EvenUsing2Threads e=new PrintSequenceOdd_EvenUsing2Threads(0);
        PrintSequenceOdd_EvenUsing2Threads o=new PrintSequenceOdd_EvenUsing2Threads(1);
        Thread even=new Thread(e);
        Thread odd=new Thread(o);
        even.start();
        odd.start();
    }
    public  void printdata() throws InterruptedException {
        synchronized (lock) {
            while (counter <= 100) {
                while(counter%2!=this.reminder){
                       // System.out.println("current thread is "+Thread.currentThread().getName());
                        lock.wait();
                }
                System.out.println(counter+" "+Thread.currentThread().getName());
                counter++;
                lock.notifyAll();
            }
        }
    }
    @Override
    public void run() {
        try {
            printdata();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
