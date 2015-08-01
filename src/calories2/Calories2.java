
package calories2;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author Jeroen De Meyer
 */
public class Calories2 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                            Calories2.class.getResource("/calories2/View/StartView.fxml"),
                            ResourceBundle.getBundle("resources/i18n"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("CalorieTeller 2.0");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/resources/icon.jpg"));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
