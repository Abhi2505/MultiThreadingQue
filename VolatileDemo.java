package com.Multithreading;

public class VolatileDemo {
    static volatile boolean  flag=false;

    public static void main(String[] args) {
        Thread writer=new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag=true;
            System.out.println("flag has been set to true "+ flag);
        });

        Thread reader=new Thread(()->{
            while(!flag){
                //wait here till flag is true
            }
            System.out.println("flag is now true");
        });
        writer.start();
        reader.start();

        //we can see even though the writer thread had updated the value the reader is not able
        //to read that flag value as it is reading from local cache .
        //if we change to volatile then we can prevent that.
        //to ensure that changes made by one thread to a shared variable are immediatly visinle to tother threads without
        //the need for explicit locking mechnaism
        //when the varible is read frequently but modifies less frequently then we use volatile keyword
    }

}
