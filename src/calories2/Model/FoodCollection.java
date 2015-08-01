package calories2.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jeroen De Meyer
 */
public class FoodCollection {
    
    // the maximum number of search results that will be displayed.
    private final int MAX_NUMBER_RESULTS = 50;
    
    private final Set<Food> foodCollection = new HashSet<>();
    public Set<Food> getFoodCollection(){return foodCollection;}
    
    public FoodCollection(){
        makeCollection();
    }

    private void makeCollection(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                        FoodCollection.class.getResourceAsStream("/resources/Calories.txt")));
            String line = reader.readLine();
            while(line!=null){
                String[]food = dataFromString(line);
                foodCollection.add(new Food(
                                    food[0].trim().toLowerCase(),
                                    food[1].trim().toLowerCase(),
                                    Double.parseDouble(food[2]),
                                    //de 5e kolom zijn de eiwitten en de 4e kolom de carbs, daarom indexen omgedraaid.
                                    Double.parseDouble(food[4]),
                                    Double.parseDouble(food[3]),
                                    Double.parseDouble(food[5])));
                line = reader.readLine();
            }
        }catch(IOException e){
            System.out.println("Couldn't read file");
        }
    }
    
    private String[] dataFromString(String s){
        return s.split("/");
    }
    
    public List<Food> search(String s){
        List<Food> results = new ArrayList<>();
        for(Food food: foodCollection){
            if(food.getName().startsWith(s.trim().toLowerCase())){
                results.add(food);
            }
            if(results.size() >= MAX_NUMBER_RESULTS){
                break;
            }
        }
        return results;
    }
}
