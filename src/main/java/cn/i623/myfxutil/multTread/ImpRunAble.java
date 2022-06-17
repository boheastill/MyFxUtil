package cn.i623.myfxutil.multTread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RunnableDemo implements Runnable {
    private Thread t;
    private final String threadName;

    RunnableDemo(String name) {
        threadName = name;
//      System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 40; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(60);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
//      System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        } else {
            System.out.println("线程有");
        }
    }
}

public class ImpRunAble {

    public static void main (String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, "a", "b", "c", "d", "e");
       for (String x : list) {
          RunnableDemo R1 = new RunnableDemo(x);
          R1.start();//让逻辑异步的执行b'g
            Thread.sleep(1000);
          System.out.println("已经开启" + x);
       }
       System.out.println("mian方法完成");

    }
}