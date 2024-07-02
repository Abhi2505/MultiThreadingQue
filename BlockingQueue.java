package com.Multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private Queue<Integer> q;
    private int capacity;
    public BlockingQueue(int cap){
        q=new LinkedList<>();
        capacity=cap;
    }
    //scenario : if capacity is full add is called so it goes to waiting state now again one more thread comes
    //for add so that thread also goes tp waiting now remove thread comes and after remove notifies other two thread
    //so one of them get chance and add and notify other waiting thread so the last adder thread will get the chance
    //so it execute from the same point and add even though the capacity is full so therfore we have to use the while
    //method to again do the checking
    public boolean add(int item) throws InterruptedException {
        synchronized (q){
            while(q.size()==capacity) {
                q.wait();//release the lock on q once wait is called
            }
            q.add(item);
            q.notifyAll();
            return true;
        }
    }
    public int remove() throws InterruptedException {
        synchronized (q){
            while(q.size()==0){
                q.wait();
                //after wake up thread start from here only
                //other thread got a chance to acess other synchronized instance method
            }
            int val= q.remove();
            q.notifyAll();
            return val;
        }
    }

    //thread .sleep does not release the lock

}
