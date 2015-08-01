package calories2.Controller;

import calories2.Model.Food;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author Jeroen De Meyer
 */
public class SelectionTable extends Table{
    
    @FXML
    public TableView<Food> tableview;
    @FXML
    public TableColumn<Food,Integer>amountCol;

    @Override
    public void initialize(){
        super.initialize();        
        tableview.setEditable(true);
        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("numberOfTimes"));
        amountCol.setCellFactory(
            TextFieldTableCell.<Food,Double>forTableColumn(
                new AmountStringConverter())
        );
        amountCol.setEditable(true);
        amountCol.setText(resources.getString("numberOfTimesCol"));
    }
    
    @Override
    public void invalidated(Observable o){
        ObservableList<Food> selected = FXCollections.observableArrayList(
                getModel().getSelectedFoods());
        //Empty table is allowed to enable a clear function that deletes all the currently selected Food.
        tableview.setItems(selected);
    }
}
