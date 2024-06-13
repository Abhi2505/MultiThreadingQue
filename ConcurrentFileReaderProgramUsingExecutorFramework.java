package com.Multithreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentFileReaderProgramUsingExecutorFramework {
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
        String []filepaths={
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile1.txt",
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile2.txt",
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile3.txt"
        };
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        for(String fp:filepaths){
            executorService.execute(()-> {
                try {
                    readFile(fp);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
        System.out.println("executed");
    }
}
