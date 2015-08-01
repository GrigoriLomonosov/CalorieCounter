package calories2.View;

import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Jeroen De Meyer
 */
public class TotalProt extends TotalBar{
    
    public void initialize(){
        typeOfTotal.setText(resources.getString("totalProtLabel"));
        typeOfNeeded.setText(resources.getString("neededProtLabel"));
    }
        
    @Override
    public void invalidated(Observable o){
        total.setText(String.valueOf(model.getTotalProt()));
        if(model.getPerson() != null){
            needed.setText(String.valueOf(model.getPerson().getNeededProt()));
            if(model.getTotalProt() > model.getPerson().getNeededProt()){
                total.setTextFill(Color.RED);
            }
        }
        progress.setProgress(model.propertionProt());
    }
}
