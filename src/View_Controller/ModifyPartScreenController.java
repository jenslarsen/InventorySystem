package View_Controller;

import Model.InhousePart;
import Model.OutsourcedPart;
import Model.Part;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModifyPartScreenController {

    Stage stage = new Stage();

    private MainScreenController mscontroller;

    /**
     * Extracts the MainScreenController for access
     * @param mscontroller 
     */
    public void setMainScreenController(MainScreenController mscontroller) {
        this.mscontroller = mscontroller;
    }

    // The selected part in the table view
    Part selectedPart;
    // Index of the selected part
    int index;

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
        stage = (Stage) partCancelButton.getScene().getWindow();

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
    void partModifyButtonClick(ActionEvent event) {
        Part partToReturn;

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
                partToReturn = newInhousePart;
            } else {
                int partID = Integer.parseInt(partIDTextField.getText());
                String name = partNameTextField.getText();
                double price = Double.parseDouble(partPriceTextField.getText());
                int inStock = Integer.parseInt(partInvTextField.getText());
                int min = Integer.parseInt(partMinTextField.getText());
                int max = Integer.parseInt(partMaxTextField.getText());
                String company = partMachineTextField.getText();

                OutsourcedPart newOutsourcedPart = new OutsourcedPart(partID, name, price, inStock, min, max, company);
                partToReturn = newOutsourcedPart;
            }
            
            mscontroller.ModifyPart(index, partToReturn);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    public void initialize() {
        partInhouseRadio.setSelected(true);
    }
}
