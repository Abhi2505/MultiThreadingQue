package com.Multithreading.OutputBasedQue;

import java.util.Optional;

class Parent {
    static void check(){
        System.out.println("Parent");
    }
//    void method(int a){
//        System.out.println("a");
//    }
    void method(double a){
        System.out.println("a");
    }
}
public class Q8 extends  Parent{
 static void test(){
     System.out.println("child");
 }
 void method(double d){
     System.out.println("tea");
 }

    public static void main(String[] args) {
        Parent p=new Q8();

        ((Q8) p).test();
        new Q8().method(100);
    }
}
