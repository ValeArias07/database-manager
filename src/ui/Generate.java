package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.FillDataBase;
import model.ProgressBarController;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class Generate{

    @FXML
    private Button generateButton;
	
    @FXML
    private Button acceptButton;
    
    @FXML
    private TextField amount;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label timeLabel;

    private FillDataBase fdb;
    private ControllerGUI control; 
    private ProgressBarController pb;
    
    public Generate(ControllerGUI control, ProgressBarController pb) {
    	this.control = control;
    	this.pb = pb;
    	fdb = new FillDataBase(this, pb);
    }
    
    @FXML
    void generate(ActionEvent event) {
    	try {
			int cant = Integer.parseInt(amount.getText());
			fdb.create(cant);
		}catch (NumberFormatException e){
			ControllerGUI.loadAlert(AlertType.ERROR,"AMOUNT ERROR", "You must type a number in the amount field", "Please fix and try again.");
		}
    }
    
    @FXML
    void accept(ActionEvent event) throws IOException {
    	loadNextPage();
    	pb.resetProgress();
    	progressBar.setProgress(pb.getProgress());
    }
    
    public void updateProgressBar() {
    	progressBar.setProgress(pb.getProgress());
    }
    
    public void setTime(String time) {
    	timeLabel.setText(time);
    }
    
    public void setEnableButton() {
    	acceptButton.setDisable(false);
    	generateButton.setDisable(true);
    }
    
    private void loadNextPage() throws IOException{
    	control.loadMenu();
    }
}
