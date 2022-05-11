package cn.i623.myspringutil.fun;

public class a {
    static Runnable codeBlock = () -> System.out.println(" Inside codeBlock");

    public static void myJavaCallback(Runnable codeBlock) {
        System.out.println("Begin calling codeBlock");
        codeBlock.run();
        System.out.println("Finished calling codeBlock");
    }

    public static void main(String[] args) {

        myJavaCallback(codeBlock);
    }
}
