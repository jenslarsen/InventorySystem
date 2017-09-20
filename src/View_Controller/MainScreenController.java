package View_Controller;

import Model.InhousePart;
import Model.OutsourcedPart;
import Model.Part;
import Model.Product;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScreenController {

    // list of all the parts
    @FXML
    private ObservableList<Part> parts;

    // list of all the products
    @FXML
    private ObservableList<Product> products;

    // list of found parts to display in search results
    @FXML
    private ObservableList<Part> searchParts;
    
    // Has a part been partFound in the list?
    private boolean partFound;

    @FXML
    private Button partSearchButton;

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
    private Button partAddButton;

    @FXML
    private Button partModifyButton;

    @FXML
    private Button partDeleteButton;

    @FXML
    private Button prodAddButton;

    @FXML
    private Button prodModifyButton;

    @FXML
    private Button prodDeleteButton;

    @FXML
    private TableColumn<?, ?> prodIDCol;

    @FXML
    private TableColumn<?, ?> prodPartNameCol;

    @FXML
    private TableColumn<?, ?> prodInvLevCol;

    @FXML
    private TableColumn<?, ?> prodPriceCol;

    @FXML
    private Button prodSearchButton;

    @FXML
    private TextField prodSearchTextField;

    public void ModifyPart(int index, Part partToModify) {
        parts.set(index, partToModify);
    }

    public void AddPart(Part partToAdd) {
        parts.add(partToAdd);
    }

    @FXML
    void partAddButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader addPartScreenLoader = new FXMLLoader();
        addPartScreenLoader.setLocation(getClass().getResource("AddPartScreen.fxml"));

        Parent root = addPartScreenLoader.load();

        stage.setScene(new Scene(root));

        AddPartScreenController addPartScreenController = addPartScreenLoader.getController();
        int index = partTableView.getSelectionModel().getSelectedIndex();
        addPartScreenController.setMainScreenController(this);

        stage.setTitle("Add Part");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
    }

    @FXML
    void partDeleteButtonClick(ActionEvent event) {
        int index = partTableView.getSelectionModel().getSelectedIndex();
        try {
            if (partFound) {
                searchParts.remove(index);
            } else {
                parts.remove(index);
            }
            parts.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to delete part");
            alert.setContentText("Invalid index");

            alert.showAndWait();
        }
    }

    @FXML
    void partModifyButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader modifyPartScreenLoader = new FXMLLoader();
        modifyPartScreenLoader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));

        Parent root = modifyPartScreenLoader.load();

        stage.setScene(new Scene(root));

        // get the selected iteem index and make sure its valid
        int index = partTableView.getSelectionModel().getSelectedIndex();

        ModifyPartScreenController modifyPartScreenController = modifyPartScreenLoader.getController();
        try {
            modifyPartScreenController.loadPart(index, partTableView.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to modify part");
            alert.setContentText("Invalid index");

            alert.showAndWait();
            return;
        }
        modifyPartScreenController.setMainScreenController(this);

        stage.setTitle("Modify Part");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
    }

    @FXML
    void partSearchButtonClick(ActionEvent event) {

        searchParts = FXCollections.observableArrayList();

        String itemToSearchFor = partSearchTextField.getText();
        partFound = false;

        // look for a NumberFormatException
        // if no exception itemToSearchFor is probably a number and we'll look for a partID
        // otherwise we treat it like a string and search the name
        try {
            int searchNumber = Integer.parseInt(itemToSearchFor);

            // loop through the parts to see if there are any matches in the part number
            // if so, add them to searchParts
            for (Part part : parts) {
                if (part.getPartID() == searchNumber) {
                    partFound = true;
                    searchParts.add(part);
                }
            }

        } catch (NumberFormatException e) {
            // The user is probably trying to search for a name
            for (Part part : parts) {
                if (part.getName().contains(itemToSearchFor)) {
                    partFound = true;
                    searchParts.add(part);
                }
            }
            partTableView.setItems(searchParts);
        }

    }

    @FXML
    void prodAddButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    void prodDeleteButtonClick(ActionEvent event) {
    }

    @FXML
    void prodModifyButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Modify Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    void prodSearchButtonClick(ActionEvent event) {
    }

    @FXML
    public void initialize() {

        parts = FXCollections.observableArrayList();
        products = FXCollections.observableArrayList();
        searchParts = FXCollections.observableArrayList();

        // load some initial part data
        parts.add(new InhousePart(101, "Widget", 9.99, 6, 0, 10, 100));
        parts.add(new OutsourcedPart(102, "Fidget", 8.99, 23, 0, 10, "Fidget's R Us"));
        parts.add(new InhousePart(103, "Gidget", 7.99, 456, 0, 10, 100));
        parts.add(new InhousePart(104, "Lidget", 6.99, 44, 0, 10, 100));
        parts.add(new OutsourcedPart(105, "Kidget", 5.99, 11, 0, 10, "Do you want stuff?"));
        parts.add(new InhousePart(106, "Quidget", 4.99, 435, 0, 10, 100));

        // assoicate part data with the columns
        partPartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load the part table with the parts
        partTableView.setItems(parts);
        // set the first item selected
        partTableView.getSelectionModel().selectFirst();
    }
}
