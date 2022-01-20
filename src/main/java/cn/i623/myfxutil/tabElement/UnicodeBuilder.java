package cn.i623.myfxutil.tabElement;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

//产品所需要的所有零件创建逻辑
public class UnicodeBuilder extends Builder {

    Button button;
    Label label;
    TextField textField;
    TextArea textArea;

    VBox vBox;

    public void buildComp() {
        button = new Button();
        button.setText("查询");
        label = new Label("输入字符");
        textField = new TextField();
        textArea = new TextArea();
    }

    public void buildVBox() {
        vBox = new VBox();
        vBox.setPadding(new Insets(100, 500, 50, 50));//高宽
        vBox.setSpacing(10);
        // Buttons
        vBox.getChildren().add(label);
        vBox.getChildren().add(button);
        vBox.getChildren().add(textField);
        vBox.getChildren().add(textArea);
    }

    public void buildTab() {
        Tab tab = new Tab();
        tab.setContent(vBox);
        tab.setText("unicode查询");
        tabProduct.setTab(tab);
    }
}