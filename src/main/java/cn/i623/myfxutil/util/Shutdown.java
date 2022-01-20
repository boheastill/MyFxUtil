package cn.i623.myfxutil.util;

import java.io.IOException;

public class Shutdown {
    public static void main(String[] args) throws IOException {
//        shutdownMin();
        quitShutdown();
    }

    public static void quitShutdown() throws IOException {
        //取消关机
        Runtime.getRuntime().exec("shutdown -a");
        Print.p("the shutdown has been cancelled.");
    }

    public static void exeShutdown(Integer seconds) throws IOException {
//        Scanner input = new Scanner(System.in);
//        String min = input.nextLine();
//        Windows64位的关机命令：shutdown - s - t[秒数]
        Runtime.getRuntime().exec("shutdown -s -t " + seconds);
        Print.p("it will shutdown after " + seconds + "s.");
    }
}
