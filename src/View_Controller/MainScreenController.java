package View_Controller;

import Model.Part;
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
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public class MainScreenController {

    @FXML
    private ObservableList<Part> parts = FXCollections.observableArrayList();

    @FXML
    private Button partSearchButton;

    @FXML
    private TextField partSearchTextField;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<?, ?> partPartIDCol;

    @FXML
    private TableColumn<?, ?> partPartNameCol;

    @FXML
    private TableColumn<?, ?> partInvLevCol;

    @FXML
    private TableColumn<?, ?> partPriceCol;

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

    public MainScreenController() {
    }

    @FXML
    void partAddButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Add Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    void partDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void partModifyButtonClick(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        stage = (Stage) partModifyButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ModifyPartScreen.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partSearchButtonClick(ActionEvent event) {

    }

    @FXML
    void prodAddButtonClick(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        stage = (Stage) partAddButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void prodDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void prodModifyButtonClick(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        stage = (Stage) partAddButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void prodSearchButtonClick(ActionEvent event) {

    }

    public void initialize() {

        partTableView = new TableView<>(parts);

        // associate data with the table columns
        partPartIDCol.setCellValueFactory(
                new PropertyValueFactory<>("partID"));
        partPartNameCol.setCellValueFactory(
                new PropertyValueFactory<>("partName"));
        partInvLevCol.setCellValueFactory(
                new PropertyValueFactory<>("inStock"));
        partPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        // load the data
        partTableView.setItems(parts);
    }
}
