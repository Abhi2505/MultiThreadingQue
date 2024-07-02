package com.Multithreading;

public class ThreadLifeCycle {
    //the lifecycle of thread is
    //while created - new state
    //when started - ready to run state
    //when started running - running state  - while running if thread goes to waiting state - goes to block for acquistion state
    //when run completed - move to dead state
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            try {
                Thread.sleep(1);
                for(int i=1000;i>0;i--);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"States");
        t.start();
        while(true){
            Thread.State state=t.getState();
            System.out.println(state);
            if(state==Thread.State.TERMINATED)break;
        }
        //when we call yield on running thread it moves to ready to run state
    }
}
