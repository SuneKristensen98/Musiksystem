package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Editor {

	Scene Editor;
	
	public void start() {
		
		//Class Call
		EditorSong editorSong = new EditorSong();
		EditorAlbum editorAlbum = new EditorAlbum();
		EditorBottom editorBottom = new EditorBottom();
		
		//Editor Start Pane
		BorderPane borderpane = new BorderPane();
		Stage editor = new Stage();
		editor.initModality(Modality.APPLICATION_MODAL);
		editor.setTitle("Editor");
		Editor = new Scene(borderpane, 1200, 900);	
		
		//Pane Placement
		borderpane.setRight(editorSong.editorSong());
		borderpane.setBottom(editorBottom.editorBottom());
		borderpane.setCenter(editorAlbum.editorAlbum());
		
		//Scene Editor
		editor.setScene(Editor);
		editor.showAndWait();
	}

}

