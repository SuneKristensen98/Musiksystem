package presentation;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.BravoMusic;
import logic.domainClasses.Album;

public class EditorBottom {
	private Button btnAlbumCancel;
	private Button btnAlbumDelete;
	private Button btnAlbumCreate;
	private Button btnAlbumSave;
	
	public HBox editorBottom(BravoMusic bravoMusic, EditorSong editorSong, Stage editor, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup, Label toggleErrorMsg) {
		Factory factory = new Factory();
		//Setup
		HBox btnBox = new HBox(25);
		btnBox.setPadding(new Insets(25, 25 ,25 ,25));
		btnBox.setAlignment(Pos.CENTER);
		btnBox.setPrefHeight(75);
		
		//Buttons
		btnBox.setPadding(new Insets(25, 0, 5, 0));
		btnBox.setAlignment(Pos.CENTER_LEFT);
		
		btnAlbumCancel = factory.buttonFactory("Tilbage", 100, false);
		btnAlbumDelete = factory.buttonFactory("Slet", 100, true);
		btnAlbumCreate = factory.buttonFactory("Opret", 100, false);
		btnAlbumSave = factory.buttonFactory("Gem", 100, true);

//				new Button("Tilbage");
//		btnAlbumCancel.setPrefSize(100, 20);
//		Button btnAlbumDelete = new Button("Slet");
//		btnAlbumDelete.setPrefSize(100, 20);
//		btnAlbumDelete.setDisable(true);
//		Button btnAlbumCreate = new Button("Opret");
//		btnAlbumCreate.setPrefSize(100, 20);
//		Button btnAlbumSave = new Button("Gem");
//		btnAlbumSave.setPrefSize(100, 20);
//		btnAlbumSave.setDisable(true);
//		
		//Placement
		btnBox.getChildren().addAll(btnAlbumCancel, btnAlbumDelete, btnAlbumCreate, btnAlbumSave);
		
		btnAlbumCancel.setOnAction(e -> cancelAction(editor));
		btnAlbumDelete.setOnAction(e -> deleteAction());
		btnAlbumCreate.setOnAction(e -> createAction(bravoMusic, tfAlbumName, tfYearOfRelease, taDescription, radioGroup, toggleErrorMsg, editorSong));
		//btnAlbumSave.setOnAction(e -> saveAction());

		//Return
		return btnBox;
	}
	
	private void cancelAction(Stage editor) {
		editor.hide();
	}
	
	private void deleteAction() {
		
	}
	
	private void createAction(BravoMusic bravoMusic, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup, Label toggleErrorMsg, EditorSong editorSong) {
		if (tfAlbumName.getText().equals("")) {
			tfAlbumName.setPromptText("Skal udfyldes");
			tfAlbumName.setStyle("-fx-border-color: RED");
		} else {
			tfAlbumName.setStyle("-fx-border-color: ");
		}
		
		boolean isToggleChosen;
		try {
			radioGroup.getSelectedToggle().getUserData();
			isToggleChosen = true;
			toggleErrorMsg.setVisible(false);
		} catch (NullPointerException e) {
			toggleErrorMsg.setVisible(true);
			isToggleChosen = false;
		}

		if (!tfAlbumName.getText().equals("") && isToggleChosen) {
			Album album = new Album(-1, tfAlbumName.getText(), radioGroup.getSelectedToggle().getUserData().toString(), Integer.parseInt(tfYearOfRelease.getText()), taDescription.getText());
			int albumId = bravoMusic.createAlbum(album);
			
			editorSong.controlCB(false);
			editorSong.editorSong(bravoMusic, albumId);

			btnAlbumCancel.setDisable(false);
			btnAlbumDelete.setDisable(false);
			btnAlbumCreate.setDisable(true);
			btnAlbumSave.setDisable(false);
		}
	}
	
}
