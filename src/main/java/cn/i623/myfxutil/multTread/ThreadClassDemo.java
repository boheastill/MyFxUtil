package cn.i623.myfxutil.multTread;

// 文件名 : ThreadClassDemo.java
public class ThreadClassDemo {

    public static void main(String[] args) {
//        Runnable hello = new DisplayMessage("打招呼");
        Runnable hello = () -> {
            while (true) {
                System.out.println("打招呼");
            }
        };
        Thread helloTherd = new Thread(hello);
        helloTherd.setDaemon(true);
        helloTherd.setName("hello");
        System.out.println("Starting hello thread...");
        helloTherd.start();

        Runnable bye = () -> {
            while (true) {
                System.out.println("告别");
            }
        };
        Thread byeThread = new Thread(bye);
        byeThread.setPriority(Thread.MIN_PRIORITY);
        byeThread.setDaemon(true);
        System.out.println("Starting goodbye thread...");
        byeThread.start();

        System.out.println("Starting thread3...");
        Thread thread3 = new GuessANumber(27);

        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
        System.out.println("Starting thread4...");
        Thread thread4 = new GuessANumber(75);

        thread4.start();
        System.out.println("main() is ending...");
    }
}