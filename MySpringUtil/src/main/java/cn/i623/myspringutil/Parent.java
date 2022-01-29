package cn.i623.myspringutil;

public class Parent {


    void model() {
        System.out.println("父类model");

    }

     void doc() {
        System.out.println("父类doc");
        model();
    }
}
