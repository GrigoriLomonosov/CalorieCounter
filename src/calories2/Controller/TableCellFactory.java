
package calories2.Controller;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Jeroen De Meyer
 */
public abstract class TableCellFactory<T,S> implements Callback<TableColumn<T,S>,TableCell<T,S>> {
    
    @Override 
    public final TableCell<T,S> call(TableColumn<T,S> p){
        return createTableCell(p);
    }    
    protected abstract TableCell<T,S> createTableCell(TableColumn<T,S>column);    
}