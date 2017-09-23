package View_Controller;

import Model.Part;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductScreenController {

    private MainScreenController msController;

    // list of all the parts
    @FXML
    private ObservableList<Part> parts;

    public AddProductScreenController() {
        parts = FXCollections.observableArrayList();
    }

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
    private Button prodDelButton;

    @FXML
    private Button prodSaveButton;

    @FXML
    private Button prodCancelButton;

    /**
     * Extracts the MainScreenController for access
     *
     * @param mscontroller
     */
    public void setMainScreenController(MainScreenController mscontroller) {
        this.msController = mscontroller;
    }
    
    @FXML
    public void sendParts(ObservableList<Part> mainParts) {
        parts = mainParts;
    }

    @FXML
    void prodAddButtonClick(ActionEvent event) {

    }

    @FXML
    void prodCancelButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) prodCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void prodDelButtonClick(ActionEvent event) {

    }

    @FXML
    void prodSaveButtonClick(ActionEvent event) {

    }

    @FXML
    void prodSearchButtonClick(ActionEvent event) {

    }

    @FXML
    public void initialize() {        
        System.out.println("Parts: " + parts);
        System.out.println("Main Screen Controller: " + msController);

        // assoicate part data with the columns
        prodTopIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        prodTopNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodTopInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load the part table with the parts
        prodTopTableView.setItems(parts);
        // set the first item selected
        prodTopTableView.getSelectionModel().selectFirst();
    }
}
