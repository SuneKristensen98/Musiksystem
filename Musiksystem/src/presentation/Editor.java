package presentation;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.BravoMusic;

public class Editor {

	Scene Editor;
	
	public void start(BravoMusic bravoMusic, int albumId) {
		
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
		borderpane.setRight(editorSong.editorSong(bravoMusic));
//		borderpane.setBottom(editorBottom.editorBottom());
		borderpane.setCenter(editorAlbum.editorAlbum(editor, bravoMusic, albumId));
		
		//Scene Editor
		editor.setScene(Editor);
		editor.showAndWait();
	}

}

