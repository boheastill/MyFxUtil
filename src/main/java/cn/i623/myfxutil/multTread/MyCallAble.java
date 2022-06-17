package cn.i623.myfxutil.multTread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallAble {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft = new FutureTask<>(() -> {
            int i = 0;
            for (; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "遍历 " + i);
            }
            return i;
        });
//        FutureTask<Integer> ft = new FutureTask<>(new MyCallAble());
        new Thread(ft, "有返线程").start();
        System.out.println("子线程的返回值：" + ft.get());//子线程未返回 ，疑似阻塞。
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "遍历" + i);
        }
    }
}