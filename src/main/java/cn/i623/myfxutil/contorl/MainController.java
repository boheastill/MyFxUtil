package cn.i623.myfxutil.contorl;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("欢迎访问fx页面233!");
    }
}