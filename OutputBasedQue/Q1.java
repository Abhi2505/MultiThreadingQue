package com.Multithreading.OutputBasedQue;

public class Q1 extends Thread{
    Q1(){
        System.out.println("ex");
    }

    public void run(String s){
        System.out.println("bac");
    }
    @Override
    public void run(){
        System.out.println("Bar");
    }

    public static void main(String[] args) {
        Q1 q1=new Q1(){
            public void run() {
             System.out.println("foo");
            }
        };
        q1.start();
    }
    //whenever an object is created then its parent default constructor is called
    //anonyms run class is called first on starting of the thread

}
