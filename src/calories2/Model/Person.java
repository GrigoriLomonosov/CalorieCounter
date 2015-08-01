package calories2.Model;

import java.util.Objects;

/**
 *
 * @author Jeroen De Meyer
 */
public class Person {
        
    //ranges from 0 t 4.
    private final int pa;
    public int getPa(){return pa;}
    
    public Sex sex;
    public Sex getSex(){return sex;}
    
    public final int age;
    public int getAge(){return age;}
    
    //height in cm
    public final int height;
    public int getHeight(){return height;}
    
    //in kg
    public final int weight;
    public int getWeight(){return weight;}
    
    //Array of values needed to calculate needed calories a day
    private final double[]exercise = {1.2,1.375,1.55,1.725,1.9};
    
    //map with number of protein per kg bodyweight
    private final double[] protein = {0.8,1.0,1.2,1.4,1.6};
    
    private final double BMR;
    public double getBMR(){return BMR;}
    
    private final int neededCal;
    public int getNeededCal(){return neededCal;}
    
    //minimum and maximum needed carbs a day in gram
    private final int[] neededCarbs;
    public int[] getNeededCarbs(){return neededCarbs;}
    
    private final int neededProt;
    public int getNeededProt(){return neededProt;}
    
    private final int[] neededFat;
    public int[] getNeededFat(){return neededFat;}
    
    public Person(Sex sex, int pa, int age, int height, int weight){        
        this.sex = sex;
        this.pa = pa;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.BMR = calculateBMR();
        this.neededCal = neededCal();
        this.neededCarbs = neededCarbs();
        this.neededProt = neededProt();
        this.neededFat = neededFat();
    }
    
    /**
     * Returns BMR according to Mifflin St Jeor Equation. This equation should be the most
     * accurate (July 2015)
     * @param kg
     * @param cm
     * @param years
     * @return Basal Metabolic Rate
     */
    private double calculateBMR(){
        double base = (10*weight)+(6.25*height)-(5*age);
        switch (sex){
            case MALE:
                return base + 5;
            case FEMALE:
                return base - 161;
            default:
                //Moet hier een error worden gegooid?
                return -1;
        }       
    }
    
    /**
     * Multiplies the Basal Metabolic Rate with a certain factor according to physical activity.
     * @return needed cals
     */
    private int neededCal(){
        return (int)(getBMR()*exercise[pa]);
    }
    
    /**
     * Takes 45-65% of needed calories as needed carbohydrates. 1 gram of carbohydrate = 4 cal.
     * @return needed carbs
     */
    private int[] neededCarbs(){
        // 1 gram of carbs is equal to 4 calories
        // 45-65% http://www.dining.ucla.edu/housing_site/dining/SNAC_pdf/CaloriesCount.pdf
        return new int[]{((getNeededCal()/100)*45)/4,((getNeededCal()/100)*65)/4};
    }
    
    /**
     * Multiplies the bodyweight with 0.8,1.0,1.2,1.4,1.6 according to physical activity
     * @return needed protein 
     */
    private int neededProt(){
        return (int)(getWeight()*protein[pa]);
    }
    /**
     * Takes 20-35% of needed calories as needed fat. 1 gram of fat = 9 cal.
     * @return needed fat
     */
    private int[] neededFat(){
        // 1 gram of fat is equal to 9 calories
        // 20-35% http://www.dining.ucla.edu/housing_site/dining/SNAC_pdf/CaloriesCount.pdf
        // http://www.heart.org/HEARTORG/Conditions/Cholesterol/PreventionTreatmentofHighCholesterol/Know-Your-Fats_UCM_305628_Article.jsp
        return new int[]{((getNeededCal()/100)*20)/9,((getNeededCal()/100)*35)/9};
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.pa;
        hash = 47 * hash + Objects.hashCode(this.sex);
        hash = 47 * hash + this.age;
        hash = 47 * hash + this.height;
        hash = 47 * hash + this.weight;
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
        final Person other = (Person) obj;
        if (this.pa != other.pa) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        return true;
    }

    
    
}
