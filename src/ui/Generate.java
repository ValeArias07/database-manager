package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Generate {

    @FXML
    private TextField amount;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label timeLabel;

    private ControllerGUI control; 
    
    public Generate(ControllerGUI control) {
    	this.control = control;
    }
    
    @FXML
    void generate(ActionEvent event) throws IOException {
    	try {
			int cant = Integer.parseInt(amount.getText());
			ControllerGUI.data.create(cant);
			control.loadMenu();
		}catch (NumberFormatException e){
			ControllerGUI.loadAlert(AlertType.ERROR,"AMOUNT ERROR", "You must type a number in the amount field", "Please fix and try again.");
		}
    }
}
