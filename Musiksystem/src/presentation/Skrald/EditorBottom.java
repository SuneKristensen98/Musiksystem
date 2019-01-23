package presentation.Skrald;

import domainClasses.Album;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.BravoMusic;

public class EditorBottom {
//	private Button btnAlbumCancel;
//	private Button btnAlbumDelete;
//	private Button btnAlbumCreate;
//	private Button btnAlbumSave;
//	Artist artist = new Artist(1, "Søren");
//	System.out.println(DBCalls.addArtist(artist));

//	Conductor conductor = new Conductor(1, "Sune");
//	System.out.println(DBCalls.addConductor(conductor));

//	Song song = new Song(5, 2, 2, 2, "Bravo", Genre.FOLK, 100, "Johan", "Dope");
//	System.out.println(SongDBCalls.addSong(song));

//	Song song = new Song(-1, 2, 2, 2, "Bravo", Genre.ROCK, 100, "Johan", "Dope");
//	System.out.println(SongDBCalls.deleteSong(song));
//	SongImpl songImpl = new SongImpl();
//	System.out.println(songImpl.createSong(song));

//	Song song = new Song(5, 2, 2, 2, "Bravo", Genre.FOLK, 100, "Johan", "Dope");
//	System.out.println(SongDBCalls.updateSong(song));

//	Album album = new Album(5, "Sune's Tune", "CD", 2019, "Dope");
//	System.out.println(AlbumDBCalls.addAlbum(album));

//	Album album = new Album(5, "Sune's Tune", 2019, "CD", "Dope");
//	System.out.println(AlbumDBCalls.deleteAlbum(album));

//	Album album = new Album(5, "Sune's Tune", 2019, "CD", "Dope");
//	System.out.println(AlbumDBCalls.updateAlbum(album));
//	List<TableViewInfo> getAllMusic = DBCalls.getAllMusic();
//		for (TableViewInfo music : getAllMusic) {
//			System.out.println(music);
//		}
//}	
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
	
//	Fra EditorAlbum
//	VBox albumVBox = new VBox();
//	albumVBox.setPadding(new Insets(25, 25, 25, 25));
//	albumVBox.setAlignment(Pos.CENTER);
//	HBox albumTop = new HBox(25);
//	albumTop.setPadding(new Insets(0, 0, 25, 0));
//	VBox albumBot = new VBox();
//	albumBot.setPadding(new Insets(0, 0, 0, 0));
//albumRight.setAlignment(Pos.CENTER);
//albumLeft.setAlignment(Pos.TOP_CENTER);
//	HBox choiceBox = new HBox(15);
//	choiceBox.setPadding(new Insets(35, 0, 0, 0));
//	choiceBox.setAlignment(Pos.CENTER);
//	VBox albumTitle = new VBox();		
//	albumTitle.setAlignment(Pos.CENTER);
//	Label labelAlbum = new Label("Album");
//	labelAlbum.setPadding(new Insets(0, 0, 5, 0));
//	labelAlbum.setFont(Font.font("Helvetica", 20));
//
//	Label labelDescription = new Label("Beskrivelse:");
//	labelDescription.setPadding(new Insets(24, 0, 5, 0));
//	labelDescription.setFont(Font.font("Helvetica", 16));
//
//	Label albumName = new Label("Album Navn:");
//	albumName.setFont(Font.font("Helvetica", 16));
//	albumName.setPadding(new Insets(25, 0, 5, 0));
//
//	Label albumYear = new Label("Udgivelsesår:");
//	albumYear.setFont(Font.font("Helvetica", 16));
//	albumYear.setPadding(new Insets(25, 0, 5, 0));
//	TextField tfAlbumName = new TextField();
//	tfAlbumName.setPrefWidth(362);
//	tfAlbumName.setFont(Font.font("Helvetica", 14));
//
//	TextField tfYearOfRelease = new TextField();
//	tfYearOfRelease.setPrefWidth(362);
//	tfYearOfRelease.setFont(Font.font("Helvetica", 14));
//	HBox toogleErrorMsgHBox = new HBox();
//	toogleErrorMsgHBox.setAlignment(Pos.CENTER);
	
//	Fra EditorSong
	//private Genre genre;
	//private ComboBox<Genre> genreCoB;
	//
	//public void editorSong(BorderPane borderpane, BravoMusic bravoMusic, int albumId, EditorTable editorTable) {
	//
//		// Setup
////		borderpane.getChildren().clear();
//		//borderpane.setRight(null);
//		VBox songBox = new VBox();
//		songBox.setPadding(new Insets(25, 25, 25, 25));
//		songBox.setAlignment(Pos.TOP_CENTER);
//		songBox.setPrefWidth(400);
	//
////		String cssLayout = "-fx-border-color: grey;\n" +
////	            "-fx-border-insets: 25;\n" +
////	            "-fx-border-width: 3;\n" +
////	            "-fx-border-style: fill;\n";
////		songBox.setStyle(cssLayout);
//		songBox.setBackground(Background.EMPTY);
//		String style = "-fx-background-color: rgba(100, 0, 255, 0.5);";
//		songBox.setStyle(style);
	//
//		HBox tidBox = new HBox(5);
//		tidBox.setAlignment(Pos.CENTER);
	//
//		VBox btnBox = new VBox(25);
//		btnBox.setAlignment(Pos.BOTTOM_CENTER);
//		btnBox.setPrefHeight(1000);
	//
//		// Label
//		Label labelSongTitle = new Label("Sang");
//		labelSongTitle.setFont(Font.font("Helvetica", 20));
	//
//		Label labelGenre = new Label("Genre:");
//		labelGenre.setFont(Font.font("Helvetica", 16));
//		labelGenre.setPadding(new Insets(30, 0, 5, 0));
	//
//		Label labelKunstner = new Label("Kunstner:");
//		labelKunstner.setPadding(new Insets(25, 0, 5, 0));
//		labelKunstner.setFont(Font.font("Helvetica", 16));
	//
//		Label labelSangTitle = new Label("Sangtitel:");
//		labelSangTitle.setPadding(new Insets(25, 0, 0, 0));
//		labelSangTitle.setFont(Font.font("Helvetica", 16));
	//
//		Label labelTid = new Label("Tid:");
//		labelTid.setPadding(new Insets(25, 0, 5, 0));
//		labelTid.setFont(Font.font("Helvetica", 16));
	//
//		Label labelTidMin = new Label("Minutter:");
//		labelTidMin.setFont(Font.font("Helvetica", 14));
	//
//		Label labelTidSec = new Label("Sekunder:");
//		labelTidSec.setPadding(new Insets(0, 0, 0, 35));
//		labelTidSec.setFont(Font.font("Helvetica", 14));
	//
//		Label labelSangSkriver = new Label("Sangskriver:");
//		labelSangSkriver.setPadding(new Insets(25, 0, 5, 0));
//		labelSangSkriver.setFont(Font.font("Helvetica", 16));
	//
//		Label labelNote = new Label("Note:");
//		labelNote.setPadding(new Insets(25, 0, 5, 0));
//		labelNote.setFont(Font.font("Helvetica", 16));
	//
//		Label labelDirigent = new Label("Dirigent:");
//		labelDirigent.setPadding(new Insets(25, 0, 5, 0));
//		labelDirigent.setFont(Font.font("Helvetica", 16));
	//
//		// Textfield
//		TextField tfKunstner = new TextField();
//		tfKunstner.setMaxWidth(1000);
//		tfKunstner.setFont(Font.font("Helvetica", 14));
	//
//		TextField tfSangTitle = new TextField();
//		tfSangTitle.setMaxWidth(1000);
//		tfSangTitle.setFont(Font.font("Helvetica", 14));
	//
//		TextField tfTidMin = new TextField();
//		tfTidMin.setMaxWidth(50);
//		tfTidMin.setFont(Font.font("Helvetica", 14));
	//
//		TextField tfTidSec = new TextField();
//		tfTidSec.setMaxWidth(50);
//		tfTidSec.setFont(Font.font("Helvetica", 14));
	//
//		TextField tfSangSkriver = new TextField();
//		tfSangSkriver.setMaxWidth(1000);
//		tfSangSkriver.setFont(Font.font("Helvetica", 14));
	//
//		TextField tfNote = new TextField();
//		tfNote.setMaxWidth(1000);
//		tfNote.setFont(Font.font("Helvetica", 14));
	//
//		TextField tfDirigent = new TextField();
//		tfDirigent.setMaxWidth(1000);
//		tfDirigent.setFont(Font.font("Helvetica", 14));
	//
//		// Buttons
//		HBox songBoxBtn = new HBox(25);
//		songBoxBtn.setPadding(new Insets(50, 0, 0, 0));
//		songBoxBtn.setAlignment(Pos.CENTER);
	//
//		Button btnAdd = new Button("Tilføj");
//		btnAdd.setPrefSize(100, 20);
//		Button btnDelete = new Button("Slet");
//		btnDelete.setPrefSize(100, 20);
//		Button btnEdit = new Button("Redigere");
//		btnEdit.setPrefSize(100, 20);
	//
//		// Disable textfields
//		tfKunstner.setDisable(true);
//		tfSangTitle.setDisable(true);
//		tfTidMin.setDisable(true);
//		tfTidSec.setDisable(true);
//		tfSangSkriver.setDisable(true);
//		tfNote.setDisable(true);
//		tfDirigent.setDisable(true);
//		System.out.println(genre);
	//
//		// Disable knapper
//		btnAdd.setDisable(false);
//		btnDelete.setDisable(true);
//		btnEdit.setDisable(true);
	//
//		// Genre combobox
//		genreCoB = new ComboBox<Genre>();
//		genreCoB.getSelectionModel().clearSelection();
//		genreCoB.getItems().setAll(Genre.values());
//		genreCoB.setPromptText("Genre");
//		genreCoB.setPrefHeight(32);
//		// controlCB(true);
//		genreCoB.setMaxWidth(1000);
//		genreCoB.valueProperty().addListener(e -> {
//			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
//			tfKunstner.setDisable(false);
//			tfSangTitle.setDisable(false);
//			tfTidMin.setDisable(false);
//			tfTidSec.setDisable(false);
//			tfSangSkriver.setDisable(false);
//			tfNote.setDisable(false);
	//
//			if (Genre.CLASSICAL == genreCoB.getSelectionModel().getSelectedItem()) {
//				tfDirigent.setDisable(false);
	//
//			} else {
//				tfDirigent.setDisable(true);
//			}
////					table.updateTable(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected());
//			System.out.println(genre);
//		});
	//
////		if (albumId == -2) {
////			genreCoB.setDisable(true);
////		}
	////
////		else {
////			genreCoB.setDisable(false);
////		}
	//
//		// setOnActions
//		btnAdd.setOnAction(e -> addAction(bravoMusic, genreCoB, tfKunstner, tfSangTitle, tfTidMin, tfTidSec,
//				tfSangSkriver, tfNote, tfDirigent, btnAdd, btnDelete, btnEdit, albumId, editorTable));
//		{
	//
//		}
//		btnDelete.setOnAction(e -> deleteAction(bravoMusic));
//		{
	//
//		}
	//
//		btnEdit.setOnAction(e -> editAction(bravoMusic));
//		{
	//
//		}
	//
////		ComboBox<Genre> genreCoB = new ComboBox<Genre>();
////		genreCoB.getItems().setAll(Genre.values());
////		genreCoB.setPromptText("Genre");
////		genreCoB.valueProperty().addListener(e -> {
////			// TODO fromString()
////			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
////			table.updateTable(
////					bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
////		});
	//
//		// genreCoB.valueProperty().addListener(e -> {
////			tfKunstner.setDisable(false);
////			tfSangTitle.setDisable(false);
////			tfTidMin.setDisable(false);
////			tfTidSec.setDisable(false);
////			tfSangSkriver.setDisable(false);
////			tfNote.setDisable(false);
	////
////			if (Genre.CLASSICAL == genreCoB.getSelectionModel().getSelectedItem()) {
////				tfDirigent.setDisable(false);
	////
////			}
	////
////			else {
////				tfDirigent.setDisable(true);
////			}
	//
//		// });
//		// ;
	//
//		// force the field to be numeric only (Minutter)
//		tfTidMin.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				if (!newValue.matches("\\d*")) {
//					tfTidMin.setText(newValue.replaceAll("[^\\d]", ""));
//				}
//			}
//		});
	//
//		// force the field to be numeric only (Sekunder)
//		tfTidSec.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				if (!newValue.matches("\\d*")) {
//					tfTidSec.setText(newValue.replaceAll("[^\\d]", ""));
//				}
//			}
//		});
	//
//		// Placement
//		songBox.getChildren().addAll(labelSongTitle, labelGenre, genreCoB, labelKunstner, tfKunstner, labelSangTitle,
//				tfSangTitle, labelTid, tidBox, labelSangSkriver, tfSangSkriver, labelNote, tfNote, labelDirigent,
//				tfDirigent, btnBox);
	//
//		btnBox.getChildren().addAll(songBoxBtn);
	//
//		tidBox.getChildren().addAll(labelTidMin, tfTidMin, labelTidSec, tfTidSec);
	//
//		songBoxBtn.getChildren().addAll(btnAdd, btnDelete, btnEdit);
	//
//		// Return
//		borderpane.setRight(songBox);
	
//	genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
//	controlTF(false, textFieldsForControlling);
//
//	if (Genre.CLASSICAL == genreCoB.getSelectionModel().getSelectedItem()) {
//		tfConductor.setDisable(false);
//		tfConductor.setStyle("-fx-opacity: 100");
//	} else {
//		tfConductor.setDisable(true);
//		tfConductor.setStyle("-fx-opacity: ");
//	}
	
//	if (textFieldFillData) {
//	Genre genre = map.get(editorTable.selectedRow().getGenre());
//
//	genreCoB.setValue(Genre.BLUES);
//	tfArtist.setText(editorTable.selectedRow().getArtistName());
//	tfSongTitle.setText(editorTable.selectedRow().getSongName());
//	tfTimeMin.setText(Integer.toString(editorTable.selectedRow().getTime() / 60));
//	tfTimeSec.setText(Integer.toString(editorTable.selectedRow().getTime() % 60));
//	tfSongWriter.setText(editorTable.selectedRow().getSongwriter());
//	tfNote.setText(editorTable.selectedRow().getSongNote());
//	tfConductor.setText(editorTable.selectedRow().getConductorName());
//
//}
	

	// force the field to be numeric only (Minutter)
//	tfTimeMin.textProperty().addListener(new ChangeListener<String>() {
//		@Override
//		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//			if (!newValue.matches("\\d*")) {
//				tfTimeMin.setText(newValue.replaceAll("[^\\d]", ""));
//			}
//		}
//	});

//	// force the field to be numeric only (Sekunder)
//	tfTimeSec.textProperty().addListener(new ChangeListener<String>() {
//		@Override
//		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//			if (!newValue.matches("\\d*")) {
//				tfTimeSec.setText(newValue.replaceAll("[^\\d]", ""));
//			}
//		}
//	});
	

	// force the field to be numeric only (Minutter)
//	tfTimeMin.textProperty().addListener(new ChangeListener<String>() {
//		@Override
//		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//			if (!newValue.matches("\\d*")) {
//				tfTimeMin.setText(newValue.replaceAll("[^\\d]", ""));
//			}
//		}
//	});

//	// force the field to be numeric only (Sekunder)
//	tfTimeSec.textProperty().addListener(new ChangeListener<String>() {
//		@Override
//		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//			if (!newValue.matches("\\d*")) {
//				tfTimeSec.setText(newValue.replaceAll("[^\\d]", ""));
//			}
//		}
//	});
}