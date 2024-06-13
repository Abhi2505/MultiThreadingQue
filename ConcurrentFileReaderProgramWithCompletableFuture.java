package com.Multithreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConcurrentFileReaderProgramWithCompletableFuture {
    private static String readFile(String filePath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder sb=new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                Thread.sleep(1000);
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    public static void main(String[] args) throws InterruptedException {
        String []filepaths={
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile1.txt",
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile2.txt",
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile3.txt"
        };
        List<CompletableFuture> futures=new ArrayList<>();
        for(String fp:filepaths){
            CompletableFuture<String>completableFuture=CompletableFuture.supplyAsync(()-> {
                try {
                    Thread.sleep(1000);
                    return readFile(fp);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            futures.add(completableFuture);
        }

        CompletableFuture<Void>allfutures=CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        //allfutures above would get the data only when all other future gets completed
        allfutures.thenAccept((res)->{
            for(CompletableFuture<String>f:futures){
                try {
                    String content=f.get();
                    System.out.println(content);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
           }

        });

        for(int i=1;i<=5;i++){
            System.out.println("Task "+i);
            Thread.sleep(2000);
        }
    }

}
