package cn.i623.myfxutil.multTread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工厂工具
 */
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