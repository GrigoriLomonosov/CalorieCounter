
package calories2.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Jeroen De Meyer
 */
public class DoubleClickTableRowFactory<T> extends TableRowFactory<T>{
    
    private EventHandler<ActionEvent> actioneventhandler;
    
    public void setOnAction(EventHandler<ActionEvent> actioneventhandler){
        this.actioneventhandler = actioneventhandler;
    }
    
    @Override
    protected TableRow<T> createTableRow(TableView<T>p){
        return new DoubleClickTableRow<>();
    }
    
    private class DoubleClickTableRow<T> extends TableRow<T>
        implements EventHandler<MouseEvent>{
        
        public DoubleClickTableRow(){
            setOnMouseClicked(this);
        }
        
        @Override
        public void handle(MouseEvent m){
            if(m.getClickCount()>1){
                ActionEvent ae = new ActionEvent(this,null);
                actioneventhandler.handle(ae);
            }
        }
    }  
}