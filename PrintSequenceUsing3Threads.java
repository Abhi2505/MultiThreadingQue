package com.Multithreading;

public class PrintSequenceUsing3Threads implements Runnable{
    int numberupto =10;
    static int num=1;
    int remainder;
    static Object lock=new Object();
    public PrintSequenceUsing3Threads(int rem){
        this.remainder=rem;
    }
    @Override
    public void run() {
      while(num<numberupto){
        synchronized (lock){
              while(num%3!=this.remainder){
                  try {
                      lock.wait();
                      System.out.println(Thread.currentThread().getName() +"  -- -- ");
                      //while loop is required here as again after printing a value by one thread the waiting thread
                      //will get a chance and i want the correct thread waiting shouyld get the chance
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }
              }
              System.out.println(Thread.currentThread().getName() + " "+ num);
              num++;
              lock.notifyAll();
          }
      }
    }

    public static void main(String[] args) {
        PrintSequenceUsing3Threads p1=new PrintSequenceUsing3Threads(1);

        PrintSequenceUsing3Threads p2=new PrintSequenceUsing3Threads(2);

        PrintSequenceUsing3Threads p3=new PrintSequenceUsing3Threads(0);
        Thread t1=new Thread(p1,"Thread1");
        Thread t2=new Thread(p2,"Thread2");
        Thread t3=new Thread(p3,"Thread3");
        t2.start();
        t1.start();
        t3.start();


    }
}
