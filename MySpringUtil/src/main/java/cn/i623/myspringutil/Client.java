package cn.i623.myspringutil;

import java.lang.reflect.InvocationTargetException;

public class Client {
    public Client() {
        System.out.println("子类构造器");

    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("main方法");
        Sub sub = Sub.class.getDeclaredConstructor().newInstance();
        System.out.println("子类对象到手···················");
        sub.doc();
    }
}
