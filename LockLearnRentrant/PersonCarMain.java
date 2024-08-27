package com.Multithreading.LockLearnRentrant;

public class PersonCarMain {
//    ReentrantLock
//    Advantages:
//   1 Advanced Features:
//
//    Offers advanced synchronization features such as timed lock waits, interruptible lock waits, and condition variables.
//   2 Fairness Policy:
//
//    Can be configured with a fairness policy to ensure that threads acquire the lock in a fair manner.
//   3 Flexibility:
//    More flexible locking mechanisms, suitable for complex synchronization requirements.
    public static void main(String[] args) {


        Car c=new Car();


        Person c1=new Person(c,"fast");


        Person c2=new Person(c,"slow");
        c1.start();
        c2.start();

    }
}
