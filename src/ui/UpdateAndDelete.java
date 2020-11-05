package ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import model.Generator;
import model.Person;

public class UpdateAndDelete {

    @FXML
    private VBox dataVBox;
	
    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField heightTextField;

    @FXML
    private ChoiceBox<String> nationalityChoiceBox;

    @FXML
    private TextField searchParam;

    @FXML
    private ChoiceBox<String> criterionChoiceBox;

    @FXML
    private WebView webView;

    @FXML
    void initialize() {
    	initializeChoiceBox();
    }
    
    @FXML
    void search(ActionEvent event) {
    	if(searchParam.getText().equals(""))
    		ControllerGUI.loadAlert(AlertType.ERROR,"PARAM EMPTY", "You must type the param", "Please type the param and try again.");
    	else {
    		if(criterionChoiceBox.getValue().equals(Search.C_ID)) {
    			try {
        			int idSearch = Integer.parseInt(searchParam.getText());
        			search();//
        		}catch (NumberFormatException e){
        			ControllerGUI.loadAlert(AlertType.ERROR,"PARAM ERROR", "You must type a number in the param with id criterion", "Please fix and try again.");
        		}
        	}else {
        		search();//
        	}
    	}
    }
    
    private void search() {
    	webView.getEngine().load("https://thispersondoesnotexist.com");
    }
    
    @FXML
    void delete(ActionEvent event) {
    	
    }

    @FXML
    void update(ActionEvent event) {
    	
    }
    
    void initializeChoiceBox() {
    	genderChoiceBox.setItems(FXCollections.observableArrayList(Person.GENDER_M, Person.GENDER_F));
    	genderChoiceBox.setValue(Person.GENDER_M);
    	nationalityChoiceBox.setItems(FXCollections.observableArrayList(Generator.COUNTRIES));
    	nationalityChoiceBox.setValue(Generator.COUNTRIES[0]);
    	criterionChoiceBox.setItems(FXCollections.observableArrayList(Search.C_ID, Search.C_NAME));
    	criterionChoiceBox.setValue(Search.C_ID);
    }
}
