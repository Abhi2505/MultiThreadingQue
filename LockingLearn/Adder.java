package com.Multithreading.LockingLearn;

public class Adder implements Runnable{
    int st=0;
    int ed=0;
    int cnt=0;
    public Adder(int st, int ed,int c) {
        this.st = st;
        this.ed = ed;
        this.cnt=c;
    }

    @Override
    public void run() {
        for(int i=this.st;i<=this.ed;i+=1){
            this.cnt+=i;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Adder ad1=new Adder(1,50,0);
        Adder ad2=new Adder(1
                ,100,0);
        Thread t1=new Thread(ad1);
        Thread t2=new Thread(ad2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        //so to prevent main thread exeucting before t1 and t2 to get correct output we can use join method
        System.out.println(ad1.cnt);
        System.out.println(ad2.cnt);

    }

}
