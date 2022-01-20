package cn.i623.myfxutil.domain;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

public class UnicodeTab {


    //6.页签
    private void addUnicodeTab(List<Tab> tabList) {
        // VBox
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(100, 500, 50, 50));//高宽
        vBox.setSpacing(10);

        Label label = new Label("输入字符");
        TextField textField = new TextField();
        TextArea textArea = new TextArea();
//        label.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));

        // Buttons
        Button button = new Button();
        button.setText("查询");

        vBox.getChildren().add(label);
        vBox.getChildren().add(button);
        vBox.getChildren().add(textField);
        vBox.getChildren().add(textArea);

        Tab tab = new Tab();
        tab.setContent(vBox);
        tab.setText("unicode查询");
        tabList.add(tab);
    }
}
