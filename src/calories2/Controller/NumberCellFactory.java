
package calories2.Controller;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Jeroen De Meyer
 */
public class NumberCellFactory<T> extends TableCellFactory<T,Number>{
    
    private class NumberCell<T> extends TextFieldTableCell<T,Number>{
        
        public NumberCell(){
            super (new NumberStringConverter());
            setAlignment(Pos.BASELINE_RIGHT);
        }
        
        @Override
        public void updateItem(Number value, boolean empty){
            super.updateItem(value,empty);
            if(empty){
                setText(null);
            }else{
                setText(value.toString());
            }
        }
    }
    
    @Override
    public TableCell<T,Number> createTableCell(TableColumn<T,Number>p){
        return new NumberCell<>();
    }        
}