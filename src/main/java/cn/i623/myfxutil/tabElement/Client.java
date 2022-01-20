package cn.i623.myfxutil.tabElement;

public class Client {

    public static void main(String[] args) {

        TabProduct tabProduct = new Director(
                new UnicodeBuilder()//产品的方法移到bud里面
        ).buildProduct();//对builder进行组装

        tabProduct.put2TabList(null);
    }

}