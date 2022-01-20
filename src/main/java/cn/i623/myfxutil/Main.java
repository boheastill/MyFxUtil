package cn.i623.myfxutil;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("舞台标题。。");

        // VBox
        VBox vb = new VBox();
        vb.setPadding(new Insets(100, 500, 50, 50));//高宽
//    vb.setSpacing(10);

        Label label = new Label("标签");
        label.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));

        vb.getChildren().add(label);

        // Buttons
        Button btn1 = new Button();
        btn1.setText("按钮");
        vb.getChildren().add(btn1);

        // Adding VBox to the scene
        Scene scene = new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}




