package ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class Menu {

	public static final String CREATE_FXML = "createPage.fxml";
	public static final String SEARCH_FXML = "searchPage.fxml";
	public static final String UPDATE_DELETE_FXML = "update&deletePage.fxml";
	
	@FXML
    private AnchorPane principalPane;
	
    @FXML
    private CheckMenuItem create;

    @FXML
    private CheckMenuItem search;

    @FXML
    private CheckMenuItem update;
	
    private Create createController;
    private Search searchController;
    private UpdateAndDelete upAndDelController;
    
    public Menu(Create c, Search s, UpdateAndDelete u) {
    	createController =  c;
    	searchController = s;
    	upAndDelController = u;
	}
    
    @FXML
    public void initialize() throws IOException {
    	loadFxml(CREATE_FXML, createController); 
    }
    
    @FXML
    void goCreate(ActionEvent event) throws IOException {
    	if(create.isSelected()) {
    		search.setSelected(false);
        	update.setSelected(false);
        	loadFxml(CREATE_FXML, createController); 
    	}else
    		create.setSelected(true);
    }

    @FXML
    void goSearch(ActionEvent event) throws IOException {
    	if(search.isSelected()) {
    		create.setSelected(false);
        	update.setSelected(false);
        	loadFxml(SEARCH_FXML, searchController);
    	}else
    		search.setSelected(true);
    }

    @FXML
    void goUpdate(ActionEvent event) throws IOException {
    	if(update.isSelected()) {
    		search.setSelected(false);
        	create.setSelected(false);
        	loadFxml(UPDATE_DELETE_FXML, upAndDelController);
    	}else
    		update.setSelected(true);
    }

    @FXML
    void save(ActionEvent event) {
    	save();
    }
    
    private void save() {
		File file = new File(ControllerGUI.SERIAL_ROUTE);

		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		try {
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(ControllerGUI.data);
			oo.close();
			ControllerGUI.loadAlert(AlertType.INFORMATION, "SUSSESFUL", "The data has been saved", ":)");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
    
    public void loadFxml(String n, Object c) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(n));
		fxmlLoader.setController(c);
		Parent parent = fxmlLoader.load();
		principalPane.getChildren().setAll(parent);
    }
}
