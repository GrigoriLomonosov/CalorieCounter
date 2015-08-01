package calories2.Model;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Jeroen De Meyer
 */
public class Food implements Comparable<Food>{
    
    private final String name;
    public String getName(){return name;}
    
    private final String amount;
    public String getAmount(){return amount;}
    
    private final double cal;
    public double getCal(){return cal;}
    
    private final double carbs;
    public double getCarbs(){return carbs;}
    
    private final double prot;
    public double getProt(){return prot;}
    
    private final double fat;
    public double getFat(){return fat;}
    
    private DoubleProperty numberOfTimes = new SimpleDoubleProperty(1);
    public DoubleProperty numberOfTimesProperty(){
        return numberOfTimes;
    }
    public double getNumberOfTimes(){
        return numberOfTimes.getValue();
    }
    public void setNumberOfTimes(double i){
        numberOfTimes.setValue(i);
    }
    
    public Food(String name, String amount, double cal, double carbs, double prot, double fat){
        this.name = name.trim().toLowerCase();
        this.amount = amount;
        this.cal = cal;
        this.carbs = carbs;
        this.prot = prot;
        this.fat = fat;
    }
    
    @Override
    public int compareTo(Food f){
        return this.getName().compareTo(f.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.amount);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.cal) ^ (Double.doubleToLongBits(this.cal) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.carbs) ^ (Double.doubleToLongBits(this.carbs) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.prot) ^ (Double.doubleToLongBits(this.prot) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.fat) ^ (Double.doubleToLongBits(this.fat) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Food other = (Food) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (Double.doubleToLongBits(this.cal) != Double.doubleToLongBits(other.cal)) {
            return false;
        }
        if (Double.doubleToLongBits(this.carbs) != Double.doubleToLongBits(other.carbs)) {
            return false;
        }
        if (Double.doubleToLongBits(this.prot) != Double.doubleToLongBits(other.prot)) {
            return false;
        }
        if (Double.doubleToLongBits(this.fat) != Double.doubleToLongBits(other.fat)) {
            return false;
        }
        return true;
    }
    
}
