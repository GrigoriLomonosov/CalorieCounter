package calories2.View;

import javafx.beans.Observable;
import javafx.scene.paint.Color;

/**
 *
 * @author Jeroen De Meyer
 */
public class TotalCal extends TotalBar{
    
    public void initialize(){
        typeOfTotal.setText(resources.getString("totalCalLabel"));
        typeOfNeeded.setText(resources.getString("neededCalLabel"));
    }
    
    @Override
    public void invalidated(Observable o){
        total.setText(String.valueOf(model.getTotalCal()));
        if(model.getPerson() != null){
            needed.setText(String.valueOf(model.getPerson().getNeededCal()));
            if(model.getTotalCal() > model.getPerson().getNeededCal()){
                total.setTextFill(Color.RED);
            }
        }
        progress.setProgress(model.proportionCal());
    }
}
