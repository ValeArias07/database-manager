package ui;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;
import model.Generator;
import model.Person;

public class Create {
	
    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField height;

    @FXML
    private ChoiceBox<String> nationalityChoiceBox;

    @FXML
    private WebView webView;

    @FXML
    void initialize() throws InterruptedException {
    	loadImage();
    	initializeChoiceBox();
    	datePicker.setValue(LocalDate.now());
    }
    
    void initializeChoiceBox() {
    	genderChoiceBox.setItems(FXCollections.observableArrayList(Person.GENDER_M, Person.GENDER_F));
    	genderChoiceBox.setValue(Person.GENDER_M);
    	nationalityChoiceBox.setItems(FXCollections.observableArrayList(Generator.COUNTRIES));
    	nationalityChoiceBox.setValue(Generator.COUNTRIES[0]);
    }
    
    private void loadImage() throws InterruptedException {
    	webView.getEngine().load("https://thispersondoesnotexist.com");
    }
    
    @FXML
    void accept(ActionEvent event) throws InterruptedException {
    	if(name.getText().equals("")) {
    		ControllerGUI.loadAlert(AlertType.ERROR,"NAME EMPTY", "You must type the name", "Please type the name and try again.");
    	}else if(height.getText().equals(""))
    		ControllerGUI.loadAlert(AlertType.ERROR,"HEIGHT EMPTY", "You must type the height", "Please type the height and try again.");
    	else if(datePicker.getValue().isAfter(LocalDate.now())){
    		ControllerGUI.loadAlert(AlertType.ERROR,"THE DATE IS INCORRECT", "You must type a valid date", "Please type the date and try again.");
    	}else {
    		String n = name.getText();
    		String g = genderChoiceBox.getValue();
    		LocalDate date = datePicker.getValue();
    		String nationality = nationalityChoiceBox.getValue();
    		try {
    			double h = Double.parseDouble(height.getText());
    			ControllerGUI.data.add(n, (g.equals(Person.GENDER_M)?true:false), date, h, nationality);
    			ControllerGUI.loadAlert(AlertType.INFORMATION, "SUSSESFUL", "The person has been added correctly", "continue adding");
    			loadImage();
    		}catch (NumberFormatException e){
    			ControllerGUI.loadAlert(AlertType.ERROR,"HEIGHT ERROR", "You must type a number in the height field", "Please fix and try again.");
    		}
    	}
    }
}