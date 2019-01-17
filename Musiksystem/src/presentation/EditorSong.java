package presentation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.BravoMusic;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;

//Total makeover
public class EditorSong {
	private Genre genre;
	Factory factory = new Factory();
	private Button btnEdit = factory.buttonFactory("Gem", 100, true);
	private Button btnDelete = factory.buttonFactory("Slet", 100, true);;
	
	private ComboBox<Genre> genreCoB;

	public void editorSong(BorderPane borderpane, BravoMusic bravoMusic, int albumId, EditorTable editorTable) {
		// Setup
		VBox songBox = factory.vBoxFactory(25, 25, 25, 25, Pos.TOP_CENTER);
		songBox.setPrefWidth(400);
		songBox.setBackground(Background.EMPTY);
		songBox.setStyle("-fx-background-color: rgba(100, 0, 255, 0.5);");

		HBox timeBox = factory.hBoxFactory(5, 0, 0, 0, 0, Pos.CENTER);

		VBox btnBox = new VBox(25);
		btnBox.setAlignment(Pos.BOTTOM_CENTER);
		btnBox.setPrefHeight(1000);

		// Label
		Label labelSong = factory.labelFactory("Sang", 0, 0, 0, 0, 20);
		Label labelGenre = factory.labelFactory("Genre:", 30, 0, 5, 0, 16);
		Label labelArtist = factory.labelFactory("Kunstner:", 25, 0, 5, 0, 16);
		Label labelSongTitle = factory.labelFactory("Sangtitel:", 25, 0, 0, 0, 16);
		Label labelTime = factory.labelFactory("Tid:", 25, 0, 5, 0, 16);
		Label labelTimeMin = factory.labelFactory("Minutter:", 0, 0, 0, 0, 14);
		Label labelTimeSec = factory.labelFactory("Sekunder:", 0, 0, 0, 35, 14);
		Label labelSongWriter = factory.labelFactory("Sangskriver:", 25, 0, 5, 0, 16);
		Label labelNote = factory.labelFactory("Note:", 25, 0, 5, 0, 16);
		Label labelConductor = factory.labelFactory("Dirigent:", 25, 0, 5, 0, 16);

		// Textfield
		TextField tfArtist = factory.textFieldFactory("", 1000, 14);
		TextField tfSongTitle = factory.textFieldFactory("", 1000, 14);
		TextField tfTimeMin = factory.textFieldFactory("", 50, 14);
		TextField tfTimeSec = factory.textFieldFactory("", 50, 14);
		TextField tfSongWriter = factory.textFieldFactory("", 1000, 14);
		TextField tfNote = factory.textFieldFactory("", 1000, 14);
		TextField tfConductor = factory.textFieldFactory("", 1000, 14);

		// Buttons
		HBox songBoxBtn = factory.hBoxFactory(25, 50, 0, 0, 0, Pos.CENTER);

		Button btnAdd = factory.buttonFactory("Tilføj", 100, false);

		// Disable textfields
		TextField[] textFieldsForControlling = { tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote };
		controlTF(true, textFieldsForControlling);
		tfConductor.setDisable(true);

		// Genre combobox
		genreCoB = new ComboBox<Genre>();
		genreCoB.getSelectionModel().clearSelection();
		genreCoB.getItems().setAll(Genre.values());
		genreCoB.setPromptText("Genre");
		genreCoB.setPrefHeight(32);
		controlCB(true);
		genreCoB.setMaxWidth(1000);
		genreCoB.valueProperty().addListener(e -> {
			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
			controlTF(false, textFieldsForControlling);

			if (Genre.CLASSICAL == genreCoB.getSelectionModel().getSelectedItem()) {
				tfConductor.setDisable(false);
				tfConductor.setStyle("-fx-opacity: 100");
			} else {
				tfConductor.setDisable(true);
				tfConductor.setStyle("-fx-opacity: ");
			}
		});

		// setOnActions
		btnAdd.setOnAction(e -> addAction(bravoMusic, tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote,
				tfConductor, btnAdd, btnDelete, btnEdit, albumId, editorTable));

		btnDelete.setOnAction(e -> deleteAction(bravoMusic));

		btnEdit.setOnAction(e -> editAction(bravoMusic));

		// force the field to be numeric only (Minutter)
		tfTimeMin.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfTimeMin.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		// force the field to be numeric only (Sekunder)
		tfTimeSec.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfTimeSec.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		// Placement
		songBoxBtn.getChildren().addAll(btnAdd, btnDelete, btnEdit);

		btnBox.getChildren().addAll(songBoxBtn);

		timeBox.getChildren().addAll(labelTimeMin, tfTimeMin, labelTimeSec, tfTimeSec);

		songBox.getChildren().addAll(labelSong, labelGenre, genreCoB, labelArtist, tfArtist, labelSongTitle,
				tfSongTitle, labelTime, timeBox, labelSongWriter, tfSongWriter, labelNote, tfNote, labelConductor,
				tfConductor, btnBox);

		// Return
		borderpane.setRight(songBox);

	}

	private void addAction(BravoMusic bravoMusic, TextField tfArtist, TextField tfSongTitle, TextField tfTimeMin,
			TextField tfTimeSec, TextField tfSongWriter, TextField tfNote, TextField tfConductor, Button btnAdd,
			Button btnDelete, Button btnEdit, int albumId, EditorTable editorTable) {
		// System.out.println(genre);

		Conductor conductor = new Conductor(-1, tfConductor.getText());
		int conductorId = bravoMusic.createConductor(conductor);
		Artist artist = new Artist(-1, tfArtist.getText());
		int artistId = bravoMusic.createArtist(artist);

		if (tfArtist.getText().equals("")) {
			tfArtist.setPromptText("Skal udfyldes");
			tfArtist.setStyle("-fx-border-color: RED; -fx-opacity: 100.0");
		} else {
			tfArtist.setStyle("-fx-opacity: 100.0;");
		}
		if (tfSongTitle.getText().equals("")) {
			tfSongTitle.setPromptText("Skal udfyldes");
			tfSongTitle.setStyle("-fx-border-color: RED; -fx-opacity: 100.0");
		} else {
			tfSongTitle.setStyle("-fx-opacity: 100.0");
		}

		int time;
		if (tfTimeMin.getText().equals("")) {
			if (tfTimeSec.getText().equals("")) {
				time = new TimeConverter().displayToSeconds(0, 0);
			} else {
				time = new TimeConverter().displayToSeconds(0, Integer.parseInt(tfTimeSec.getText()));
			}
		} else {
			if (tfTimeSec.getText().equals("")) {
				time = new TimeConverter().displayToSeconds(Integer.parseInt(tfTimeMin.getText()), 0);
			} else {
				time = new TimeConverter().displayToSeconds(Integer.parseInt(tfTimeMin.getText()),
						Integer.parseInt(tfTimeSec.getText()));
			}
		}
//			
//			int time = new TimeConverter().displayToSeconds(Integer.parseInt(tfTidMin.getText()),
//					Integer.parseInt(tfTidSec.getText()));
		Song song = new Song(-1, albumId, artistId, conductorId, tfSongTitle.getText(), genre, time,
				tfSongWriter.getText(), tfNote.getText());
		bravoMusic.createSong(song);
		// System.out.println(bravoMusic.createSong(song));

		editorTable.updateTable(albumId);

		if (!tfArtist.getText().equals("") && !tfSongTitle.getText().equals("")) {
			// btnAdd.setDisable(true);
			tfArtist.clear();
			tfSongTitle.clear();
			tfTimeMin.clear();
			tfTimeSec.clear();
			tfSongWriter.clear();
			tfNote.clear();
			tfConductor.clear();
		}
	}

	private void deleteAction(BravoMusic bravoMusic) {
//		bravoMusic.deleteSong(song);
	}

	private void editAction(BravoMusic bravoMusic) {
//		bravoMusic.createSong(song);
	}

	public void controlCB(boolean isDisabled) {
		genreCoB.setDisable(isDisabled);
	}

	public void controlBtnDelete(boolean disable) {
		btnDelete.setDisable(disable);
		btnDelete.setStyle("-fx-opacity: 100.0");
	}

	public void controlBtnEdit(boolean disable) {
		btnEdit.setDisable(false);
		btnEdit.setStyle("-fx-opacity: 100.0");
	}

	private void controlTF(boolean isDisabled, TextField[] textFields) {
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setDisable(isDisabled);
			if (!isDisabled) {
				textFields[i].setStyle("-fx-opacity: 100.0");
			}
		}
	}
}

//private Genre genre;
//private ComboBox<Genre> genreCoB;
//
//public void editorSong(BorderPane borderpane, BravoMusic bravoMusic, int albumId, EditorTable editorTable) {
//
//	// Setup
////	borderpane.getChildren().clear();
//	//borderpane.setRight(null);
//	VBox songBox = new VBox();
//	songBox.setPadding(new Insets(25, 25, 25, 25));
//	songBox.setAlignment(Pos.TOP_CENTER);
//	songBox.setPrefWidth(400);
//
////	String cssLayout = "-fx-border-color: grey;\n" +
////            "-fx-border-insets: 25;\n" +
////            "-fx-border-width: 3;\n" +
////            "-fx-border-style: fill;\n";
////	songBox.setStyle(cssLayout);
//	songBox.setBackground(Background.EMPTY);
//	String style = "-fx-background-color: rgba(100, 0, 255, 0.5);";
//	songBox.setStyle(style);
//
//	HBox tidBox = new HBox(5);
//	tidBox.setAlignment(Pos.CENTER);
//
//	VBox btnBox = new VBox(25);
//	btnBox.setAlignment(Pos.BOTTOM_CENTER);
//	btnBox.setPrefHeight(1000);
//
//	// Label
//	Label labelSongTitle = new Label("Sang");
//	labelSongTitle.setFont(Font.font("Helvetica", 20));
//
//	Label labelGenre = new Label("Genre:");
//	labelGenre.setFont(Font.font("Helvetica", 16));
//	labelGenre.setPadding(new Insets(30, 0, 5, 0));
//
//	Label labelKunstner = new Label("Kunstner:");
//	labelKunstner.setPadding(new Insets(25, 0, 5, 0));
//	labelKunstner.setFont(Font.font("Helvetica", 16));
//
//	Label labelSangTitle = new Label("Sangtitel:");
//	labelSangTitle.setPadding(new Insets(25, 0, 0, 0));
//	labelSangTitle.setFont(Font.font("Helvetica", 16));
//
//	Label labelTid = new Label("Tid:");
//	labelTid.setPadding(new Insets(25, 0, 5, 0));
//	labelTid.setFont(Font.font("Helvetica", 16));
//
//	Label labelTidMin = new Label("Minutter:");
//	labelTidMin.setFont(Font.font("Helvetica", 14));
//
//	Label labelTidSec = new Label("Sekunder:");
//	labelTidSec.setPadding(new Insets(0, 0, 0, 35));
//	labelTidSec.setFont(Font.font("Helvetica", 14));
//
//	Label labelSangSkriver = new Label("Sangskriver:");
//	labelSangSkriver.setPadding(new Insets(25, 0, 5, 0));
//	labelSangSkriver.setFont(Font.font("Helvetica", 16));
//
//	Label labelNote = new Label("Note:");
//	labelNote.setPadding(new Insets(25, 0, 5, 0));
//	labelNote.setFont(Font.font("Helvetica", 16));
//
//	Label labelDirigent = new Label("Dirigent:");
//	labelDirigent.setPadding(new Insets(25, 0, 5, 0));
//	labelDirigent.setFont(Font.font("Helvetica", 16));
//
//	// Textfield
//	TextField tfKunstner = new TextField();
//	tfKunstner.setMaxWidth(1000);
//	tfKunstner.setFont(Font.font("Helvetica", 14));
//
//	TextField tfSangTitle = new TextField();
//	tfSangTitle.setMaxWidth(1000);
//	tfSangTitle.setFont(Font.font("Helvetica", 14));
//
//	TextField tfTidMin = new TextField();
//	tfTidMin.setMaxWidth(50);
//	tfTidMin.setFont(Font.font("Helvetica", 14));
//
//	TextField tfTidSec = new TextField();
//	tfTidSec.setMaxWidth(50);
//	tfTidSec.setFont(Font.font("Helvetica", 14));
//
//	TextField tfSangSkriver = new TextField();
//	tfSangSkriver.setMaxWidth(1000);
//	tfSangSkriver.setFont(Font.font("Helvetica", 14));
//
//	TextField tfNote = new TextField();
//	tfNote.setMaxWidth(1000);
//	tfNote.setFont(Font.font("Helvetica", 14));
//
//	TextField tfDirigent = new TextField();
//	tfDirigent.setMaxWidth(1000);
//	tfDirigent.setFont(Font.font("Helvetica", 14));
//
//	// Buttons
//	HBox songBoxBtn = new HBox(25);
//	songBoxBtn.setPadding(new Insets(50, 0, 0, 0));
//	songBoxBtn.setAlignment(Pos.CENTER);
//
//	Button btnAdd = new Button("Tilføj");
//	btnAdd.setPrefSize(100, 20);
//	Button btnDelete = new Button("Slet");
//	btnDelete.setPrefSize(100, 20);
//	Button btnEdit = new Button("Redigere");
//	btnEdit.setPrefSize(100, 20);
//
//	// Disable textfields
//	tfKunstner.setDisable(true);
//	tfSangTitle.setDisable(true);
//	tfTidMin.setDisable(true);
//	tfTidSec.setDisable(true);
//	tfSangSkriver.setDisable(true);
//	tfNote.setDisable(true);
//	tfDirigent.setDisable(true);
//	System.out.println(genre);
//
//	// Disable knapper
//	btnAdd.setDisable(false);
//	btnDelete.setDisable(true);
//	btnEdit.setDisable(true);
//
//	// Genre combobox
//	genreCoB = new ComboBox<Genre>();
//	genreCoB.getSelectionModel().clearSelection();
//	genreCoB.getItems().setAll(Genre.values());
//	genreCoB.setPromptText("Genre");
//	genreCoB.setPrefHeight(32);
//	// controlCB(true);
//	genreCoB.setMaxWidth(1000);
//	genreCoB.valueProperty().addListener(e -> {
//		genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
//		tfKunstner.setDisable(false);
//		tfSangTitle.setDisable(false);
//		tfTidMin.setDisable(false);
//		tfTidSec.setDisable(false);
//		tfSangSkriver.setDisable(false);
//		tfNote.setDisable(false);
//
//		if (Genre.CLASSICAL == genreCoB.getSelectionModel().getSelectedItem()) {
//			tfDirigent.setDisable(false);
//
//		} else {
//			tfDirigent.setDisable(true);
//		}
////				table.updateTable(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected());
//		System.out.println(genre);
//	});
//
////	if (albumId == -2) {
////		genreCoB.setDisable(true);
////	}
////
////	else {
////		genreCoB.setDisable(false);
////	}
//
//	// setOnActions
//	btnAdd.setOnAction(e -> addAction(bravoMusic, genreCoB, tfKunstner, tfSangTitle, tfTidMin, tfTidSec,
//			tfSangSkriver, tfNote, tfDirigent, btnAdd, btnDelete, btnEdit, albumId, editorTable));
//	{
//
//	}
//	btnDelete.setOnAction(e -> deleteAction(bravoMusic));
//	{
//
//	}
//
//	btnEdit.setOnAction(e -> editAction(bravoMusic));
//	{
//
//	}
//
////	ComboBox<Genre> genreCoB = new ComboBox<Genre>();
////	genreCoB.getItems().setAll(Genre.values());
////	genreCoB.setPromptText("Genre");
////	genreCoB.valueProperty().addListener(e -> {
////		// TODO fromString()
////		genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
////		table.updateTable(
////				bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
////	});
//
//	// genreCoB.valueProperty().addListener(e -> {
////		tfKunstner.setDisable(false);
////		tfSangTitle.setDisable(false);
////		tfTidMin.setDisable(false);
////		tfTidSec.setDisable(false);
////		tfSangSkriver.setDisable(false);
////		tfNote.setDisable(false);
////
////		if (Genre.CLASSICAL == genreCoB.getSelectionModel().getSelectedItem()) {
////			tfDirigent.setDisable(false);
////
////		}
////
////		else {
////			tfDirigent.setDisable(true);
////		}
//
//	// });
//	// ;
//
//	// force the field to be numeric only (Minutter)
//	tfTidMin.textProperty().addListener(new ChangeListener<String>() {
//		@Override
//		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//			if (!newValue.matches("\\d*")) {
//				tfTidMin.setText(newValue.replaceAll("[^\\d]", ""));
//			}
//		}
//	});
//
//	// force the field to be numeric only (Sekunder)
//	tfTidSec.textProperty().addListener(new ChangeListener<String>() {
//		@Override
//		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//			if (!newValue.matches("\\d*")) {
//				tfTidSec.setText(newValue.replaceAll("[^\\d]", ""));
//			}
//		}
//	});
//
//	// Placement
//	songBox.getChildren().addAll(labelSongTitle, labelGenre, genreCoB, labelKunstner, tfKunstner, labelSangTitle,
//			tfSangTitle, labelTid, tidBox, labelSangSkriver, tfSangSkriver, labelNote, tfNote, labelDirigent,
//			tfDirigent, btnBox);
//
//	btnBox.getChildren().addAll(songBoxBtn);
//
//	tidBox.getChildren().addAll(labelTidMin, tfTidMin, labelTidSec, tfTidSec);
//
//	songBoxBtn.getChildren().addAll(btnAdd, btnDelete, btnEdit);
//
//	// Return
//	borderpane.setRight(songBox);