package cn.i623.myfxutil.util;

import java.io.IOException;

public class KafkaStart {
    public static void main(String[] args) throws IOException {
//        shutdownMin();
        start("D:\\coding\\kafka\\kafka_2.13-2.8.1\\bin\\windows");
    }

    public static void start(String path) throws IOException {
        //取消关机
        Runtime.getRuntime().exec("cd " + path);
        Runtime.getRuntime().exec("zookeeper-server-start.bat ..\\..\\config\\zookeeper.properties");
        Runtime.getRuntime().exec("kafka-server-start.bat ..\\..\\config\\server.properties");
        Print.p("启动成功.");
    }

}
