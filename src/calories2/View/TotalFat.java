package calories2.View;

import java.text.MessageFormat;
import javafx.beans.Observable;
import javafx.scene.paint.Color;

/**
 *
 * @author Jeroen De Meyer
 */
public class TotalFat extends TotalBar{
    
    public void initialize(){
        typeOfTotal.setText(resources.getString("totalFatLabel"));
        typeOfNeeded.setText(resources.getString("neededFatLabel"));
    }
    
    @Override
    public void invalidated(Observable o){
        total.setText(String.valueOf(model.getTotalFat()));
        if(model.getPerson() != null){
            //geeft een array terug
            String min = String.valueOf(model.getPerson().getNeededFat()[0]);
            String max = String.valueOf(model.getPerson().getNeededFat()[1]);
            String pattern = resources.getString("minMaxMessage");
            MessageFormat mf = new MessageFormat(pattern);
            String message = mf.format(new String[]{min,max});
            needed.setText(message);
            //Compares the max needed fat from the person with the total.
            if(model.getTotalFat() > model.getPerson().getNeededFat()[1]){
                total.setTextFill(Color.RED);
            }
        }
        progress.setProgress(model.proportionFat());
    }
}
