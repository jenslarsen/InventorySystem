package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModifyProductScreenController {

    public ModifyProductScreenController() {
        partsForProduct = FXCollections.observableArrayList();
    }

    // the selected product in the list
    Product selectedProduct;

    // index of the selected product
    private int index;

    private MainScreenController msController;

    ObservableList<Part> partsForProduct;

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
    private Button prodDelButton;

    @FXML
    private Button prodSaveButton;

    @FXML
    private Button prodCancelButton;

    @FXML
    private TableView prodTopTableView;

    @FXML
    private TableView prodBotTableView;

    public void loadProduct(int index, Product product) {
        this.index = index;

        prodIDTextField.setText(Integer.toString(product.getProductID()));
        prodNameTextField.setText(product.getName());
        prodInvTextField.setText(Integer.toString(product.getInStock()));
        prodPriceTextField.setText(Double.toString(product.getPrice()));
        prodMaxTextField.setText(Integer.toString(product.getMax()));
        prodMinTextField.setText(Integer.toString(product.getMin()));

        partsForProduct = Inventory.getProducts().get(index).getAssociatedParts();
        System.out.println("partsForProduct " + partsForProduct);

        // load the bottom table with the added parts
        prodBotTableView.setItems(partsForProduct);
    }

    /**
     * Extracts the MainScreenController for access
     *
     * @param mscontroller
     */
    public void setMainScreenController(MainScreenController mscontroller) {
        this.msController = mscontroller;
    }

    @FXML
    void prodAddButtonClick(ActionEvent event) {
        int index = prodTopTableView.getSelectionModel().getSelectedIndex();
        partsForProduct.add(Inventory.getParts().get(index));
    }

    @FXML
    void prodCancelButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Modifying Product");
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
            if (index > partsForProduct.size() || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            // verify the user actually wants to delete the part
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
            conf.setTitle("Confirmation Dialog");
            conf.setHeaderText("Delete Part");
            conf.setContentText("Are you sure?");

            Optional<ButtonType> result = conf.showAndWait();

            if (result.get() == ButtonType.OK) {
                partsForProduct.remove(index);
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

        Product prodToModify;

        // get the input and check for invalid data
        try {
            int prodID = Integer.parseInt(prodIDTextField.getText());
            String prodName = prodNameTextField.getText();
            int prodInv = Integer.parseInt(prodInvTextField.getText());
            double prodPrice = Double.parseDouble(prodPriceTextField.getText());
            int prodMax = Integer.parseInt(prodMaxTextField.getText());
            int prodMin = Integer.parseInt(prodMinTextField.getText());

            // check for empty parts list
            if (partsForProduct.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable add product");
                alert.setContentText("Parts list can't be empty");

                alert.showAndWait();
            } else {
                // create a new product
                prodToModify = new Product(partsForProduct, prodID, prodName, prodPrice, prodInv, prodMin, prodMax);

                // modify the product in inventory and close the window
                Inventory.updateProduct(index, prodToModify);
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

    }

    public void initialize() {

        // assoicate part data with the top columns
        prodTopIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        prodTopNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodTopInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // assoicate part data with the bottom columns
        prodBotIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        prodBotNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodBotInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodBotPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load the part table with the parts
        prodTopTableView.setItems(Inventory.getParts());

        // set the first item selected
        prodTopTableView.getSelectionModel().selectFirst();
    }

}
