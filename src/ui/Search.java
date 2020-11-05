package ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;

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
    		if(criterionChoiceBox.getValue().equals(C_ID)) {
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
    void initialize() {
    	initializeChoiceBox();
    }
    
    private void initializeChoiceBox() {
    	criterionChoiceBox.setItems(FXCollections.observableArrayList(C_ID, C_NAME));
    	criterionChoiceBox.setValue(C_ID);
    }
}