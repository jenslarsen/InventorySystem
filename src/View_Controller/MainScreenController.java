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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML
    private ObservableList<Part> parts;

    @FXML
    private ObservableList<Product> products;

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
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setResizable(false);

        stage.showAndWait();
    }

    @FXML
    void partDeleteButtonClick(ActionEvent event) {
        int index = -1;
        index = partTableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            parts.remove(index);
        } else {
            System.out.println("No parts left to delete!");
        }
    }

    @FXML
    void partModifyButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader modifyPartScreenLoader = new FXMLLoader();
        modifyPartScreenLoader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));

        Parent root = modifyPartScreenLoader.load();

        stage.setScene(new Scene(root));

        ModifyPartScreenController modifyPartScreenController = modifyPartScreenLoader.getController();
        int index = partTableView.getSelectionModel().getSelectedIndex();
        modifyPartScreenController.loadPart(index, partTableView.getSelectionModel().getSelectedItem());
        modifyPartScreenController.setMainScreenController(this);

        stage.setTitle("Modify Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setResizable(false);

        stage.showAndWait();
    }

    @FXML
    void partSearchButtonClick(ActionEvent event) {

    }

    @FXML
    void prodAddButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
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
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
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

        // load some initial data
        parts.add(new InhousePart(101, "Widget", 9.99, 6, 0, 10, 100));
        parts.add(new OutsourcedPart(102, "Fidget", 8.99, 23, 0, 10, "Fidget's R Us"));
        parts.add(new InhousePart(103, "Gidget", 7.99, 456, 0, 10, 100));
        parts.add(new InhousePart(104, "Lidget", 6.99, 44, 0, 10, 100));
        parts.add(new OutsourcedPart(105, "Kidget", 5.99, 11, 0, 10, "Do you want stuff?"));
        parts.add(new InhousePart(106, "Quidget", 4.99, 435, 0, 10, 100));

        // assoicate data with the columns
        partPartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load the table with the parts
        partTableView.setItems(parts);
        // set the first item selected
        // prevents NPE clicking Modify with no part selected
        partTableView.getSelectionModel().selectFirst();
    }
}
