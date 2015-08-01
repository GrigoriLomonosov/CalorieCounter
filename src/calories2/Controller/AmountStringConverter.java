package calories2.Controller;

import javafx.util.StringConverter;

/**
 * Double to String converter, which always return 0 when a negative or non-numeric value is given. No errors are thrown.
 * @author Jeroen De Meyer
 */
public class AmountStringConverter extends StringConverter{
    
    @Override
    public Double fromString(String value){
        if (value == null) {
            return null;
        }
        value = value.trim();
        if (value.length() < 1) {
           return null;
        }
        if(isNumber(value)){
            return Double.valueOf(value);
        }
        return 0.0;
    }
    
    @Override
    public String toString(Object d){
        if(d instanceof Double){
            return Double.toString((Double)d);
        }
        else{
            return "0.0";
        }
    }
    
    private boolean isNumber(String value){
        return value.matches("^\\.?\\d*\\.?\\d+$");
    }
}
