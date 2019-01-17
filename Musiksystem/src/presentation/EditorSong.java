package presentation;

import java.util.HashMap;

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
import logic.domainClasses.Album;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;
import logic.domainClasses.TableViewInfo;

//Total makeover
public class EditorSong {
	private Genre genre;
	Factory factory = new Factory();
	private Button btnEdit = factory.buttonFactory("Gem", 100, true);
	private Button btnDelete = factory.buttonFactory("Slet", 100, true);;
	private boolean textFieldTest;
	private ComboBox<Genre> genreCoB = new ComboBox<Genre>();;
	

	// Textfield
	private TextField tfArtist;
	private TextField tfSongTitle;
	private TextField tfTimeMin;
	private TextField tfTimeSec;
	private TextField tfSongWriter;
	private TextField tfNote;
	private TextField tfConductor;

	private HashMap<String, Genre> map;

	public EditorSong() {
		map = new HashMap<String, Genre>();
		map.put("elektronisk", Genre.ELECTRONICA);
		map.put("næste type", Genre.JAZZ);

	}

	public void editorSong(BorderPane borderpane, BravoMusic bravoMusic, int albumId, EditorTable editorTable) {
		// Setup
		textFieldTest = false;
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
		tfArtist = factory.textFieldFactory("", 1000, 14);
		tfSongTitle = factory.textFieldFactory("", 1000, 14);
		tfTimeMin = factory.textFieldFactory("", 50, 14);
		tfTimeSec = factory.textFieldFactory("", 50, 14);
		tfSongWriter = factory.textFieldFactory("", 1000, 14);
		tfNote = factory.textFieldFactory("", 1000, 14);
		tfConductor = factory.textFieldFactory("", 1000, 14);

		// Buttons
		HBox songBoxBtn = factory.hBoxFactory(25, 50, 0, 0, 0, Pos.CENTER);

		Button btnAdd = factory.buttonFactory("Tilføj", 100, false);

		// Disable textfields
		TextField[] textFieldsForControlling = { tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote };
		controlTF(true, textFieldsForControlling);
		tfConductor.setDisable(true);

		// Genre combobox
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

		if (textFieldTest) {
			Genre genre = map.get(editorTable.selectedRow().getGenre());

			genreCoB.setValue(Genre.BLUES);
			tfArtist.setText(editorTable.selectedRow().getArtistName());
			tfSongTitle.setText(editorTable.selectedRow().getSongName());
			tfTimeMin.setText(Integer.toString(editorTable.selectedRow().getTime() / 60));
			tfTimeSec.setText(Integer.toString(editorTable.selectedRow().getTime() % 60));
			tfSongWriter.setText(editorTable.selectedRow().getSongwriter());
			tfNote.setText(editorTable.selectedRow().getSongNote());
			tfConductor.setText(editorTable.selectedRow().getConductorName());

		}

//			Song song = bravoMusic.searchSongWithId(songId);
//			Artist artist = bravoMusic.searchArtistWithId(artistId);
//			Conductor conductor = bravoMusic.searchConductorWithId(conductorId);
//			
//		//	genreCoB.setText(song.getSongName());
//			tfSongTitle.setText(song.getSongName());
//			tfArtist.setText(artist.getName());
//			tfTimeMin.setText(Integer.toString(song.getTime()));
//			tfTimeSec.setText(Integer.toString(song.getTime()));
//			tfSongWriter.setText(song.getSongwriter());
//			tfNote.setText(song.getSongNote());
//			tfConductor.setText(conductor.getConductorName());

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

		if (!tfArtist.getText().equals("") && !tfSongTitle.getText().equals("")) {
			// btnAdd.setDisable(true);
			Song song = new Song(-1, albumId, artistId, conductorId, tfSongTitle.getText(), genre, time,
					tfSongWriter.getText(), tfNote.getText());
			bravoMusic.createSong(song);
			tfArtist.clear();
			tfSongTitle.clear();
			tfTimeMin.clear();
			tfTimeSec.clear();
			tfSongWriter.clear();
			tfNote.clear();
			tfConductor.clear();
			tfArtist.setDisable(true);
			tfSongTitle.setDisable(true);
			tfTimeMin.setDisable(true);
			tfTimeSec.setDisable(true);
			tfSongWriter.setDisable(true);
			tfNote.setDisable(true);
			tfConductor.setDisable(true);
			editorTable.updateTable(albumId);
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

	private void updateAction(BravoMusic bravoMusic, EditorTable table) {
		TableViewInfo selectedRow = table.selectedRow();

	}

	public void setTextFields(TableViewInfo selectedRow) {

		Genre genre = map.get(selectedRow.getGenre());

		genreCoB.setValue(Genre.BLUES);
		tfArtist.setText(selectedRow.getArtistName());
		tfSongTitle.setText(selectedRow.getSongName());
		tfTimeMin.setText(Integer.toString(selectedRow.getTime() / 60));
		tfTimeSec.setText(Integer.toString(selectedRow.getTime() % 60));
		tfSongWriter.setText(selectedRow.getSongwriter());
		tfNote.setText(selectedRow.getSongNote());
		tfConductor.setText(selectedRow.getConductorName());

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