package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPartScreenController {

    private Part partToAdd;

    @FXML
    private RadioButton partInhouseRadio;

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
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Adding Part");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) partCancelButton.getScene().getWindow();
            stage.close();
        }
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

                // check inventory level
                if (inStock < min || inStock > max) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Inventory can't be less than min or more than max");

                    alert.showAndWait();
                    return;
                    // check that min is not more than the maximum 
                } else if (min > max) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Maximum must be smaller than minimum");

                    alert.showAndWait();
                    return;
                }

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

                // check inventory level
                if (inStock < min || inStock > max) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Inventory can't be less than min or more than max");

                    alert.showAndWait();
                    return;
                // check that min is not more than the maximum 
                } else if (min > max) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Maximum must be smaller than minimum");

                    alert.showAndWait();
                    return;
                }

                OutsourcedPart newOutsourcedPart = new OutsourcedPart(partID, name, price, inStock, min, max, company);
                partToAdd = newOutsourcedPart;
            }

            Inventory.addPart(partToAdd);
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
