package cn.i623.myfxutil.contorl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static cn.i623.myfxutil.util.Tjlog.exeM;


public class TabController {

    //label
    @FXML
    private Label contain;
    @FXML
    private Label start;
    @FXML
    private Label end;
    @FXML
    private Label path;
    @FXML
    private Label outTitle;
    @FXML
    private Label out;
    //    文本
    @FXML
    private TextField containText;
    @FXML
    private TextField startText;
    @FXML
    private TextField endText;
    @FXML
    private TextField pathText;
    @FXML
    private TextField outPathText;
    @FXML
    private TextArea outText;
    //按钮
    @FXML
    private Button exeButton;
    @FXML
    private VBox timebox;          //填写时间的板块

    //    方法
    @FXML
    protected void onExeButtonClick() {
        String path = "D:\\tlpdf\\tjlt1.out";
        String outPath = "D:\\tlpdf\\tjlt1_deal.out";
        String startText = "POST http://wg.zj.tj.jcy/iop-ocr/api/ocr/tyyw/catalogue HTTP/1.1";
        String startText2 = "POST http://wg.zj.tj.jcy/iop-ocr/api/ocr/tyyw/receive-ocr-pdf HTTP/1.1";
        String endText = "END HTTP";
        String starWith = "2021-12-16";
        Charset inputCharset = StandardCharsets.UTF_8;
        String containRow = "get request...submit";

        String mpath = "".equals(pathText.getText()) ? path : pathText.getText();
        String moutPath = "".equals(outPathText.getText()) ? outPath : outPathText.getText();

        //按钮
        exeM(mpath, moutPath, starWith, inputCharset, startText, startText2, endText, containText.getText() != null ? containText.getText() : containRow);
        outText.setText("完成");


    }

    @FXML
    protected void onHelloButtonClick() {
//        String text = containText.getText();
//        Print.p("t",text);
    }

    @FXML
    void addtimecustom(MouseEvent event) {
        //理论上来说，下面这种方式应该也是可以的，但是不知道为什么就是出不来效果
        //AnchorPane time1 = new AnchorPane(timecustom);
        //timebox.getChildren().add(time1);

        HBox timeall = new HBox();

        TextField time = new TextField();
        time.setPrefHeight(6.0);
        time.setPrefWidth(40.0);
        time.setLayoutY(2.0);
        timeall.getChildren().add(time);

        Label time1 = new Label("  : ");
        time1.setLayoutX(53.0);
        time1.setLayoutY(-2.0);
        time1.prefHeight(28.0);
        time1.setPrefWidth(19.0);
        timeall.getChildren().add(time1);

        TextField time2 = new TextField();
        time2.setPrefHeight(6.0);
        time2.setPrefWidth(40.0);
        time2.setLayoutY(2.0);
        timeall.getChildren().add(time2);

        timebox.setSpacing(10.0);
        timebox.getChildren().add(timeall);
    }

    //点击减少时间段
    @FXML
    void subtimecustom(MouseEvent event) {
        timebox.getChildren().remove(0);//这个index 0 是删除视觉上面排在第一个的。
    }
}