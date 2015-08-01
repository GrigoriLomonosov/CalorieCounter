package calories2.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * @author Jeroen De Meyer
 */
public class Model implements Observable{
    
    private final FoodCollection foodCollection = new FoodCollection();
    
    private List<Food> searchResults = new ArrayList<>();
    public List<Food> getSearchResults(){return searchResults;}
    
    private final Set<Food> selectedFoods = new HashSet<>();
    public Set<Food> getSelectedFoods(){return selectedFoods;}
    
    private Person person;
    public Person getPerson(){return person;}
    public void setPerson(Person p){
        if(person==null){
            person = p;
            fireInvalidationEvent();
        }
        else if(!person.equals(p)){
            person = p;
            fireInvalidationEvent();
        }    
    }
    
    private double totalCal = 0;
    public double getTotalCal() {
        return Math.rint(totalCal);
    }
    
    private double totalCarbs = 0;
    public double getTotalCarbs() {
        return Math.rint(totalCarbs);
    }

    private double totalProt = 0;
    public double getTotalProt() {
        return Math.rint(totalProt);
    }

    private double totalFat = 0;
    public double getTotalFat() {
        return Math.rint(totalFat);
    }
    
    public void calculateTotals(){
        totalCal = 0;
        totalCarbs = 0;
        totalProt = 0;
        totalFat = 0;
        for(Food f: selectedFoods){
           totalCal += f.getCal()*f.getNumberOfTimes();
           totalCarbs += f.getCarbs()*f.getNumberOfTimes();
           totalProt += f.getProt()*f.getNumberOfTimes();
           totalFat += f.getFat()*f.getNumberOfTimes();
        }
        fireInvalidationEvent();
    }
    
    public void search(String s){
        searchResults = foodCollection.search(s);
        if(!searchResults.isEmpty()){
            fireInvalidationEvent();
        }
    }
    
    public void select(Food food){
        if(selectedFoods.add(food)){
            fireInvalidationEvent();
        }
    }
    
    public void removeFromSelection(Food f){
        if(selectedFoods.remove(f)){
            fireInvalidationEvent();
        }   
    }
    
    public void clearSelection(){
        if(!selectedFoods.isEmpty()){
            selectedFoods.clear();
            fireInvalidationEvent();
        }
    }
    
    public void clearSearchResults(){
        if(!searchResults.isEmpty()){
            searchResults.clear();
            fireInvalidationEvent();
        }
    }
    
    //Proportion needed calories for the person-total calories.
    public double proportionCal(){
        if(person != null){
            return totalCal/person.getNeededCal();
        }
        return 0.0;
    }
    
    //Proportion needed carbs for the person-total carbs.
    public double proportionCarbs(){
        if(person != null){
            return totalCarbs/person.getNeededCarbs()[0];
        }
        return 0.0;
    }
    
    //Proportion needed protein for the person-total protein.
    public double propertionProt(){
        if(person != null){
            return totalProt/person.getNeededProt();
        }
        return 0.0;
    }
    
    //Proportion needed fat for the person-total fat.
    public double proportionFat(){
        if(person != null){
            return totalFat/person.getNeededFat()[0];
        }
        return 0.0;
    }
    
    private final List<InvalidationListener> listenerList = new ArrayList<>();
    @Override
    public void addListener(InvalidationListener listener){
        listenerList.add(listener);
    }
    @Override
    public void removeListener(InvalidationListener listener){
        listenerList.add(listener);
    }

    public void fireInvalidationEvent(){
        for(InvalidationListener listener: listenerList){
            listener.invalidated(this);
        }
    }
}
