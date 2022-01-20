package cn.i623.myfxutil.tabElement;

import javafx.scene.control.Tab;
import lombok.Data;

import java.util.List;

//对应的是tab对象
@Data
public class TabProduct {
    //属性  输入输出框
    Tab tab;

    //行为
    public void put2TabList(List<Tab> tabList) {
        tabList.add(tab);
    }
}