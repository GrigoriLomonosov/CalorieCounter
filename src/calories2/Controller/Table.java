package calories2.Controller;

import calories2.Model.Food;
import calories2.Model.Model;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Jeroen De Meyer
 */
public abstract class Table extends TableView implements InvalidationListener{
    
    public ResourceBundle resources;
    @FXML
    public TableColumn<Food,String> nameCol;
    @FXML
    public TableColumn<Food,Double> calCol;
    @FXML
    public TableColumn<Food,Double> protCol;
    @FXML
    public TableColumn<Food,Double> carbsCol;
    @FXML
    public TableColumn<Food,Double> fatCol;
           
    public Model model;
    public void setModel(Model m){
        if(m!=model){
            if(model!=null){
                model.removeListener(this);
            }
            model = m;
            if(model!=null){
                model.addListener(this);
            }
        }
    }      
    public Model getModel(){
        return model;
    } 
  
    public Table(){
        try{
        FXMLLoader loader = new FXMLLoader(
                Table.class.getResource("Table.fxml"),
                ResourceBundle.getBundle("resources/i18n"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void initialize(){
        nameCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        calCol.setCellValueFactory(
                new PropertyValueFactory<>("cal"));
        carbsCol.setCellValueFactory(
                new PropertyValueFactory<>("carbs"));
        carbsCol.setCellFactory(
                new MarkedCellFactory<>());
        protCol.setCellValueFactory(
                new PropertyValueFactory<>("prot"));
        protCol.setCellFactory(
                new MarkedCellFactory<>());
        fatCol.setCellValueFactory(
                    new PropertyValueFactory<>("fat"));
        fatCol.setCellFactory(
                new MarkedCellFactory<>());
    }
    
    @Override
    public abstract void invalidated(Observable o);
}
