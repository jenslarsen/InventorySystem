package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jens Larsen
 */
public class Main extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
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
}
