package com.Multithreading.ProducerConsumer;

public class SharedResource {
    boolean itemAvailable=false;
    public synchronized void addItem(){
        itemAvailable=true;
        System.out.println("item added by : "+Thread.currentThread().getName()+" and notifying all threads which are waiting");
        notifyAll();
    }
    public synchronized void consumeItem() throws InterruptedException {
        System.out.println("consumeItem method invoked by : "+Thread.currentThread().getName());

        while(!itemAvailable){
            System.out.println("Thread "+Thread.currentThread().getName()+" is waiting now");
            wait();
        }
        System.out.println("item consumed by "+Thread.currentThread().getName());
        itemAvailable=false;
    }
}
