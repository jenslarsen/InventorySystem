package View_Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPartScreenController {

    private MainScreenController mainController;

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
    private Label addPartMachLabel;

    @FXML
    boolean inHousePart;

    @FXML
    void partCancelButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) partCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void partInhouseRadioClick(ActionEvent event) {
        addPartMachLabel.setText("Machine ID");
        inHousePart = true;
    }

    @FXML
    void partOutsourcedRadioClick(ActionEvent event) {
        addPartMachLabel.setText("Company");
        inHousePart = false;
    }

    @FXML
    void partSaveButtonClick(ActionEvent event) throws Exception {

        try {
            int partID = Integer.parseInt(partIDTextField.getText());
            String name = partNameTextField.getText();
            int inv = Integer.parseInt(partInvTextField.getText());
            double price = Double.parseDouble(partPriceTextField.getText());
            int max = Integer.parseInt(partMaxTextField.getText());
            int min = Integer.parseInt(partMinTextField.getText());

            if (inHousePart) {
                int machineID = Integer.parseInt(partMachineTextField.getText());
            } else {
                String company = partMachineTextField.getText();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    public void initialize() {
        partInhouseRadio.setSelected(true);
    }
}
