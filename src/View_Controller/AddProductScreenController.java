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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductScreenController implements Initializable {

    public AddProductScreenController() {
        partsForNewProduct = FXCollections.observableArrayList();
        searchParts = FXCollections.observableArrayList();
        searchParts.addAll(Inventory.getParts());
    }

    ObservableList<Part> partsForNewProduct;

    ObservableList<Part> searchParts;

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
    private TextField prodSearchTextView;

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
    private Button prodSaveButton;

    @FXML
    private Button prodCancelButton;

    @FXML
    void prodAddButtonClick(ActionEvent event) {

        int index = prodTopTableView.getSelectionModel().getSelectedIndex();
        partsForNewProduct.add(Inventory.getParts().get(index));
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

        // get the input
        try {
            int prodID = Integer.parseInt(prodIDTextField.getText());
            String prodName = prodNameTextField.getText();
            int prodInv = Integer.parseInt(prodInvTextField.getText());
            double prodPrice = Double.parseDouble(prodPriceTextField.getText());
            int prodMax = Integer.parseInt(prodMaxTextField.getText());
            int prodMin = Integer.parseInt(prodMinTextField.getText());

            // check to ensure that the price of product isn't less than the cost of the parts
            double partsTotalPrice = 0;
            for (Part part : partsForNewProduct) {
                partsTotalPrice += part.getPrice();
            }
            if (prodPrice < partsTotalPrice) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable add product");
                alert.setContentText("Price can't be less than the price of the parts");

                alert.showAndWait();
            } else if (partsForNewProduct.isEmpty()) {
                // check for empty parts list
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable add product");
                alert.setContentText("Parts list can't be empty");

                alert.showAndWait();
            } else if (prodInv < prodMin || prodInv > prodMax) {
                // check inventory level
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable add product");
                alert.setContentText("Inventory can't be less than min or more than max");

                alert.showAndWait();
                // check that min is not more than the maximum 
            } else if (prodMin > prodMax) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable add part");
                alert.setContentText("Maximum must be smaller than minimum");

                alert.showAndWait();
            } else {
                // create a new product
                prodToAdd = new Product(partsForNewProduct, prodID, prodName, prodPrice, prodInv, prodMin, prodMax);

                // add the product to inventory and close the window
                Inventory.addProduct(prodToAdd);
                Stage stage = (Stage) prodSaveButton.getScene().getWindow();
                stage.close();
            }

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
        ObservableList<Part> parts = Inventory.getParts();

        searchParts.clear();

        for (Part part : parts) {
            if (part.getName().toLowerCase().contains((prodSearchTextView.getText()))) {
                searchParts.add(part);
            }
        }
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

        // load the top part table with the parts
        prodTopTableView.setItems(searchParts);

        // load the bottom table with the added parts
        prodBotTableView.setItems(partsForNewProduct);

        // set the first item selected
        prodTopTableView.getSelectionModel().selectFirst();
    }
}
