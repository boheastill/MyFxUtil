package cn.i623.myfxutil;


import cn.i623.myfxutil.myinterface.IDCardInterface;
import cn.i623.myfxutil.tabElement.Director;
import cn.i623.myfxutil.tabElement.IdCardBuilder;
import cn.i623.myfxutil.tabElement.TabProduct;
import cn.i623.myfxutil.test.IDCard;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RootApp extends Application {


    public static void main(String[] args) {


//        System.out.println(aBoolean +","+ radomIdCard);
        launch(args);
    }

    //1.舞台
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = getScene();
        stage.setScene(scene);
        stage.setHeight(600);
        stage.setTitle("有.东西");
        stage.show();
    }

    //2.幕布
    private Scene getScene() throws IOException {
        return new Scene(getpane());
    }

    //3.画板
    private Parent getpane() throws IOException {
        BorderPane borderPane = initPane("root.fxml");
        //页签画板设置在根画板上
        borderPane.setCenter(getTabPane());
        return borderPane;
    }

    //4.页签画板
    private TabPane getTabPane() throws IOException {
        TabPane tabPane = initPane("tabs.fxml");
        ObservableList<Tab> tabs = tabPane.getTabs();
        //tabs修改现有页签
        tabsChange(tabs.get(0), getAnchorPane());
        //tabs增加页签
        tabs.addAll(getTabList());
        return tabPane;
    }


    //5.页签集合
    private List<Tab> getTabList() {
        List<Tab> tabList = new ArrayList<>();

        TabProduct tabProduct = new Director(
                new IdCardBuilder()//产品的方法移到bud里面
        ).buildProduct();//对builder进行组装

        tabProduct.put2TabList(tabList);


        IDCardInterface idCard = new IDCard();
        String radomIdCard = idCard.getRadomIdCard();
        Boolean aBoolean = idCard.cheakIdCard(radomIdCard);

//        addUnicodeTab(tabList);
//        addTab(tabList);
        return tabList;
    }


    private void addTab(List<Tab> tabList) {
        // VBox
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(100, 500, 50, 50));//高宽
        vBox.setSpacing(10);

        Label label = new Label("输入字符");
        TextField textField = new TextField();
        TextArea textArea = new TextArea();
//        label.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));

        // Buttons
        Button burron = new Button();
        burron.setText("查询");

        vBox.getChildren().add(label);
        vBox.getChildren().add(burron);
        vBox.getChildren().add(textField);
        vBox.getChildren().add(textArea);

        Tab tab = new Tab();
        tab.setContent(vBox);
        tab.setText("unicode查询");
        tabList.add(tab);
    }


    private <T> T initPane(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RootApp.class.getResource(name));
        return loader.load();
    }

    private AnchorPane getAnchorPane() throws IOException {
        String tjLogTabName = "tjLogTab.fxml";
        return initPane(tjLogTabName);
    }

    private void tabsChange(Tab tab, Node tjLogTab) {
        tab.setContent(tjLogTab);
    }

    /*
     *//**
     * The data as an observable list of Persons.
     *//*
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    *//**
     * Constructor
     *//*
    public RootApp() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    *//**
     * Returns the data as an observable list of Persons.
     *
     * @return
     *//*
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    *//**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     *//*
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("编辑信息");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public void showPerson(String fxmlName) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootApp.class.getResource(fxmlName));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            borderPane.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    *//**
     * Returns the main stage.
     *
     * @return
     *//*
    public Stage getStage() {
        return stage;
    }*/
}