package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class ControllerGUI {

	public static final String MENU_FXML = "menuPage.fxml";
	public static final String GENERATE_FXML = "generatePage.fxml";
	
    @FXML
    private AnchorPane principalPane;

    @FXML
    private AnchorPane buttonAnchor;

    public static Controller controller;
    private Generate generatePage;
    private Menu menuPage;
    
    public ControllerGUI(Controller c) {
    	controller = c;
    	generatePage = new Generate();
    	menuPage = new Menu(new Create(), new Search(), new UpdateAndDelete());
    }
    
    @FXML
    void LoadData(ActionEvent event) throws IOException {
    	//LOAD SERIALIZATION
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_FXML));
		fxmlLoader.setController(menuPage);
		Parent parent = fxmlLoader.load();
		principalPane.getChildren().setAll(parent);
    }

    @FXML
    void generateNewData(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GENERATE_FXML));
		fxmlLoader.setController(generatePage);
		Parent parent = fxmlLoader.load();
		principalPane.getChildren().setAll(parent);
    }
    
    public static void loadAlert(AlertType type, String title, String middle, String bot) {
		Alert alert = new Alert(type);
		alert.setContentText(bot);
		alert.setHeaderText(middle);
		alert.setTitle(title);
		alert.show();
	}
}
