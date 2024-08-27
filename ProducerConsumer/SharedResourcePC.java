package com.Multithreading.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResourcePC {
    //Two threads share a common fixec size buffer as a queue
    //make sure that producer won't produce if buffer is full and consumer wont consume if buffer is empty
private Queue<Integer>shareBuffer;
private int buffersize;
public SharedResourcePC(int buffersize){
    shareBuffer=new LinkedList<>();
    this.buffersize=buffersize;
}

public synchronized  void produce(int item) throws InterruptedException {
    while(shareBuffer.size()==buffersize){
        System.out.println("buffer is full, producer is waiting for consumer");
        wait();
    }
    System.out.println(" Produced item");
    shareBuffer.add(item);
    notify();

}

public synchronized  int consume() throws InterruptedException {
    while(shareBuffer.isEmpty()){
        System.out.println("Buffer is empty consumer waiting for producer ");
        wait();
    }
    int item=shareBuffer.poll();
    System.out.println("Consumerd "+item);
    notify();
    return item;
}
}
