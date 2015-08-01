package calories2.View;

import calories2.Model.Food;
import calories2.Model.Model;
import calories2.Model.Person;
import calories2.Model.Sex;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jeroen De Meyer
 */
public class StartView extends VBox implements InvalidationListener{

    public ResourceBundle resources;
    @FXML
    public Button deleteBtn;
    @FXML
    public Button clearBtn;
    @FXML
    public Button calcBtn;
    @FXML
    public TextField ageField;
    @FXML
    public TextField weightField;
    @FXML
    public TextField heightField;
    @FXML
    public ChoiceBox paChoice;
    @FXML
    public ChoiceBox sexChoice;
    @FXML
    public TextField searchField;
    @FXML
    public Button searchBtn;
    @FXML
    public Button makePerson;
    @FXML
    public TableView<Food> selectedFoods;
    @FXML
    public TotalBar totalCal;
    @FXML
    public TotalBar totalCarbs;
    @FXML
    public TotalBar totalProt;
    @FXML
    public TotalBar totalFat;

    public Model model;
    
    public void initialize(){
        paChoice.setItems(FXCollections.observableArrayList(
                createOptions("options.pa",5)));
        sexChoice.setItems(FXCollections.observableArrayList(
                createOptions("options.sex",2)));
        //Make sure a person can only be created when valid integers are entered in every textfield and a choice is made
        //with the choiceboxes
        InvalidationListener listener = new InvalidationListener(){
            @Override
            public void invalidated(Observable o){
                makePerson.setDisable(numberIncorrect(ageField.getText(),1,125) ||
                            numberIncorrect(weightField.getText(),10,300) ||
                            numberIncorrect(heightField.getText(),50,250) ||
                            paChoice.getSelectionModel().selectedIndexProperty().getValue() == -1 ||
                            sexChoice.getSelectionModel().selectedIndexProperty().getValue() == -1);
            }
        };
        ageField.textProperty().addListener(listener);
        heightField.textProperty().addListener(listener);
        weightField.textProperty().addListener(listener);
        paChoice.getSelectionModel().selectedIndexProperty().addListener(listener);
        sexChoice.getSelectionModel().selectedIndexProperty().addListener(listener); 
        //Adds the startView as listener to the model.
        model.addListener(this);
    }
    
    public void handleSearch(ActionEvent e){
        if (searchField.getText() != null && !searchField.getText().trim().isEmpty()){
            model.search(searchField.getText());
        }
    }
    
    public void handleClear(ActionEvent e){
        model.clearSelection();
    }
    
    public void handleCalculate(ActionEvent e){
        model.calculateTotals();
    }
    
    public void handleDelete(ActionEvent e){
        Food f = selectedFoods.getSelectionModel().getSelectedItem();
        if(f != null){
            model.removeFromSelection(selectedFoods.getSelectionModel().getSelectedItem());
        }
        selectedFoods.getSelectionModel().clearSelection();
    }
    
    public void handleMakePerson(ActionEvent e){
        Sex sex = Sex.MALE;
        if(sexChoice.getSelectionModel().selectedIndexProperty().getValue()==0){
            sex = Sex.FEMALE;
        }
        Person pers = new Person(
                sex,
                paChoice.getSelectionModel().selectedIndexProperty().getValue(),
                Integer.parseInt(ageField.getText()),
                Integer.parseInt(heightField.getText()),
                Integer.parseInt(weightField.getText()));
        model.setPerson(pers);
    }
    
    @Override
    public void invalidated(Observable o){            
        calcBtn.setDisable(model.getSelectedFoods().isEmpty());
        clearBtn.setDisable(model.getSelectedFoods().isEmpty());
    }
    
    /**
     * @param number String representation of an integer
     * @param max
     * @param min
     * @return true if the given number is greater than the max or smaller than the min.
     */
    private boolean numberIncorrect(String number, int min, int max){
        if(!number.equals("")){
            return Integer.parseInt(number) > max || Integer.parseInt(number) < min;
        }
        // When the number is an empty string, it can't be used, so we return the same result as if the number is incorrect.
        return true;
    }
    
    private List<String> createOptions(String s, int numberOfOptions){
        List<String> list = new ArrayList<>();
        MessageFormat mf = new MessageFormat(resources.getString(s));
        for(int i=0; i<numberOfOptions; i++){
            String option = mf.format(new Integer[]{i});
            list.add(option);
        }
        return list;
    }
}
