package presentation;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.BravoMusic;

public class Editor {

	Scene Editor;
	
	public void start(BravoMusic bravoMusic, int albumId) {
		
		//Class Call
		EditorSong editorSong = new EditorSong();
		EditorAlbum editorAlbum = new EditorAlbum();
		//EditorBottom editorBottom = new EditorBottom();

		
		//Editor Start Pane
		BorderPane borderpane = new BorderPane();
		Stage editor = new Stage();
		editor.initModality(Modality.APPLICATION_MODAL);
		editor.setTitle("Editor");
		editor.setResizable(false);
		Editor = new Scene(borderpane, 1200, 900);	
		
		//Sørger for at vindue ikke kan lukkes med kryds
		editor.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
		
		//Pane Placement
		editorSong.editorSong(borderpane, bravoMusic, -2);
//		borderpane.setBottom(editorBottom.editorBottom());
		borderpane.setCenter(editorAlbum.editorAlbum(borderpane, editor, bravoMusic, albumId, editorSong));
		
		//Scene Editor
		editor.setScene(Editor);
		editor.showAndWait();
	}

}

