package View_Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyProductScreenController {

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
    void prodAddButtonClick(ActionEvent event) {

    }

    @FXML
    void prodCancelButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) prodAddButton.getScene().getWindow();
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

}
