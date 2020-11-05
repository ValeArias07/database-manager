package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static final String WELCOME_FXML = "welcomePage.fxml";
	private ControllerGUI gui;
	
	public Main() throws IOException {
		gui = new ControllerGUI(new Controller());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(WELCOME_FXML));
		fxmlLoader.setController(gui);
		Parent parent = fxmlLoader.load();
	
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Database-Manager");
		primaryStage.getIcons().add(new Image("images/logo.png"));
		primaryStage.show();
	}
}
