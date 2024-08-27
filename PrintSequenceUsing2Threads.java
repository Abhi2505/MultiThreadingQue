package com.Multithreading;

public class PrintSequenceUsing2Threads {
    private static boolean printletter=false;
    private static final Object lock=new Object();

    // print the  sequence 1 A 2 B 3 C --------- 26 Z using two threads
    public static void main(String[] args) throws InterruptedException {
        Runnable letterTask = new Runnable() {
            @Override
            public void run() {
                for (char ch = 'A'; ch <= 'Z'; ch++) {
                   // System.out.print(ch + " ");
                    synchronized (lock) {
                        try {
                            if (!printletter) {
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.print(ch + " ");
                        printletter = false;
                        lock.notify();
                    }

                    // Thread.sleep(1000);
                }
            }
        };


        Runnable numberTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 26; i++) {
                    //System.out.print(ch+" ");
                    synchronized (lock) {
                        try {
                            if (printletter) {
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.print(i + " ");
                        printletter = true;
                        lock.notify();

                    }
                    // Thread.sleep(1000);
                }
            }
        }

            ;

            Thread letterThread = new Thread(letterTask);
            Thread numberThread = new Thread(numberTask);
     numberThread.start();
     letterThread.start();
    letterThread.join();
     numberThread.join();
     System.out.println(" main done");
        }
}
