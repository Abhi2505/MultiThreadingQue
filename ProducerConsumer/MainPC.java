package com.Multithreading.ProducerConsumer;

public class MainPC {
    public static void main(String[] args) {
        SharedResourcePC sharedResourcePC=new SharedResourcePC(3);
        Thread producer=new Thread(()->{
            for(int i=1;i<=6;i++){
                try {
                    sharedResourcePC.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread consumer=new Thread(()->{
            for (int i=1;i<=6;i++){
                try {
                    sharedResourcePC.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producer.start();
        consumer.start();
    }
}
