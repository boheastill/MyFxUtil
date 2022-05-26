package cn.i623.myspringutil.huibian;

import java.util.Arrays;

public class LifeCycle {
    // ᶉாં௔
    private static final String staticField = getStaticField();

    static {
        System.out.println(staticField);
        System.out.println("静态代码块");
    }

    // ฦ᭗ં௔
    private final String field = getField();

    {
        System.out.println(field);
    }

    // ຅᭜ڍහ
    public LifeCycle() {
        System.out.println("构造函数");
    }

    public static String getStaticField() {
        String statiFiled = "静态字段";
        return statiFiled;
    }

    public static String getField() {
        String filed = "字段";
        return filed;
    }

    // Ԇڍහ
    public static void main(String[] argc) {
        LifeCycle lifeCycle = new LifeCycle();
        lifeCycle.add();
        Integer[] array = new  Integer[]{1,2,3,4};

        System.out.println(Arrays.toString(array
        ));
    }

    void add() {
        System.out.println("方法");
    }
}