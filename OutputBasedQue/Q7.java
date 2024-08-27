package com.Multithreading.OutputBasedQue;

public class Q7 extends Thread{
    public  void run(){
        System.out.println("run");
    }

    public static void main(String[] args) {
        System.out.println("hello");
        Q7 q1=new Q7();
        q1.start();
        q1.start();
        System.out.println("bye");
    }
}
