package presentation;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
		editor.setResizable(false);
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

