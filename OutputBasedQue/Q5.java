package com.Multithreading.OutputBasedQue;

public class Q5 {
    Integer id;
    Q5(int id){
        this.id=id;
    }

    public static void main(String[] args) {
        Q5 a=new Q5(3);
        Q5 b=new Q5(3);
        System.out.println(a==b);
        System.out.println(a.equals(b));

    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.id).equals(((Q5)obj).id);
    }
}
