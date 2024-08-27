package com.Multithreading.ProducerConsumer;

public class Main {

    public static void main(String[] args) {
        SharedResource sharedResource=new SharedResource();
        Thread producer=new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResource.addItem();
        });
        Thread consumer=new Thread(()->{
            try {
                sharedResource.consumeItem();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
