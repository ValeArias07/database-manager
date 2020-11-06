package ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;
import model.Person;

public class Search {

	public static final String C_ID = "Id";
	public static final String C_NAME = "Name";
	
    @FXML
    private Label nameShown;

    @FXML
    private Label genderShown;

    @FXML
    private Label dateShown;

    @FXML
    private Label heightShown;

    @FXML
    private Label nationalityShown;

    @FXML
    private WebView webView;

    @FXML
    private ChoiceBox<String> criterionChoiceBox;

    @FXML
    private TextField searchParam;

    @FXML
    void search(ActionEvent event) {
    	if(searchParam.getText().equals("")) 
    		ControllerGUI.loadAlert(AlertType.ERROR,"PARAM EMPTY", "You must type the param", "Please type the param and try again.");
    	else {
    		String idSearch = searchParam.getText();
			search(idSearch);
    	}
    }
    
    private void search(String id) {
    	Person p = ControllerGUI.data.search(id);
    	if(p!=null) {
    		nameShown.setText(p.getName());
    		genderShown.setText((p.getGender())?"MAN":"WOMAN");
    		dateShown.setText(p.getBornDate().toString());
    		heightShown.setText(p.getHeight()+"");
    		nationalityShown.setText(p.getNationality());
    		webView.getEngine().load("https://thispersondoesnotexist.com");
    	}else {
    		ControllerGUI.loadAlert(AlertType.INFORMATION, "NOT FOUND", "THE PERSON DOES NOT EXIST", "try with other person");
    	}
    }
    
    @FXML
    void initialize() {
    	initializeChoiceBox();
    }
    
    private void initializeChoiceBox() {
    	criterionChoiceBox.setItems(FXCollections.observableArrayList(C_ID, C_NAME));
    	criterionChoiceBox.setValue(C_ID);
    }
}