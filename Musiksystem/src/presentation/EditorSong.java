package presentation;

import java.util.Arrays;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.BravoMusic;
import logic.domainClasses.Album;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;

public class EditorSong {
	private Genre genre;
	private ComboBox<Genre> genreCoB;

	public void editorSong(BorderPane borderpane, BravoMusic bravoMusic, int albumId, EditorTable editorTable) {

		// Setup
	//	borderpane.getChildren().clear();
		//borderpane.setRight(null);
		VBox songBox = new VBox();
		songBox.setPadding(new Insets(25, 25, 25, 25));
		songBox.setAlignment(Pos.TOP_CENTER);
		songBox.setPrefWidth(400);

//		String cssLayout = "-fx-border-color: grey;\n" +
//                "-fx-border-insets: 25;\n" +
//                "-fx-border-width: 3;\n" +
//                "-fx-border-style: fill;\n";
//		songBox.setStyle(cssLayout);
		songBox.setBackground(Background.EMPTY);
		String style = "-fx-background-color: rgba(100, 0, 255, 0.5);";
		songBox.setStyle(style);

		HBox tidBox = new HBox(5);
		tidBox.setAlignment(Pos.CENTER);

		VBox btnBox = new VBox(25);
		btnBox.setAlignment(Pos.BOTTOM_CENTER);
		btnBox.setPrefHeight(1000);

		// Label
		Label labelSongTitle = new Label("Sang");
		labelSongTitle.setFont(Font.font("Helvetica", 20));

		Label labelGenre = new Label("Genre:");
		labelGenre.setFont(Font.font("Helvetica", 16));
		labelGenre.setPadding(new Insets(30, 0, 5, 0));

		Label labelKunstner = new Label("Kunstner:");
		labelKunstner.setPadding(new Insets(25, 0, 5, 0));
		labelKunstner.setFont(Font.font("Helvetica", 16));

		Label labelSangTitle = new Label("Sangtitel:");
		labelSangTitle.setPadding(new Insets(25, 0, 0, 0));
		labelSangTitle.setFont(Font.font("Helvetica", 16));

		Label labelTid = new Label("Tid:");
		labelTid.setPadding(new Insets(25, 0, 5, 0));
		labelTid.setFont(Font.font("Helvetica", 16));

		Label labelTidMin = new Label("Minutter:");
		labelTidMin.setFont(Font.font("Helvetica", 14));

		Label labelTidSec = new Label("Sekunder:");
		labelTidSec.setPadding(new Insets(0, 0, 0, 35));
		labelTidSec.setFont(Font.font("Helvetica", 14));

		Label labelSangSkriver = new Label("Sangskriver:");
		labelSangSkriver.setPadding(new Insets(25, 0, 5, 0));
		labelSangSkriver.setFont(Font.font("Helvetica", 16));

		Label labelNote = new Label("Note:");
		labelNote.setPadding(new Insets(25, 0, 5, 0));
		labelNote.setFont(Font.font("Helvetica", 16));

		Label labelDirigent = new Label("Dirigent:");
		labelDirigent.setPadding(new Insets(25, 0, 5, 0));
		labelDirigent.setFont(Font.font("Helvetica", 16));

		// Textfield
		TextField tfKunstner = new TextField();
		tfKunstner.setMaxWidth(1000);
		tfKunstner.setFont(Font.font("Helvetica", 14));

		TextField tfSangTitle = new TextField();
		tfSangTitle.setMaxWidth(1000);
		tfSangTitle.setFont(Font.font("Helvetica", 14));

		TextField tfTidMin = new TextField();
		tfTidMin.setMaxWidth(50);
		tfTidMin.setFont(Font.font("Helvetica", 14));

		TextField tfTidSec = new TextField();
		tfTidSec.setMaxWidth(50);
		tfTidSec.setFont(Font.font("Helvetica", 14));

		TextField tfSangSkriver = new TextField();
		tfSangSkriver.setMaxWidth(1000);
		tfSangSkriver.setFont(Font.font("Helvetica", 14));

		TextField tfNote = new TextField();
		tfNote.setMaxWidth(1000);
		tfNote.setFont(Font.font("Helvetica", 14));

		TextField tfDirigent = new TextField();
		tfDirigent.setMaxWidth(1000);
		tfDirigent.setFont(Font.font("Helvetica", 14));

		// Buttons
		HBox songBoxBtn = new HBox(25);
		songBoxBtn.setPadding(new Insets(50, 0, 0, 0));
		songBoxBtn.setAlignment(Pos.CENTER);

		Button btnAdd = new Button("Tilføj");
		btnAdd.setPrefSize(100, 20);
		Button btnDelete = new Button("Slet");
		btnDelete.setPrefSize(100, 20);
		Button btnEdit = new Button("Redigere");
		btnEdit.setPrefSize(100, 20);

		// Disable textfields
		tfKunstner.setDisable(true);
		tfSangTitle.setDisable(true);
		tfTidMin.setDisable(true);
		tfTidSec.setDisable(true);
		tfSangSkriver.setDisable(true);
		tfNote.setDisable(true);
		tfDirigent.setDisable(true);
		System.out.println(genre);

		// Disable knapper
		btnAdd.setDisable(false);
		btnDelete.setDisable(true);
		btnEdit.setDisable(true);

		// Genre combobox
		genreCoB = new ComboBox<Genre>();
		genreCoB.getSelectionModel().clearSelection();
		genreCoB.getItems().setAll(Genre.values());
		genreCoB.setPromptText("Genre");
		genreCoB.setPrefHeight(32);
		// controlCB(true);
		genreCoB.setMaxWidth(1000);
		genreCoB.valueProperty().addListener(e -> {
			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
			tfKunstner.setDisable(false);
			tfSangTitle.setDisable(false);
			tfTidMin.setDisable(false);
			tfTidSec.setDisable(false);
			tfSangSkriver.setDisable(false);
			tfNote.setDisable(false);

			if (Genre.CLASSICAL == genreCoB.getSelectionModel().getSelectedItem()) {
				tfDirigent.setDisable(false);

			} else {
				tfDirigent.setDisable(true);
			}
//					table.updateTable(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected());
			System.out.println(genre);
		});

//		if (albumId == -2) {
//			genreCoB.setDisable(true);
//		}
//
//		else {
//			genreCoB.setDisable(false);
//		}

		// setOnActions
		btnAdd.setOnAction(e -> addAction(bravoMusic, genreCoB, tfKunstner, tfSangTitle, tfTidMin, tfTidSec,
				tfSangSkriver, tfNote, tfDirigent, btnAdd, btnDelete, btnEdit, albumId, editorTable));
		{

		}
		btnDelete.setOnAction(e -> deleteAction(bravoMusic));
		{

		}

		btnEdit.setOnAction(e -> editAction(bravoMusic));
		{

		}

//		ComboBox<Genre> genreCoB = new ComboBox<Genre>();
//		genreCoB.getItems().setAll(Genre.values());
//		genreCoB.setPromptText("Genre");
//		genreCoB.valueProperty().addListener(e -> {
//			// TODO fromString()
//			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
//			table.updateTable(
//					bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
//		});

		// genreCoB.valueProperty().addListener(e -> {
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
//			}
//
//			else {
//				tfDirigent.setDisable(true);
//			}

		// });
		// ;

		// force the field to be numeric only (Minutter)
		tfTidMin.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfTidMin.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		// force the field to be numeric only (Sekunder)
		tfTidSec.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfTidSec.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		// Placement
		songBox.getChildren().addAll(labelSongTitle, labelGenre, genreCoB, labelKunstner, tfKunstner, labelSangTitle,
				tfSangTitle, labelTid, tidBox, labelSangSkriver, tfSangSkriver, labelNote, tfNote, labelDirigent,
				tfDirigent, btnBox);

		btnBox.getChildren().addAll(songBoxBtn);

		tidBox.getChildren().addAll(labelTidMin, tfTidMin, labelTidSec, tfTidSec);

		songBoxBtn.getChildren().addAll(btnAdd, btnDelete, btnEdit);

		// Return
		borderpane.setRight(songBox);

	}

	private void addAction(BravoMusic bravoMusic, ComboBox<Genre> genreCoB, TextField tfKunstner, TextField tfSangTitle,
			TextField tfTidMin, TextField tfTidSec, TextField tfSangSkriver, TextField tfNote, TextField tfDirigent,
			Button btnAdd, Button btnDelete, Button btnEdit, int albumId, EditorTable editorTable) {
		System.out.println(genre);

		System.out.println();
		Conductor conductor = new Conductor(-1, tfDirigent.getText());
		Artist artist = new Artist(-1, tfKunstner.getText());
		int artistId = bravoMusic.createArtist(artist);
		int conductorId = bravoMusic.createConductor(conductor);

		if (tfKunstner.getText().equals("")) {
			tfKunstner.setPromptText("Skal udfyldes");
			tfKunstner.setStyle("-fx-border-color: RED");
		} else {
			tfKunstner.setStyle("-fx-border-color: ");
		}
		if (tfSangTitle.getText().equals("")) {
			tfSangTitle.setPromptText("Skal udfyldes");
			tfSangTitle.setStyle("-fx-border-color: RED");
		} else {
			tfSangTitle.setStyle("-fx-border-color: ");
		}

		if (!tfKunstner.getText().equals("") && !tfSangTitle.getText().equals("")) {
			btnAdd.setDisable(true);
			btnDelete.setDisable(false);
			btnEdit.setDisable(false);
			int time = new TimeConverter().displayToSeconds(Integer.parseInt(tfTidMin.getText()),
					Integer.parseInt(tfTidSec.getText()));
			Song song = new Song(-1, albumId, artistId, conductorId, tfSangTitle.getText(), genre, time,
					tfSangSkriver.getText(), tfNote.getText());
			System.out.println(bravoMusic.createSong(song));
			
			editorTable.updateTable(albumId);

//		 bravoMusic.createSong(song);

		}
	}

	private void deleteAction(BravoMusic bravoMusic) {
//		bravoMusic.deleteSong(song);
	}

	private void editAction(BravoMusic bravoMusic) {
//		bravoMusic.createSong(song);
	}

//	public void controlCB(boolean isDisabled) {
//		genreCoB.setDisable(isDisabled);
//	}

}
