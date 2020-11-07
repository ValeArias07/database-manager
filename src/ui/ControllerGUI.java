package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.DataBase;
import model.ProgressBarController;

public class ControllerGUI {

	public static final String SERIAL_ROUTE= "SerialData/data.save";
	public static final String MENU_FXML = "menuPage.fxml";
	public static final String GENERATE_FXML = "generatePage.fxml";
	
    @FXML
    private AnchorPane principalPane;

    @FXML
    private AnchorPane buttonAnchor;

	public static DataBase data;
    private Generate generatePage;
    private Menu menuPage;
    private ProgressBarController pb;
    
    public ControllerGUI() {
    	data = new DataBase();
    	pb =  new ProgressBarController();
    	generatePage = new Generate(this, pb);
    	menuPage = new Menu(new Create(), new Search(), new UpdateAndDelete());
    }
    
    @FXML
    void LoadData(ActionEvent event) throws IOException, ClassNotFoundException {
    	load();
    	loadMenu();
    }
    
    public void loadMenu() throws IOException {
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
    
	@SuppressWarnings("resource")
	private void load() throws ClassNotFoundException {

		File file = new File(SERIAL_ROUTE);

		if(file.exists()) {
			try {
				FileInputStream fi = new FileInputStream(SERIAL_ROUTE);
				ObjectInputStream oi;
				oi = new ObjectInputStream(fi);
				ControllerGUI.data = (DataBase)oi.readObject();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
	}
}
