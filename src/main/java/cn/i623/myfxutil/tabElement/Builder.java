package cn.i623.myfxutil.tabElement;

//创建产品对象
abstract class Builder {
    protected TabProduct tabProduct = new TabProduct();

    public abstract void buildComp();

    public abstract void buildVBox();

    public abstract void buildTab();

    public TabProduct getProduct() {
        return tabProduct;
    }
}