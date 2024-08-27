package com.Multithreading.OutputBasedQue;

public class Q3 extends Thread{
    public void start(){
        System.out.println("start");
    }

    @Override
    public void run() {
        System.out.println("Run");
    }

    public static void main(String[] args) {
        Q3 a=new Q3();
        Q3 b=new Q3();
        a.start();
        b.start();
    }
}
