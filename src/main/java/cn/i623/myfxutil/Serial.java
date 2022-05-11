package cn.i623.myfxutil;

import java.io.*;

/*
 * 序列化方法
 * 反序列化方法
 * 实现 Serializability 父类的POJO,
 * POJO包含： private static final long serialVersionUID = 1L;
 * */
public class Serial implements Serializable {
    public static void main2(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream("tmp");
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject("xsEleAddInfo");

        s.flush();
        System.out.println("序列号完成");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream("tmp");
        ObjectInputStream s = new ObjectInputStream(in);
        String xsEleAddInfo = (String) s.readObject();

        System.out.println(xsEleAddInfo);
    }
}
