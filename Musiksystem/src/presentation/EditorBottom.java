package presentation;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.BravoMusic;

public class EditorBottom {

	public HBox editorBottom(BravoMusic bravoMusic, Stage editor, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup) {
		
		//Setup
		HBox btnBox = new HBox(25);
		btnBox.setPadding(new Insets(25, 25 ,25 ,25));
		btnBox.setAlignment(Pos.CENTER);
		btnBox.setPrefHeight(75);
		
		//Buttons
		btnBox.setPadding(new Insets(25, 0, 5, 0));
		btnBox.setAlignment(Pos.CENTER_LEFT);
		
		Button btnAlbumCancel = new Button("Fortryd");
		btnAlbumCancel.setPrefSize(100, 20);
		Button btnAlbumDelete = new Button("Slet");
		btnAlbumDelete.setPrefSize(100, 20);
		Button btnAlbumSave = new Button("Gem");
		btnAlbumSave.setPrefSize(100, 20);
		
		//Placement
		btnBox.getChildren().addAll(btnAlbumCancel, btnAlbumDelete, btnAlbumSave);
		
		btnAlbumCancel.setOnAction(e -> cancelAction(editor));
		btnAlbumDelete.setOnAction(e -> deleteAction());
		btnAlbumSave.setOnAction(e -> saveAction(bravoMusic));

		//Return
		return btnBox;
	}
	
	private void cancelAction(Stage editor) {
		editor.hide();
	}
	
	private void deleteAction() {

	}
	
	private void saveAction(BravoMusic bravoMusic) {
		bravoMusic.createAlbum(album);
	}
}
