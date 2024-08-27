package com.Multithreading;

import java.util.concurrent.RecursiveTask;

public class ComputeSumTaskForkJoinPool extends RecursiveTask<Integer> {
    int start;
    int end;
    ComputeSumTaskForkJoinPool(int st,int ed){
        this.start=st;
        this.end=ed;
    }


    @Override
    protected Integer compute() {
        int tsum=0;
        if(end-start<=4){
            for(int i=start;i<=end;i++){
                tsum+=i;
            }
            return tsum;
        }
        else{
            int mid=(start+end)/2;
            ComputeSumTaskForkJoinPool lefttask=new ComputeSumTaskForkJoinPool(start,mid);
            ComputeSumTaskForkJoinPool righttask=new ComputeSumTaskForkJoinPool(mid+1,end);

            lefttask.fork();
            righttask.fork();
            int leftResult=lefttask.join();
            int righResult=righttask.join();
            return leftResult+righResult;
        }
    }


}
