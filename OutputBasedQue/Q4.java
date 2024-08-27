package com.Multithreading.OutputBasedQue;

public class Q4 implements Runnable{
    int x;
    int y;
    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            synchronized (this){
                x=12;
                y=12;
            }
            System.out.println(x+" "+y);
        }
    }

    public static void main(String[] args) {
        Q4 q4=new Q4();
        Thread t1=new Thread(q4);
        Thread t2=new Thread(q4);
        t1.start();
        t2.start();
    }
}
