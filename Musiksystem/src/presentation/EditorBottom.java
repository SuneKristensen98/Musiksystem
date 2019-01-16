package presentation;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.BravoMusic;
import logic.domainClasses.Album;

public class EditorBottom {
//	private Button btnAlbumCancel;
//	private Button btnAlbumDelete;
//	private Button btnAlbumCreate;
//	private Button btnAlbumSave;
	
//	public HBox editorBottom(BorderPane borderpane, BravoMusic bravoMusic, EditorSong editorSong, Stage editor, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup, Label toggleErrorMsg, EditorTable editorTable) {
//		Factory factory = new Factory();
//		
////		//Setup
////		HBox btnBox = factory.hBoxFactory(25, 25, 0, 5, 0, Pos.CENTER_LEFT);
////		btnBox.setPrefHeight(75);
////		
////		//Buttons	
////		btnAlbumCancel = factory.buttonFactory("Tilbage", 100, false);
////		btnAlbumDelete = factory.buttonFactory("Slet", 100, true);
////		btnAlbumCreate = factory.buttonFactory("Opret", 100, false);
////		btnAlbumSave = factory.buttonFactory("Gem", 100, true);
////
////		//Placement
////		btnBox.getChildren().addAll(btnAlbumCancel, btnAlbumDelete, btnAlbumCreate, btnAlbumSave);
////		
////		btnAlbumCancel.setOnAction(e -> cancelAction(editor));
////		btnAlbumDelete.setOnAction(e -> deleteAction());
////		btnAlbumCreate.setOnAction(e -> createAction(borderpane, bravoMusic, tfAlbumName, tfYearOfRelease, taDescription, radioGroup, toggleErrorMsg, editorSong, editorTable));
////		btnAlbumSave.setOnAction(e -> saveAction());
////
////		//Return
////		return btnBox;
//	}
//	
//	private void cancelAction(Stage editor) {
//		editor.hide();
//	}
//	
//	private void deleteAction() {
//	}
//	
//	private void createAction(BorderPane borderpane, BravoMusic bravoMusic, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup, Label toggleErrorMsg, EditorSong editorSong, EditorTable editorTable) {
//		if (tfAlbumName.getText().equals("")) {
//			tfAlbumName.setPromptText("Skal udfyldes");
//			tfAlbumName.setStyle("-fx-border-color: RED");
//		} else {
//			tfAlbumName.setStyle("-fx-border-color: ");
//		}
//		
//		boolean isToggleChosen;
//		try {
//			radioGroup.getSelectedToggle().getUserData();
//			isToggleChosen = true;
//			toggleErrorMsg.setVisible(false);
//		} catch (NullPointerException e) {
//			toggleErrorMsg.setVisible(true);
//			isToggleChosen = false;
//		}
//
//		if (!tfAlbumName.getText().equals("") && isToggleChosen) {
//			int year;
//			if (tfYearOfRelease.getText().equals("")) {
//				year = 0;
//			} else {
//				year = Integer.parseInt(tfYearOfRelease.getText());
//			}
//
//			Album album = new Album(-1, tfAlbumName.getText(), radioGroup.getSelectedToggle().getUserData().toString(), year, taDescription.getText());
//			int albumId = bravoMusic.createAlbum(album);
//			
//			//borderpane.setRight(null);
//			editorSong.editorSong(borderpane, bravoMusic, albumId, editorTable);
//			editorSong.controlCB(false);
// 
//			btnAlbumCancel.setDisable(false);
//			btnAlbumDelete.setDisable(false);
//			btnAlbumCreate.setDisable(true);
//			btnAlbumSave.setDisable(false);
//		}
//	}
//	
//	private void saveAction() {
//	}
}