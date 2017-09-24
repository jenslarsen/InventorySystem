package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductScreenController implements Initializable {

    public AddProductScreenController() {
        partsForNewProduct = FXCollections.observableArrayList();
    }

    ObservableList<Part> partsForNewProduct;

    @FXML
    private Label addModifyProdLabel;

    @FXML
    private TextField prodIDTextField;

    @FXML
    private TextField prodNameTextField;

    @FXML
    private TextField prodInvTextField;

    @FXML
    private TextField prodPriceTextField;

    @FXML
    private TextField prodMaxTextField;

    @FXML
    private TextField prodMinTextField;

    @FXML
    private TableColumn<?, ?> prodTopIDCol;

    @FXML
    private TableColumn<?, ?> prodTopNameCol;

    @FXML
    private TableColumn<?, ?> prodTopInvCol;

    @FXML
    private TableColumn<?, ?> prodTopPriceCol;

    @FXML
    private TableView prodTopTableView;

    @FXML
    private Button prodSearchButton;

    @FXML
    private TextField prodSearchTextView;

    @FXML
    private Button prodAddButton;

    @FXML
    private TableColumn<?, ?> prodBotIDCol;

    @FXML
    private TableColumn<?, ?> prodBotNameCol;

    @FXML
    private TableColumn<?, ?> prodBotInvCol;

    @FXML
    private TableColumn<?, ?> prodBotPriceCol;

    @FXML
    private TableView prodBotTableView;

    @FXML
    private Button prodDelButton;

    @FXML
    private Button prodSaveButton;

    @FXML
    private Button prodCancelButton;

    @FXML
    void prodAddButtonClick(ActionEvent event) {

        int index = prodTopTableView.getSelectionModel().getSelectedIndex();
        partsForNewProduct.add(Inventory.parts.get(index));
    }

    @FXML
    void prodCancelButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Adding Product");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) prodCancelButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void prodDelButtonClick(ActionEvent event) {

        int index = prodBotTableView.getSelectionModel().getSelectedIndex();

        try {
            // check the index
            if (index > partsForNewProduct.size() || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            // verify the user actually wants to delete the part
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
            conf.setTitle("Confirmation Dialog");
            conf.setHeaderText("Delete Part");
            conf.setContentText("Are you sure?");

            Optional<ButtonType> result = conf.showAndWait();

            if (result.get() == ButtonType.OK) {
                partsForNewProduct.remove(index);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to delete part");
            alert.setContentText("No part selected");

            alert.showAndWait();
        }
    }

    @FXML
    void prodSaveButtonClick(ActionEvent event) {

        Product prodToAdd;

        // check for empty parts list
        if (partsForNewProduct.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable add product");
            alert.setContentText("Parts list can't be empty");

            alert.showAndWait();
        }

        // get the input and check for invalid data
        try {
            int prodID = Integer.parseInt(prodIDTextField.getText());
            String prodName = prodNameTextField.getText();
            int prodInv = Integer.parseInt(prodInvTextField.getText());
            double prodPrice = Double.parseDouble(prodPriceTextField.getText());
            int prodMax = Integer.parseInt(prodMaxTextField.getText());
            int prodMin = Integer.parseInt(prodMinTextField.getText());

            prodToAdd = new Product(partsForNewProduct, prodID, prodName, prodPrice, prodInv, prodMin, prodMax);

            Inventory.addProduct(prodToAdd);
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable add part");
            alert.setContentText("Number format is invalid");

            alert.showAndWait();
        }
    }

    @FXML
    void prodSearchButtonClick(ActionEvent event) {

    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // assoicate part data with the columns in both tables
        prodTopIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        prodTopNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodTopInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        prodBotIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        prodBotNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodBotInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodBotPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load the part table with the parts
        prodTopTableView.setItems(Inventory.parts);

        // load the bottom table with the added parts
        prodBotTableView.setItems(partsForNewProduct);

        // set the first item selected
        prodTopTableView.getSelectionModel().selectFirst();
    }
}
