package presentation.Editor;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.BravoMusic;
import logic.BravoMusicFactory;
import presentation.MainSide.Table;

public class Editor {

	private Scene editorStage;
	private EditorAlbum editorAlbum;
	private Stage editor;
	private Table table;
	
	public void start(BravoMusic bravoMusic, Table table1, int albumId) {
		table = table1;
		
		//Class Call
		editorAlbum = new EditorAlbum();

		
		//Editor Start Pane
		BorderPane borderpane = new BorderPane();
		editor = new Stage();
		editor.initModality(Modality.APPLICATION_MODAL);
		editor.setTitle("Album");
		editor.setResizable(false);
		editorStage = new Scene(borderpane, 1200, 900);	
		
		//Sørger for at vindue ikke kan lukkes med kryds
		editor.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
		
		editor.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					editorAlbum.cancelAction(editor, table, bravoMusic);
				}
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

