package calories2.Controller;

import calories2.Model.Food;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Jeroen De Meyer
 */
public class SearchResultTable extends Table{
    
    @FXML
    public TableView<Food> tableview;
    @FXML
    public TableColumn<Food,String> amountCol;
    
    @Override
    public void initialize(){
        super.initialize();
        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("amount"));
        DoubleClickTableRowFactory fac = new DoubleClickTableRowFactory<>();
        fac.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        TableRow tc = (TableRow) e.getSource();
                        Food f = (Food) tc.getItem();
                        getModel().select(f);
                    }
                });
        tableview.setRowFactory(fac);
        amountCol.setText(resources.getString("amountCol"));
    }
   
    @Override
    public void invalidated(Observable o){
        ObservableList<Food>results = FXCollections.observableArrayList(
            getModel().getSearchResults());
        if(!results.isEmpty()){
            tableview.setItems(results);
        }
    }
}
