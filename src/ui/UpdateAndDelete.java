package ui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import model.Generator;
import model.Person;

public class UpdateAndDelete {

    @FXML
    private Button delete;

    @FXML
    private Button update;
	
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
    private WebView webView;

    private Person selectedPerson;
    
    @FXML
    void initialize() {
    	initializeChoiceBox();
    }
    
    @FXML
    void search(ActionEvent event) throws InterruptedException {
    	if(searchParam.getText().equals("")) 
    		ControllerGUI.loadAlert(AlertType.ERROR,"PARAM EMPTY", "You must type the param", "Please type the param and try again.");
    	else {
    		String idSearch = searchParam.getText();
			search(idSearch);
    	}
    }
    
    private void search(String id) throws InterruptedException {
    	selectedPerson = ControllerGUI.data.search(id);
    	if(selectedPerson!=null) {
    		nameTextField.setText(selectedPerson.getName());
    		genderChoiceBox.setValue((selectedPerson.getGender())?Person.GENDER_M:Person.GENDER_F);
    		datePicker.setValue(selectedPerson.getBornDate());
    		heightTextField.setText(selectedPerson.getHeight()+"");
    		nationalityChoiceBox.setValue(selectedPerson.getNationality());
    		loadImage();
    		dataVBox.setDisable(false);
    		update.setDisable(false);
    		delete.setDisable(false);
    	}else {
    		ControllerGUI.loadAlert(AlertType.INFORMATION, "NOT FOUND", "THE PERSON DOES NOT EXIST", "try with other person");
    	}
    }
    
    @FXML
    void delete(ActionEvent event) {
    	ControllerGUI.data.remove(selectedPerson.getId());
    	ControllerGUI.loadAlert(AlertType.INFORMATION, "SUSSESFUL", "The person has been eleminated correctly", ":)");
    	clean();
    }

    @FXML
    void update(ActionEvent event) {
    	String nam = nameTextField.getText();
		String gender = genderChoiceBox.getValue();
		LocalDate ld = datePicker.getValue();
		String nat = nationalityChoiceBox.getValue();
		try {
			double h = Double.parseDouble(heightTextField.getText());
			if(!nam.equals("")) {
				selectedPerson.setName(nam);
				selectedPerson.setGender((gender.equals(Person.GENDER_M))?true:false);
				selectedPerson.setBornDate(ld);
				selectedPerson.setNationality(nat);
				selectedPerson.setHeight(h);
				clean();
			}else {
				ControllerGUI.loadAlert(AlertType.ERROR,"NAME ERROR", "You must type a valid name", "Please fix and try again.");
			}
		}catch (NumberFormatException e){
			ControllerGUI.loadAlert(AlertType.ERROR,"HEIGHT ERROR", "You must type a number in the height field", "Please fix and try again.");
		}
		
    }
    
    private void clean() {
    	nameTextField.setText("");
    	genderChoiceBox.setValue(Person.GENDER_M);
		datePicker.setValue(LocalDate.now());
		heightTextField.setText("");
		nationalityChoiceBox.setValue(Generator.COUNTRIES[0]);
		dataVBox.setDisable(true);
		update.setDisable(true);
		delete.setDisable(true);
		searchParam.setText("");
    }
    
    private void loadImage() throws InterruptedException {
    	webView.getEngine().load("https://thispersondoesnotexist.com");
    }
    
    void initializeChoiceBox() {
    	genderChoiceBox.setItems(FXCollections.observableArrayList(Person.GENDER_M, Person.GENDER_F));
    	genderChoiceBox.setValue(Person.GENDER_M);
    	nationalityChoiceBox.setItems(FXCollections.observableArrayList(Generator.COUNTRIES));
    	nationalityChoiceBox.setValue(Generator.COUNTRIES[0]);
    }
}
