package cn.i623.myfxutil.multTread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class MulThreadTest {

    public static void main(String[] args) throws InterruptedException {
        //模拟参数
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
        //执行
        listMulThreadDealOld(list, (task) -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " 线程，完成任务:" + task);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return false;
        });
    }

    /**
     * 用给定的lambda 方法处理 列表中的每一个元素（非处理列表）
     * 封装了多线程实现代码
     */
    public static <T> void listMulThreadDealOld(List<T> list, MulThreadInterface mulThreadInterface) {
        long start = System.currentTimeMillis();
        if (list == null || list.size() == 0) {
            return;
        }
        ExecutorService executorService = ThreadPoolFactory.createFixedThreadPool();//线程池，队列满后加线程至最大，溢出任务在mian线程执行
        Collection<Future<?>> tasks = new LinkedList<Future<?>>();
        for (T ele : list) {
            Runnable runnable = () -> {
                eleMulThreadDeal(ele, mulThreadInterface);
            };//多个线程并行，

            Future future = executorService.submit(runnable);//非阻塞执行 （最大线程+队列长度）个任务，其他任务阻塞于此。此时线程每消费一个任务， 新非阻塞执行数量加一。
            tasks.add(future);
            System.out.println("收集任务:" + ele);
        }//循环完成后，所有任务必被收集，因为submit会在队列溢出时阻塞。
        System.out.println("future0");

        // wait for tasks completion
        for (Future currTask : tasks) {//tasks非阻塞，意味着还有runnable在执行
            try {
                currTask.get();//currTask阻塞、有序执行。意味着当前任务的runnable必然已经执行。
                System.out.println("currTask.get();");
            } catch (Throwable thrown) {
                thrown.toString();
            }
        }//tasks任务全部执行完毕
        long end = System.currentTimeMillis();
        long curTaskTime = end - start;
        System.out.println("总时间 = " + curTaskTime);
    }

    /**
     * 接受 参数 和 lambad方法，调接口来执行异步方法
     */
    private static <T> void eleMulThreadDeal(T task, MulThreadInterface mulThreadInterface) {
        boolean b = mulThreadInterface.taskDeal(task);
        System.out.println("该任务执行结果：" + b);
    }

    /**
     * 定义一个能容纳 处理单个任务 方法的空接口
     */
    @FunctionalInterface
    interface MulThreadInterface<T> {
        boolean taskDeal(T ele);
    }

    public class ThreadPoolFactory {

        /**
         * 生成固定大小的线程池
         *
         * @param
         * @return 线程池
         */
        public static ExecutorService createFixedThreadPool() {
            return new ThreadPoolExecutor(
                    // 核心线程数
                    desiredThreadNum(),
                    // 最大线程数
                    desiredThreadNum(),
                    // 空闲线程存活时间
                    60L,
                    // 空闲线程存活时间单位
                    TimeUnit.SECONDS,
                    // 工作队列
                    new LinkedBlockingQueue(8192),
                    // 线程工厂
//                r -> new Thread(r, "PST-" + threadNumber.getAndIncrement() + ""),
                    r -> new Thread(r, "xxl-job, admin JobLosedMonitorHelper-callbackThreadPool-" + r.hashCode()),
                    // 拒绝策略
                    new ThreadPoolExecutor.CallerRunsPolicy());
        }

        /**
         * 理想的线程数，使用 2倍cpu核心数
         */
        public static int desiredThreadNum() {
            int i = Runtime.getRuntime().availableProcessors() * 2;
            return i;
        }
    }
}
