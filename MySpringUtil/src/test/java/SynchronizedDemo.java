public class SynchronizedDemo {
    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.method();
    }

    public void method() {
        synchronized (this) {
            System.out.println("synchronizedCODE");
        }
    }
}