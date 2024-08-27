package com.Multithreading.LockLearnRentrant;

public class Person extends Thread{
    String drivingspeed;
    Car c;
    Person(Car c, String name){
        this.drivingspeed=name;
        this.c=c;
    }


    @Override
    public void run() {
        try {
            c.drive(drivingspeed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
