package calories2.View;

import calories2.Model.Model;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jeroen De Meyer
 */
public abstract class TotalBar extends HBox implements InvalidationListener{
    
    public ResourceBundle resources;
    @FXML
    public Label typeOfTotal;
    @FXML
    public Label typeOfNeeded;
    @FXML
    public Label total;
    @FXML
    public Label needed;
    @FXML
    public ProgressBar progress;
    
    public Model model;
    public Model getModel(){
        return model;
    }
    public void setModel(Model m){
        if(model != m){
            if(model != null){
                model.removeListener(this);
            }
            model = m;
            if(model!=null){
                model.addListener(this);
            }
        }
    }
    
    public TotalBar(){
        try{
            FXMLLoader loader = new FXMLLoader(
                    TotalBar.class.getResource("TotalBar.fxml"),
                    ResourceBundle.getBundle("resources/i18n"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
            }catch(IOException e){
                throw new RuntimeException(e);
        }
    }
    
    @Override
    public abstract void invalidated(Observable o);
}
