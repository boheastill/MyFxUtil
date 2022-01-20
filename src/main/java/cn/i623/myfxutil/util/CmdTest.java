package cn.i623.myfxutil.util;

import java.io.IOException;
import java.io.InputStream;

public class CmdTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("cmd.exe  cd D:/coding/kafka/kafka_2.13-2.8.1/bin/windows  &  zookeeper-server-start.bat ../../config/zookeeper.properties");
        int status = process.waitFor();
        if (status != 0) {
            System.out.println(status);
            InputStream in = process.getInputStream();
//            IOUtils.copy(in, System.out);
        }
    }

}