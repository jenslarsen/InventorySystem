package View_Controller;

import Model.InhousePart;
import Model.OutsourcedPart;
import Model.Part;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPartScreenController {

    private MainScreenController MSController;

    private Part partToAdd;

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

    /**
     * Extracts the MainScreenController for access
     *
     * @param mscontroller
     */
    public void setMainScreenController(MainScreenController mscontroller) {
        this.MSController = mscontroller;
    }

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
            if (inHousePart) {
                int partID = Integer.parseInt(partIDTextField.getText());
                String name = partNameTextField.getText();
                double price = Double.parseDouble(partPriceTextField.getText());
                int inStock = Integer.parseInt(partInvTextField.getText());
                int min = Integer.parseInt(partMinTextField.getText());
                int max = Integer.parseInt(partMaxTextField.getText());
                int machineID = Integer.parseInt(partMachineTextField.getText());

                InhousePart newInhousePart = new InhousePart(partID, name, price, inStock, min, max, machineID);
                partToAdd = newInhousePart;
            } else {
                int partID = Integer.parseInt(partIDTextField.getText());
                String name = partNameTextField.getText();
                double price = Double.parseDouble(partPriceTextField.getText());
                int inStock = Integer.parseInt(partInvTextField.getText());
                int min = Integer.parseInt(partMinTextField.getText());
                int max = Integer.parseInt(partMaxTextField.getText());
                String company = partMachineTextField.getText();

                OutsourcedPart newOutsourcedPart = new OutsourcedPart(partID, name, price, inStock, min, max, company);
                partToAdd = newOutsourcedPart;
            }

            MSController.AddPart(partToAdd);
            Stage stage = (Stage) partSaveButton.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable add part");
            alert.setContentText("Number format is invalid");

            alert.showAndWait();
        }
    }

    public void initialize() {
        // set the default to InhousePart
        inHousePart = true;
        partInhouseRadio.setSelected(true);
    }
}
