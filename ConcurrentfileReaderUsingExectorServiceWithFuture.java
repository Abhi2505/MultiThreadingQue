package com.Multithreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentfileReaderUsingExectorServiceWithFuture {

    private static String readFile(String filePath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder sb=new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                Thread.sleep(2000);
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String []filepaths={
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile1.txt",
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile2.txt",
                "C:/Users/AACS/IdeaProjects/Stream/src/com/Multithreading/samplefile3.txt"
        };
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        List<Future>futures=new ArrayList<>();
        for(String fp:filepaths){
            Future<String>future=executorService.submit(()->readFile(fp));
            System.out.println(future);
            futures.add(future);
        }

        for(Future<String> f:futures){
            while(!f.isDone()){
                Thread.sleep(2000);
                System.out.println("Future not done");
            }

//            for(int i=0;i<8;i++){
//                System.out.println("This is task executed by thread : "+Thread.currentThread().getName());
//            }
            String content=f.get();// get call is blocking which means it waits until the task is
            // completed and can make the application less responsive.
            System.out.println(content);
        }
        for(int i=1;i<10;i++){
            System.out.println("task "+i);
        }
    }

}
