package com.Multithreading.OutputBasedQue;

class Example extends Thread{
    Q2 q2;
    public Example(Q2 q2){
        this.q2=q2;
    }
    public void run(){
        q2.doSomething();
    }
}
public class Q2 {
    private int count=1;
    public synchronized  void doSomething(){
        for(int i=0;i<10;i++){
            System.out.println(count++);
        }
    }

    public static void main(String[] args) {
        Q2 q2=new Q2();
        Thread a1=new Example(q2);
        Thread a2=new Example(q2);
        a1.start();
        a2.start();
    }
}
