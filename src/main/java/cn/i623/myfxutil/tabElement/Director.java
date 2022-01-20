package cn.i623.myfxutil.tabElement;

//产品组装并返回
public class Director {
    private final Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    //   执行构建逻辑，构建过程产品对象tab属性有了值，获取产品
    public TabProduct buildProduct() {
        builder.buildComp();
        builder.buildVBox();
        builder.buildTab();
        return builder.getProduct();
    }
}