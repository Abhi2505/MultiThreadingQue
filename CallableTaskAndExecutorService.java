package com.Multithreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

class  StockPriceFetcherTask implements Callable<Double>{
    private String stockSymbol;
    public StockPriceFetcherTask (String stocksymbol){
        this.stockSymbol=stocksymbol;
    }
    //when we submit the object to the executor service then call method is called
    @Override
    public Double call() throws Exception {
        Thread.sleep(5000);
        return Math.random()*1000;
    }
}
public class CallableTaskAndExecutorService {


    //callable future code demo

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //callable is a functional interface in java util.concurrent package
        //it represent a task that can be executed async and return a result
        //just like runnable it represnt a unit of work but it can return a result or throw an exception
        //callable task are submitted to an executor service for execution and it returns a future object that can be used
        //to return the result or handle exception

        //why to use
        //as it return a result . Crucial when u need output of a task such as a computation result, db query
        //callable can throw checked exceptions and runnable not allow
        //when u have multiple task that run concurenlty and u wnat to collect their results efficiently,callable
        //combined with the executor service makes it easier to manage concurrent computations and retrive resilt


//Future is a java interface in util.concurent package . it represent the result of async computation that may not be immediatly available
//returned when callable task submitted to executor service.
// methods get(): retrieve the result or wait for task to complete
//isDone():check if task is completed
//cancel:cancel task if not started


        List<String> stocksymbols = Arrays.asList("AdaniPower", "IRFC", "IREDA","LTI","Infosys");

        List<Future> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (String stock : stocksymbols) {
            StockPriceFetcherTask stockPriceFetcherTask = new StockPriceFetcherTask(stock);
            Future<Double> future=executorService.submit(stockPriceFetcherTask);
            System.out.println(future);
            futures.add(future);
        }
        executorService.shutdown();
        for(int i=0;i<5;i++){
            while(!futures.get(i).isDone()){
                Thread.sleep(2000);
                System.out.println("Waiting....");
            }
            System.out.println("price for stock "+stocksymbols.get(i)+" = "+futures.get(i).get());
        }
    }




}
