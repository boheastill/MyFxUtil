package cn.i623.myspringutil.fun;

public class Sub extends Father {
    public static void main(String[] args) {
        GrandFater father = new Sub();
        father.doConvert();
    }
//    void doConvert() {
//        System.out.println("子类do");
//        for (int i = 0; i < 5; i++) {
//
//            super.doConvert();
//        }
//    }

}
