package com.Multithreading.LockingLearn;

class ThreadDemo extends Thread {
    //if both threads acting on same object and  if we use synchronzied keyword
    //on an instance method then object level lock is acquired by the current thread.
    //and only after its execution the other thread will get the chance
    //If method is a static method then class level lock is acquired by the current thread and the other thread would
    //get a chance only after the current completes however the thread can execute the other instance method while the other
    // thread is executing static method.


    ObjLock1 obj;
    public ThreadDemo(ObjLock1 o) {
        this.obj=o;
    }

    @Override
    public void run() {
        try {
            ObjLock1.m3();
            ObjLock1.m4();
            obj.m1();
            obj.m2();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("try catch");
    }
}
public class ObjLock1 {
    public synchronized void m1() throws InterruptedException {
        System.out.println("Inside instance method m1 by thread "+Thread.currentThread().getName());
        for(int i=0;i<6;i++){
            Thread.sleep(2000);
            System.out.println(i+" "+Thread.currentThread().getName());
        }
    }

    public synchronized void m2(){
        System.out.println("Im in instance method m2 "+Thread.currentThread().getName());
    }
    public static synchronized void m3(){
        for(int i=0;i<3;i++){
            System.out.println(" im static method m3 "+Thread.currentThread().getName());
        }
    }
    public static synchronized void m4() throws InterruptedException {
        for(int i=0;i<4;i++){
            Thread.sleep(2000);
            System.out.println(" im static method m4 "+Thread.currentThread().getName());
        }
    }
    public static void main(String[] args) {
        ObjLock1 l1=new ObjLock1();
        ThreadDemo t1=new ThreadDemo(l1);
        ThreadDemo t2=new ThreadDemo(l1);
        t2.start();
        t1.start();
    }
}