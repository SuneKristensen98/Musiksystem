package presentation;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Editor {

	Scene Editor;
	
	public void start() {
		
		
		BorderPane borderpane = new BorderPane();
		Stage editor = new Stage();
		editor.initModality(Modality.APPLICATION_MODAL);
		editor.setTitle("editor");
		Editor = new Scene(borderpane, 1600, 900);
		editor.showAndWait();
		

		
	}
	
}
