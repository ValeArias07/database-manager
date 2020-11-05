package ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;
import model.Generator;

public class Create {

	private final String GENDER_M = "Male";
	private final String GENDER_F = "Female";
	
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
    void initialize() {
    	webView.getEngine().load("https://thispersondoesnotexist.com");
    	initializeChoiceBox();
    }
    
    void initializeChoiceBox() {
    	genderChoiceBox.setItems(FXCollections.observableArrayList(GENDER_M, GENDER_F));
    	genderChoiceBox.setValue(GENDER_M);
    	nationalityChoiceBox.setItems(FXCollections.observableArrayList(Generator.COUNTRIES));
    	nationalityChoiceBox.setValue(Generator.COUNTRIES[0]);
    }
    
    @FXML
    void accept(ActionEvent event) {
    	if(name.getText().equals("")) {
    		ControllerGUI.loadAlert(AlertType.ERROR,"NAME EMPTY", "You must type the name", "Please type the name and try again.");
    	}else if(height.getText().equals(""))
    		ControllerGUI.loadAlert(AlertType.ERROR,"HEIGHT EMPTY", "You must type the height", "Please type the height and try again.");
    	else {
    		String n = name.getText();
    		String h = height.getText();
    	}
    }
}