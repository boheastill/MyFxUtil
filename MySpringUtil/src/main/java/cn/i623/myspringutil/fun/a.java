package cn.i623.myspringutil.fun;

public class a {
    static Runnable codeBlock = () -> System.out.println(" Inside codeBlock");

    public static void myJavaCallback(Runnable codeBlock) {
        System.out.println("Begin calling codeBlock");
        codeBlock.run();
        System.out.println("Finished calling codeBlock");
    }

    public static void main(String[] args) {
        double oneMan = 0.05  * 6;
        System.out.println(oneMan);
        System.out.println(oneMan*1000);
        myJavaCallback(codeBlock);
    }

}
