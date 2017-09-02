package View_Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainScreenController {

    @FXML
    private Button partSearchButton;

    @FXML
    private TextField partSearchTextField;

    @FXML
    private TableColumn<?, ?> partPartIDCol;

    @FXML
    private TableColumn<?, ?> partPartNameCol;

    @FXML
    private TableColumn<?, ?> partInvLevCol;

    @FXML
    private TableColumn<?, ?> partPriceCol;

    @FXML
    private Button partAddButton;

    @FXML
    private Button partModifyButton;

    @FXML
    private Button partDeleteButton;

    @FXML
    private Button prodAddButton;

    @FXML
    private Button prodModifyButton;

    @FXML
    private Button prodDeleteButton;

    @FXML
    private TableColumn<?, ?> prodPartIDCol;

    @FXML
    private TableColumn<?, ?> prodPartNameCol;

    @FXML
    private TableColumn<?, ?> prodInvLevCol;

    @FXML
    private TableColumn<?, ?> prodPriceCol;

    @FXML
    private Button prodSearchButton;

    @FXML
    private TextField prodSearchTextField;

    @FXML
    void partAddButtonClick(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        stage = (Stage)partAddButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void partModifyButtonClick(ActionEvent event) throws IOException {
       
        Stage stage;
        Parent root;

        stage = (Stage)partModifyButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ModifyPartScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partSearchButtonClick(ActionEvent event) {

    }

    @FXML
    void prodAddButtonClick(ActionEvent event) throws IOException {
        
        Stage stage;
        Parent root;

        stage = (Stage)partAddButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void prodDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void prodModifyButtonClick(ActionEvent event) throws IOException {
        
        Stage stage;
        Parent root;

        stage = (Stage)partAddButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void prodSearchButtonClick(ActionEvent event) {

    }

}
