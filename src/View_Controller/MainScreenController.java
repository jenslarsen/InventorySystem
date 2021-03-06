package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScreenController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = primaryStage;

        FXMLLoader mainScreenLoader = new FXMLLoader();
        mainScreenLoader.setLocation(getClass().getResource("/View_Controller/MainScreen.fxml"));

        Parent root = mainScreenLoader.load();

        stage.setScene(new Scene(root));

        stage.setTitle("Inventory System");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    // list of found parts to display in search results
    @FXML
    private ObservableList<Part> searchParts = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Product> searchProd = FXCollections.observableArrayList();

    @FXML
    private TextField partSearchTextField;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partPartIDCol;

    @FXML
    private TableColumn<Part, String> partPartNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLevCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableColumn<Product, Integer> prodIDCol;

    @FXML
    private TableColumn<Product, String> prodPartNameCol;

    @FXML
    private TableColumn<Product, Integer> prodInvLevCol;

    @FXML
    private TableColumn<Product, Double> prodPriceCol;

    @FXML
    private TextField prodSearchTextField;

    @FXML
    private TableView prodTableView;

    @FXML
    void exitButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Exit application");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) partTableView.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void partAddButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader addPartScreenLoader = new FXMLLoader();
        addPartScreenLoader.setLocation(getClass().getResource("AddPartScreen.fxml"));

        Parent root = addPartScreenLoader.load();

        stage.setScene(new Scene(root));

        stage.setTitle("Add Part");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
        searchParts.clear();
        searchParts.addAll(Inventory.getParts());
    }

    @FXML
    void partDeleteButtonClick(ActionEvent event) {

        int index = partTableView.getSelectionModel().getSelectedIndex();
        try {
            // check the index
            if (index > Inventory.getParts().size() || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            // verify the user actually wants to delete the part
            Alert conf = new Alert(AlertType.CONFIRMATION);
            conf.setTitle("Confirmation Dialog");
            conf.setHeaderText("Delete Part");
            conf.setContentText("Are you sure?");

            Optional<ButtonType> result = conf.showAndWait();

            if (result.get() == ButtonType.OK) {
                Inventory.deletePart(searchParts.get(index));
                searchParts.remove(index);
                partTableView.refresh();
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
    void partModifyButtonClick(ActionEvent event) throws IOException {
        // get the selected iteem index and make sure its valid
        int index = partTableView.getSelectionModel().getSelectedIndex();

        Stage stage = new Stage();

        FXMLLoader modifyPartScreenLoader = new FXMLLoader();
        modifyPartScreenLoader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));

        Parent root = modifyPartScreenLoader.load();

        stage.setScene(new Scene(root));

        // get the modify part screen controller
        ModifyPartScreenController modifyPartScreenController
                = modifyPartScreenLoader.getController();

        try {
            modifyPartScreenController.loadPart(index,
                    partTableView.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to modify part");
            alert.setContentText("No part selected");

            alert.showAndWait();
            return;
        }

        stage.setTitle("Modify Part");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
        searchParts.clear();
        searchParts.addAll(Inventory.getParts());
    }

    @FXML
    void partSearchButtonClick(ActionEvent event) {

        ObservableList<Part> parts = Inventory.getParts();

        searchParts.clear();

        for (Part part : parts) {
            if (part.getName().contains((partSearchTextField.getText()))) {
                searchParts.add(part);
            }
        }
    }

    @FXML
    void prodAddButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader addProductScreenLoader = new FXMLLoader();
        addProductScreenLoader.setLocation(getClass().getResource("AddProductScreen.fxml"));

        Parent root = addProductScreenLoader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        searchProd.clear();
        searchProd.addAll(Inventory.getProducts());
    }

    @FXML
    void prodDeleteButtonClick(ActionEvent event) {

        int index = prodTableView.getSelectionModel().getSelectedIndex();
        try {
            // check the index
            if (index > Inventory.getProducts().size() || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            // product with at least one part cannot be deleted!
            if (!searchProd.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to delete part");
                alert.setContentText("Products with at least one part cannot be deleted");
                alert.showAndWait();
                return;
            }

            // verify the user actually wants to delete the part
            Alert conf = new Alert(AlertType.CONFIRMATION);
            conf.setTitle("Confirmation Dialog");
            conf.setHeaderText("Delete Product");
            conf.setContentText("Are you sure?");

            Optional<ButtonType> result = conf.showAndWait();

            if (result.get() == ButtonType.OK) {
                Inventory.removeProduct(searchProd.get(index));
                searchProd.remove(index);
                prodTableView.refresh();
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
    void prodModifyButtonClick(ActionEvent event) throws IOException {
        // get the selected iteem index and make sure its valid
        int index = prodTableView.getSelectionModel().getSelectedIndex();
        Product selectedProduct = (Product) prodTableView.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader modifyProductScreenLoader = new FXMLLoader();
        modifyProductScreenLoader.setLocation(getClass().getResource("ModifyProductScreen.fxml"));

        Parent root = modifyProductScreenLoader.load();

        stage.setScene(new Scene(root));

        // get the modify product screen controller
        ModifyProductScreenController modifyProductScreenController = modifyProductScreenLoader.getController();

        // load the selected part
        try {
            modifyProductScreenController.loadProduct(index, selectedProduct);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to modify part");
            alert.setContentText("No part selected");

            alert.showAndWait();
            return;
        }

        stage.setTitle("Modify Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        searchProd.clear();
        searchProd.addAll(Inventory.getProducts());
    }

    @FXML
    void prodSearchButtonClick(ActionEvent event) {
        ObservableList<Product> products = Inventory.getProducts();

        searchProd.clear();

        for (Product product : products) {
            if (product.getName().contains((prodSearchTextField.getText()))) {
                searchProd.add(product);
            }
        }
    }

    @FXML
    public void initialize() {
        // load some initial part data
        Inventory.addPart(new InhousePart(101, "Widget", 9.99, 6, 0, 10, 100));
        Inventory.addPart(new OutsourcedPart(102, "Fidget", 8.99, 23, 0, 10, "Fidget's R Us"));
        Inventory.addPart(new InhousePart(103, "Gidget", 7.99, 456, 0, 10, 100));
        Inventory.addPart(new InhousePart(104, "Lidget", 6.99, 44, 0, 10, 100));
        Inventory.addPart(new OutsourcedPart(105, "Kidget", 5.99, 11, 0, 10, "Do you want stuff?"));
        Inventory.addPart(new InhousePart(106, "Quidget", 4.99, 435, 0, 10, 100));

        // load some initial product data
        ObservableList<Part> startParts = FXCollections.observableArrayList();
        startParts.add(Inventory.lookupPart(0));
        Inventory.addProduct(new Product(startParts, 100, "Device", 100.0, 9, 0, 10));

        // assoicate part data with the columns
        partPartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // assoicate product data with the columns
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        prodPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvLevCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // add all of the Inventory.parts to the searchParts
        searchParts.addAll(Inventory.getParts());

        // add all of the Inventory.products to the searchProd
        searchProd.addAll(Inventory.getProducts());

        // load the part table with the searchParts
        partTableView.setItems(searchParts);

        // load the product table with products
        prodTableView.setItems(searchProd);

        // set the first item selected
        partTableView.getSelectionModel().selectFirst();

        // set the first item selected
        prodTableView.getSelectionModel().selectFirst();
    }
}
