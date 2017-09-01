package View_Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModifyPartScreenController {

    @FXML
    private RadioButton partOutsourcedRadio;

    @FXML
    private ToggleGroup inOutGroup;

    @FXML
    private RadioButton partInhouseRadio;

    @FXML
    private Label addModifyPartLabel;

    @FXML
    private TextField partIDTextField;

    @FXML
    private TextField partNameTextField;

    @FXML
    private TextField partInvTextField;

    @FXML
    private TextField partPriceTextField;

    @FXML
    private TextField partMaxTextField;

    @FXML
    private TextField partMachineTextField;

    @FXML
    private TextField partMinTextField;

    @FXML
    private Button partSaveButton;

    @FXML
    private Button partCancelButton;

    @FXML
    void partCancelButtonClick(ActionEvent event) throws IOException {

        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));

        Scene scene = new Scene(root);
        stage = (Stage)partCancelButton.getScene().getWindow();


        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partInhouseRadioClick(ActionEvent event) {

    }

    @FXML
    void partOutsourcedRadioClick(ActionEvent event) {

    }

    @FXML
    void partModifyButtonClick(ActionEvent event) {

    }

}