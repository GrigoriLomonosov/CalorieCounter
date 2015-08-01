
package calories2.Controller;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

/**
 *
 * @author Jeroen De Meyer
 */
public class MarkedCellFactory<T> extends TableCellFactory<T,Double>{
    
    private class MarkedCell<T> extends TableCell<T,Double>{
        
       @Override
       protected void updateItem(Double value, boolean empty){
           super.updateItem(value, empty);
           setTextFill(Color.BLACK);
           if(empty){
               setText(null);
           }
           else{
               setText(value.toString());
               if(value>10){
                   setTextFill(Color.RED);
               }
           }
       }
    }
    @Override
    public TableCell<T,Double> createTableCell(TableColumn<T,Double> col){
        return new MarkedCell<T>();
    }   
}
