
package calories2.Controller;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author oxfam
 */
public abstract class TableRowFactory<T> implements Callback<TableView<T>,TableRow<T>>{
    @Override
    public final TableRow<T> call(TableView<T> p){
        return createTableRow(p);
    }
    protected abstract TableRow<T> createTableRow(TableView<T> table);   
}