package presentation.Editor;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.BravoMusic;
import logic.BravoMusicFactory;
import presentation.MainSide.Table;
import presentation.PopUp.BackPopUp;

public class Editor {

	Scene editorStage;
	EditorAlbum editorAlbum;
	Stage editor;
	Table table;
	
	public void start(BravoMusic bravoMusic, Table table1, int albumId) {
		table = table1;
		
		//Class Call
		editorAlbum = new EditorAlbum();

		
		//Editor Start Pane
		BorderPane borderpane = new BorderPane();
		editor = new Stage();
		editor.initModality(Modality.APPLICATION_MODAL);
		editor.setTitle("Editor");
		editor.setResizable(false);
		editorStage = new Scene(borderpane, 1200, 900);	
		
		//Sørger for at vindue ikke kan lukkes med kryds
		editor.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });

		borderpane.setCenter(editorAlbum.start(borderpane, editor, table, bravoMusic, albumId));
		
		//Scene Editor
		editor.setScene(editorStage);
		editor.show();
		editor.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
	}
	private void closeWindowEvent(WindowEvent event) {
		BravoMusic bravoMusic = new BravoMusicFactory().makeBravoMusic();
		editorAlbum.cancelAction(editor, table, bravoMusic);
	}
}

