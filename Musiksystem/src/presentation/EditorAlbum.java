package presentation;

import java.util.List;
import java.util.Optional;

import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.BravoMusic;
import logic.domainClasses.Album;
import logic.domainClasses.Song;
import logic.domainClasses.TableViewInfo;
//To klasser i en nu, total makeover
public class EditorAlbum {
	private Button btnAlbumCancel;
	private Button btnAlbumDelete;
	private Button btnAlbumCreate;
	private Button btnAlbumSave;
	private Button btnAlbumAddSong;
	private int albumId;
	
	public VBox editorAlbum(BorderPane borderpane, Stage editor, Table table, BravoMusic bravoMusic, int albumID, EditorSong editorSong) {
		Factory factory = new Factory();
		albumId = albumID;
		// Setup
		VBox albumVBox = factory.vBoxFactory(25, 25, 25, 25, Pos.CENTER);

		// Box Setup
		HBox albumTop = factory.hBoxFactory(25, 0, 0, 25, 0, Pos.BASELINE_LEFT);
		VBox albumTableBot = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		albumTableBot.setMinHeight(300);
		VBox albumRight = new VBox();
		VBox albumLeft = new VBox();
		HBox choiceBox = factory.hBoxFactory(15, 35, 0, 0, 0, Pos.CENTER);
		VBox albumTitle = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		HBox btnBox = factory.hBoxFactory(25, 25, 0, 0, 0, Pos.CENTER_LEFT);
		btnBox.setPrefHeight(75);
		
		//Buttons
		btnAlbumCancel = factory.buttonFactory("Tilbage", 100, false);
		if (albumId == -1) {			
			btnAlbumDelete = factory.buttonFactory("Slet", 100, true);
			btnAlbumCreate = factory.buttonFactory("Opret", 100, false);
			btnAlbumSave = factory.buttonFactory("Gem", 100, true);
			btnAlbumAddSong = factory.buttonFactory("Tilføj ny sang", 100, true);
		} else {
			btnAlbumDelete = factory.buttonFactory("Slet", 100, false);
			btnAlbumCreate = factory.buttonFactory("Opret", 100, true);
			btnAlbumSave = factory.buttonFactory("Gem", 100, false);
			btnAlbumAddSong = factory.buttonFactory("Tilføj ny sang", 100, false);
		}
		
		// Label
		Label labelAlbum = factory.labelFactory("Album", 0, 0, 5, 0, 20);
		Label labelDescription = factory.labelFactory("Beskrivelse", 24, 0, 5, 0, 16);
		Label albumName = factory.labelFactory("Albumtitel", 25, 0, 5, 0, 16);
		Label albumYear = factory.labelFactory("Udgivelsesår", 25, 0, 5, 0, 16);
		
		// TextField
		TextField tfAlbumName = factory.textFieldFactory("", 362, 14);
		TextField tfYearOfRelease = factory.textFieldFactory("", 362, 14);
		
		tfYearOfRelease.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfYearOfRelease.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		// TextArea
		TextArea taDescription = new TextArea();
		taDescription.setPrefHeight(1000);
		taDescription.setPrefWidth(362);
		taDescription.setFont(Font.font("Helvetica", 14));

		// Radio Buttons
		RadioButton radioButton1 = new RadioButton("LP");
		radioButton1.setUserData("LP");
		RadioButton radioButton2 = new RadioButton("CD");
		radioButton2.setUserData("CD");

		ToggleGroup radioGroup = new ToggleGroup();
		radioButton1.setToggleGroup(radioGroup);
		radioButton2.setToggleGroup(radioGroup);
		
		EditorTable editorTable = new EditorTable(albumTableBot, albumId, editorSong);
		editorSong.editorSong(borderpane, bravoMusic, albumId, editorTable);
		
		if (albumId != -1) {
			editorSong.controlCB(false);
			Album album = bravoMusic.searchAlbumWithId(albumId);
			tfAlbumName.setText(album.getAlbumName());
			tfYearOfRelease.setText(Integer.toString(album.getYearOfRelease()));
			taDescription.setText(album.getAlbumDescription());
			if (album.getType().equals("LP")) {
				radioButton1.setSelected(true);
			} else if (album.getType().equals("CD")) {
				radioButton2.setSelected(true);			
			}
		}

		Label toggleErrorMsg = factory.labelFactory("LP eller CD skal vælges", 5, 0, 0, 0, -1);
		toggleErrorMsg.setTextFill(Color.RED);
		toggleErrorMsg.setVisible(false);

		HBox toogleErrorMsgHBox = factory.hBoxFactory(0, 0, 0, 0, 0, Pos.CENTER);
		toogleErrorMsgHBox.getChildren().add(toggleErrorMsg);
		
		
		// Placement
		btnBox.getChildren().addAll(btnAlbumCancel, btnAlbumDelete, btnAlbumCreate, btnAlbumSave, btnAlbumAddSong);
		albumTitle.getChildren().addAll(labelAlbum);
		choiceBox.getChildren().addAll(radioButton1, radioButton2);
		albumRight.getChildren().addAll(labelDescription, taDescription);
		albumLeft.getChildren().addAll(albumName, tfAlbumName, albumYear, tfYearOfRelease, choiceBox, toogleErrorMsgHBox);
		albumTop.getChildren().addAll(albumLeft, albumRight);
		albumVBox.getChildren().addAll(albumTitle, albumTop, albumTableBot, btnBox);
		
		btnAlbumCancel.setOnAction(e -> cancelAction(editor, table, bravoMusic/*, albumId*/));
		btnAlbumDelete.setOnAction(e -> deleteAction(editor, table, bravoMusic, /*albumId, */tfAlbumName, tfYearOfRelease, taDescription, radioGroup));
		btnAlbumCreate.setOnAction(e -> createAction(editor, borderpane, bravoMusic, tfAlbumName, tfYearOfRelease, taDescription, radioGroup, toggleErrorMsg, editorSong, editorTable/*, albumId*/));
		btnAlbumSave.setOnAction(e -> saveAction(bravoMusic/*, albumId*/, tfAlbumName, tfYearOfRelease, taDescription, radioGroup, toggleErrorMsg, editorSong, editorTable));
		
		// Return
		return albumVBox;
	}
	
	private void closeWindowEvent(WindowEvent event) {
		BackPopUp backPopUp = new BackPopUp();
		//TODO mangler ligesom noget fyld
		//backPopUp.start(bravoMusic, editor, albumId);
	}
	
	private void cancelAction(Stage editor, Table table, BravoMusic bravoMusic/*, int albumId*/) {
		if (bravoMusic.searchMusic("", null, true, true, albumId).size() == 0 && albumId != -1) {
			BackPopUp backPopUp = new BackPopUp();
			backPopUp.start(bravoMusic, editor, albumId);
		} else {
			editor.hide();
			table.updateTable(bravoMusic.searchMusic("", null, true, true, -2));
		}
	}
	
	private void deleteAction(Stage editor, Table table, BravoMusic bravoMusic/*, int albumId*/, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup) {
		int yearOfRelease;
		if (tfYearOfRelease.getText().equals("")) {
			yearOfRelease = 0;
		} else {
			yearOfRelease = Integer.parseInt(tfYearOfRelease.getText());
		}
		
		List<TableViewInfo> songOnAlbum = bravoMusic.searchMusic("", null, true, true, albumId);
		for (int i = 0; i < songOnAlbum.size(); i++) {
			bravoMusic.deleteSong(songOnAlbum.get(i).getSongId());
		}
		
		bravoMusic.deleteAlbum(albumId);
		table.updateTable(bravoMusic.searchMusic("", null, true, true, -2));
		editor.hide();
	}
	
	private void createAction(Stage editor, BorderPane borderpane, BravoMusic bravoMusic, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup, Label toggleErrorMsg, EditorSong editorSong, EditorTable editorTable/*, int albumId*/) {
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
			int year;
			if (tfYearOfRelease.getText().equals("")) {
				year = 0;
			} else {
				year = Integer.parseInt(tfYearOfRelease.getText());
			}

			Album album = new Album(-1, tfAlbumName.getText(), radioGroup.getSelectedToggle().getUserData().toString(), year, taDescription.getText());
			albumId = bravoMusic.createAlbum(album);
			
			editorSong.editorSong(borderpane, bravoMusic, albumId, editorTable);
			editorSong.controlCB(false);
 
			btnAlbumCancel.setDisable(false);
			btnAlbumDelete.setDisable(false);
			btnAlbumCreate.setDisable(true);
			btnAlbumSave.setDisable(false);
			btnAlbumAddSong.setDisable(false);
			
			editor.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

		}
	}
	
	private void saveAction(BravoMusic bravoMusic/*, int albumId*/, TextField tfAlbumName, TextField tfYearOfRelease, TextArea taDescription, ToggleGroup radioGroup, Label toggleErrorMsg, EditorSong editorSong, EditorTable editorTable) {
		int year;

		if (tfAlbumName.getText().equals("")) {
			tfAlbumName.setPromptText("Skal udfyldes");
			tfAlbumName.setStyle("-fx-border-color: RED");
		} else {
			tfAlbumName.setStyle("-fx-border-color: ");
			
			if (tfYearOfRelease.getText().equals("")) {
				year = 0;
			} else {
				year = Integer.parseInt(tfYearOfRelease.getText());
			}

			Album album = new Album(albumId, tfAlbumName.getText(), radioGroup.getSelectedToggle().getUserData().toString(), year, taDescription.getText());
			bravoMusic.editAlbum(album);
		}		
	}
}