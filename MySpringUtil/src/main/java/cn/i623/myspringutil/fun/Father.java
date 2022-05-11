package cn.i623.myspringutil.fun;

public class Father extends GrandFater {

    void doConvert() {
        System.out.println("父类do");

        for (int i = 0; i < 5; i++) {
            super.doConvert();
        }
        System.out.println("父类do执行完毕");

    }

}
