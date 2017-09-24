package View_Controller;

import Model.Inventory;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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

    @FXML
    void prodAddButtonClick(ActionEvent event) {

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

    }

    @FXML
    void prodSaveButtonClick(ActionEvent event) {

    }

    @FXML
    void prodSearchButtonClick(ActionEvent event) {

    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // assoicate part data with the columns
        prodTopIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        prodTopNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodTopInvCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodTopPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load the part table with the parts
        prodTopTableView.setItems(Inventory.parts);
        // set the first item selected
        prodTopTableView.getSelectionModel().selectFirst();
    }
}
