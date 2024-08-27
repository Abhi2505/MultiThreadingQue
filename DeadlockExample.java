package com.Multithreading;

public class DeadlockExample {
    public static void main(String[] args) {
        Object akey=new Object();
        Object bkey=new Object();
        Thread t1=new Thread(()->{
            synchronized (bkey){
                System.out.println("a has acquired key of b");
                try{
                System.out.println("a is sleeping for 3 sec");
                Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("a woke up");
                synchronized (akey){
                    System.out.println("a got his key");
                }
            }
        });

        Thread t2=new Thread(()->{
            synchronized (akey){
                System.out.println("b has acquired key of a");
                try{
                    System.out.println("b is sleeping for 3 sec");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("b woke up");
                synchronized (bkey){
                    System.out.println("b got his key");
                }
            }
        });

        t1.start();
        t2.start();
        // to check the dead lock using cmd use jstack with process id which u can get by seeing using ctrl+shift+esc  for the javaa
        //file executed from cmd

        //if in 2nd sync code if we change the order of lock as first bky and then aky then theri wont be any deadlock

    }
}
