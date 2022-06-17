package cn.i623.myfxutil.multTread;

public class Main {
  public static void main(String[] argv) {
    engine((x,y)-> x + y);//传给工具类 方法。工具类通过调接口，执行该方法
    engine((x,y)-> x * y);
    engine((x,y)-> x / y);
    engine((x,y)-> x % y);
  }
  private static void engine(Calculator calculator){//接口的方法体

    int result = calculator.calculate(3,5);//调用接口
    System.out.println(result);
  }
}

@FunctionalInterface
interface Calculator{
  int calculate(int x, int y);
}