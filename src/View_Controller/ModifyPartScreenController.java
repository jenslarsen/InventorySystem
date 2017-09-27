package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Jens Larsen
 */
public class ModifyPartScreenController implements Initializable {

    // The selected part in the table view
    Part selectedPart;
    // Index of the selected part
    int index;

    @FXML
    private RadioButton partOutsourcedRadio;

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

    /**
     * Loads the selected part into the fields
     *
     * @param index of selected part
     * @param part to modify
     */
    public void loadPart(int index, Part part) {
        // save the index so the part can be modified
        this.index = index;

        if (part instanceof InhousePart) {
            InhousePart ipart = (InhousePart) part;
            partInhouseRadio.setSelected(true);
            addPartMachLabel.setText("Machine ID");
            partIDTextField.setText(Integer.toString(ipart.getPartID()));
            partNameTextField.setText(ipart.getName());
            partInvTextField.setText(Integer.toString(ipart.getInStock()));
            partPriceTextField.setText(Double.toString(ipart.getPrice()));
            partMaxTextField.setText(Integer.toString(ipart.getMax()));
            partMinTextField.setText(Integer.toString(ipart.getMin()));
            partMachineTextField.setText(Integer.toString(ipart.getMachineID()));
        } else {
            OutsourcedPart opart = (OutsourcedPart) part;
            partOutsourcedRadio.setSelected(true);
            addPartMachLabel.setText("Company");
            partIDTextField.setText(Integer.toString(opart.getPartID()));
            partNameTextField.setText(opart.getName());
            partInvTextField.setText(Integer.toString(opart.getInStock()));
            partPriceTextField.setText(Double.toString(opart.getPrice()));
            partMaxTextField.setText(Integer.toString(opart.getMax()));
            partMinTextField.setText(Integer.toString(opart.getMin()));
            partMachineTextField.setText(opart.getCompanyName());
        }
    }

    @FXML
    void partCancelButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Modifying Part");
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
    void partModifyButtonClick(ActionEvent event) {
        Part partToModify;

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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Inventory can't be less than min or more than max");

                    alert.showAndWait();
                    return;
                    // check that min is not more than the maximum 
                } else if (min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Maximum must be smaller than minimum");

                    alert.showAndWait();
                    return;
                }

                InhousePart newInhousePart = new InhousePart(partID, name, price, inStock, min, max, machineID);
                partToModify = newInhousePart;
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Inventory can't be less than min or more than max");

                    alert.showAndWait();
                    return;
                // check that min is not more than the maximum 
                } else if (min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable add part");
                    alert.setContentText("Maximum must be smaller than minimum");

                    alert.showAndWait();
                    return;
                }

                OutsourcedPart newOutsourcedPart = new OutsourcedPart(partID, name, price, inStock, min, max, company);
                partToModify = newOutsourcedPart;
            }

            Inventory.updatePart(index, partToModify);
            Stage stage = (Stage) partSaveButton.getScene().getWindow();

            stage.close();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to modify part");
            alert.setContentText("Number format invalid");

            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partInhouseRadio.setSelected(true);
    }
}
