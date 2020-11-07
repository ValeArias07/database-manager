package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;
import model.Person;

public class Search {
	
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
    private TextField searchParam;

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
    	Person p = ControllerGUI.data.search(id);
    	if(p!=null) {
    		nameShown.setText(p.getName());
    		genderShown.setText((p.getGender())?Person.GENDER_M:Person.GENDER_F);
    		dateShown.setText(p.getBornDate().toString());
    		heightShown.setText(p.getHeight()+"");
    		nationalityShown.setText(p.getNationality());
    		loadImage();
    	}else {
    		ControllerGUI.loadAlert(AlertType.INFORMATION, "NOT FOUND", "THE PERSON DOES NOT EXIST", "try with other person");
    	}
    }
    
    private void loadImage() throws InterruptedException {
    	webView.getEngine().load("https://thispersondoesnotexist.com");
    }
}