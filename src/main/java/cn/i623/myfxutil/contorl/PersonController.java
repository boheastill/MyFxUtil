package cn.i623.myfxutil.contorl;


import cn.i623.myfxutil.RootApp;
import cn.i623.myfxutil.domain.Person;
import cn.i623.myfxutil.util.DateUtil;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private RootApp rootApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());

        lastNameColumn.setCellValueFactory(
                cellData -> {
                    Person value = cellData.getValue();
                    StringProperty stringProperty = value.lastNameProperty();
//                    System.out.println(stringProperty);
                    return stringProperty;
                });

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param rootApp
     */
    public void setMainApp(RootApp rootApp) {
        this.rootApp = rootApp;

        // Add observable list data to the table
//        personTable.setItems(rootApp.getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }


    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
            alert.setHeaderText("今日天气"); // 设置对话框的头部文本
            // 设置对话框的内容文本
            alert.setContentText("今天白天晴转多云，北转南风2、3间4级，最高气温28℃；夜间多云转阴，南风2级左右，最低气温16℃。");
            alert.show(); // 显示对话框
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
//        System.out.println("缺点1");

        Person newPerson = new Person();
//        boolean okClicked = rootApp.showPersonEditDialog(newPerson);
//        if (okClicked) {
//            System.out.println("缺点");
//            rootApp.getPersonData().add(newPerson);
//        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
//            boolean okClicked = rootApp.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
            showPersonDetails(selectedPerson);
//            }

        } else {
            // Nothing selected.
            System.out.println("应该弹窗");
        }
    }
}