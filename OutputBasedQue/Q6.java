package com.Multithreading.OutputBasedQue;

public class Q6 {
    public static int method(){
        try{
            return 1;
        }
        catch (Exception e){
            return 2;
        }
        finally {
            return 4;
        }
    }
    public static void main(String[] args) {
        System.out.println(method());
    }
}
