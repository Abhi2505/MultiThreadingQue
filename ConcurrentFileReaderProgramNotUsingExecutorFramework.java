package com.Multithreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//our task is to develop a generic concurrent file reader program that can read data from mulitple text file concurrrently
//using java thread pool and executor framework
public class ConcurrentFileReaderProgramNotUsingExecutorFramework {
    private static void readFile(String filePath) throws FileNotFoundException {
        BufferedReader br=new BufferedReader(new FileReader(filePath)) ;
        try{
            String line;
            while((line=br.readLine())!=null){
                System.out.println(Thread.currentThread().getName()+" : reads line "+line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //callable is a functional interface in java util.concurrent package
        //it represent a task that can be executed async and return a result
        //just like runnable it represnt a unit of work but it can return a result or throw an exception
        //callable task are submitted to an executor service for execution
        String filepath1="C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile1.txt";
        String filepath2="C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile2.txt";

        Thread file1Thread=new Thread(()-> {
            try {
                readFile(filepath1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Thread file2Thread=new Thread(()-> {
            try {
                readFile(filepath2);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        file1Thread.start();
        file2Thread.start();
        try{
            file1Thread.join();
            file2Thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Filer reading completed");


        //there is some limitation of above way of executing

        //1)Manual creation of thread suppose if u dont know how many files you have to read and u reading from aws
        //s3 location in that situation how we know how many threads we need
        //2)Manual start of thread
        //all lifecyle management here is done by developer

        //to avoid this executor framework came into picture
        //executor framework helps in efficient managing thread lifecycle (creating,start,closing)

        //Core componentws
        //Executor Interface
        //1) contains a single method void execute(Runnable tasks)

        //SubExecutor Interface (executor service)
        //1) sub interface of executor
        //2)provide a higher level api for managing thread execution
        //3)Offers method for task submission ,termination and more.

        //Thread pool executor class
        //1) the most commonly used implementation of executor service
        //2) manage the pool of worker threads
        //3) Allows customization of thread ppole size

        //Executor class
        //1) a utility class that provide factory methods for creating different types of executor service instances

        //Executor lifecycle
            //task submitted
        //1) task are submitted to executor service
        //2) can be represented as runnable or callable objects

            //task queuing
        //1) tasks are stored in a queue until a worker thread becomes available.

        //thread execution
        //1) woker thread execute the task concurrently from the queue

        //thread termination
        //1) ExecutorService manages the lifecycle of worker threads
        //2) threads are reused if more tasks are available.

        //Thread pool is collection of preinitialized thread that are ready to execute the tasks instead of creating a new thread for every task
        //u can borrow from the pool and the return back to ppol when done . Can adopt to changing workload by dynamically resizing of pool
        //used

    }




    




}
