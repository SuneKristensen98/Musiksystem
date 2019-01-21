package presentation.Editor;

import java.util.HashMap;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import logic.BravoMusic;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;
import logic.domainClasses.TableViewInfo;
import presentation.Factory;
import presentation.TimeConverter;
import presentation.PopUp.DeleteSongPopUp;

public class EditorSong {
	private Genre genre;
	private int artistId;
	private int conductorId;
	private Button btnEdit, btnDelete, btnAdd;
	private ComboBox<Genre> genreCoB;
	private int songId;
	private TextField tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote, tfConductor;
	private HashMap<String, Genre> map;

	public EditorSong() {
		makeHashMap();
	}

	public void start(BorderPane borderpane, BravoMusic bravoMusic, int albumId, EditorTable editorTable) {
		Factory factory = new Factory();
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
		tfArtist = factory.textFieldFactory("", 1000, 14);
		tfSongTitle = factory.textFieldFactory("", 1000, 14);
		tfTimeMin = factory.textFieldFactory("", 50, 14);
		onlyNumeric(tfTimeMin);
		tfTimeSec = factory.textFieldFactory("", 50, 14);
		onlyNumeric(tfTimeSec);
		tfSongWriter = factory.textFieldFactory("", 1000, 14);
		tfNote = factory.textFieldFactory("", 1000, 14);
		tfConductor = factory.textFieldFactory("", 1000, 14);

		// Buttons
		HBox songBoxBtn = factory.hBoxFactory(25, 50, 0, 0, 0, Pos.CENTER);
		btnDelete = factory.buttonFactory("Slet", 100, true);
		btnEdit = factory.buttonFactory("Gem", 100, true);
		btnAdd = factory.buttonFactory("Tilføj", 100, true);
		if (albumId != -1) {
			btnAdd.setDisable(false);
		}

		// Disable textfields
		TextField[] textFieldsForControlling = { tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote };
		controlTF(true, textFieldsForControlling);
		tfConductor.setDisable(true);

		// Genre combobox
		genreCoB = new ComboBox<Genre>();
		genreCoB.getItems().setAll(Genre.values());
		genreCoB.setPromptText("Genre");
		genreCoB.setPrefHeight(32);
		controlDisablingOfCB(true);
		genreCoB.setMaxWidth(1000);
		genreCoB.valueProperty().addListener(e -> controlTfWithCoB(textFieldsForControlling));

		genreCoB.setButtonCell(new ListCell<Genre>() {
			@Override
			protected void updateItem(Genre genre, boolean empty) {
				super.updateItem(genre, empty);
				if (empty || genre == null) {
					setText("Genre");
				} else {
					setText(genre.stringValue);
				}
			}
		});

		Label labelSongSaved = factory.labelFactory("Sangen er gemt", 80, 0, 0, 0, 16);
		labelSongSaved.setStyle("-fx-font-weight: BOLD");
		labelSongSaved.setVisible(false);
		labelSongSaved.setMinWidth(362);
		labelSongSaved.setAlignment(Pos.CENTER);

		// setOnActions
		btnAdd.setOnAction(e -> addAction(bravoMusic, btnAdd, albumId, editorTable, textFieldsForControlling));
		btnDelete.setOnAction(e -> deleteAction(bravoMusic, songId, albumId, editorTable));
		btnEdit.setOnAction(e -> editAction(bravoMusic, albumId, artistId, conductorId, editorTable, labelSongSaved));

		// Placement
		songBoxBtn.getChildren().addAll(btnAdd, btnDelete, btnEdit);
		btnBox.getChildren().addAll(songBoxBtn);
		timeBox.getChildren().addAll(labelTimeMin, tfTimeMin, labelTimeSec, tfTimeSec);
		songBox.getChildren().addAll(labelSong, labelGenre, genreCoB, labelArtist, tfArtist, labelSongTitle,
				tfSongTitle, labelTime, timeBox, labelSongWriter, tfSongWriter, labelNote, tfNote, labelConductor,
				tfConductor, labelSongSaved, btnBox);

		// Return
		borderpane.setRight(songBox);
	}

	private void addAction(BravoMusic bravoMusic, Button btnAdd, int albumId, EditorTable editorTable,
			TextField[] textFieldArray) {
		// TODO Det skal vel flyttes ned til hvis en sang skal oprettes?
		int conductorId;

		// Sørger for at conductorId bliver sat till NULL i databasen, hvis der ingen
		// conductor er
		if (tfConductor.getText().equals("")) {
			conductorId = 0;
		} else {
			if (bravoMusic.searchConductor(tfConductor.getText()) == -1) {
				Conductor conductor = new Conductor(-1, tfConductor.getText());
				conductorId = bravoMusic.createConductor(conductor);

			} else {
				conductorId = bravoMusic.searchConductor(tfConductor.getText());
			}

		}
		if (!tfArtist.getText().equals("") && !tfSongTitle.getText().equals("")) {
			if (bravoMusic.searchArtist(tfArtist.getText()) == -1) {
				Artist artist = new Artist(-1, tfArtist.getText());
				artistId = bravoMusic.createArtist(artist);

			} else {
				artistId = bravoMusic.searchArtist(tfArtist.getText());

			}
			
			
			if (tfArtist.getText().equals("")) {
				tfArtist.setPromptText("Skal udfyldes");
				tfArtist.setStyle("-fx-border-color: RED; -fx-opacity: ");
			} else {
				tfArtist.setStyle("-fx-opacity: 100.0;");
			}
			if (tfSongTitle.getText().equals("")) {
				tfSongTitle.setPromptText("Skal udfyldes");
				tfSongTitle.setStyle("-fx-border-color: RED; -fx-opacity: ");
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

			Song song = new Song(-1, albumId, artistId, conductorId, tfSongTitle.getText(), genre, time,
					tfSongWriter.getText(), tfNote.getText());
			bravoMusic.createSong(song);
			clearAndDisableTF();
			editorTable.updateTable(albumId);
		}
	}

	private void deleteAction(BravoMusic bravoMusic, int songId, int albumId, EditorTable table) {
		DeleteSongPopUp deleteSongPopUp = new DeleteSongPopUp();

		if (deleteSongPopUp.start(bravoMusic, songId, albumId, table)) {
			clearAndDisableTF();
		}
	}

	private void editAction(BravoMusic bravoMusic, int albumId, int artistId, int conductorId, EditorTable editorTable,
			Label labelSongSaved) {
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
			if (bravoMusic.searchArtist(tfArtist.getText()) == -1) {
				Artist artist = new Artist(-1, tfArtist.getText());
				artistId = bravoMusic.createArtist(artist);

			} else {
				artistId = bravoMusic.searchArtist(tfArtist.getText());
			}

			if (bravoMusic.searchConductor(tfConductor.getText()) == -1) {
				Conductor conductor = new Conductor(-1, tfConductor.getText());
				conductorId = bravoMusic.createConductor(conductor);

			} else {
				conductorId = bravoMusic.searchConductor(tfConductor.getText());
			}

			Song song = new Song(songId, albumId, artistId, conductorId, tfSongTitle.getText(), genreCoB.getValue(),
					time, tfSongWriter.getText(), tfNote.getText());
			bravoMusic.editSong(song);
			editorTable.updateTable(albumId);

			labelSongSaved.setVisible(true);
			PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
			visiblePause.setOnFinished(e -> labelSongSaved.setVisible(false));
			visiblePause.play();
		}
	}

	private void controlTfWithCoB(TextField[] textFieldsForControlling) {
		genre = genreCoB.getValue();
		controlTF(false, textFieldsForControlling);

		if (Genre.CLASSICAL == genreCoB.getValue()) {
			tfConductor.setDisable(false);
			tfConductor.setStyle("-fx-opacity: 100");
		} else {
			tfConductor.setDisable(true);
			tfConductor.setStyle("-fx-opacity: ");
		}
	}

	private void controlTF(boolean isDisabled, TextField[] textFields) {
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setDisable(isDisabled);
			if (!isDisabled) {
				textFields[i].setStyle("-fx-opacity: 100.0");
			} else {
				textFields[i].setStyle("-fx-opacity: ");
			}
		}
	}

	private void onlyNumeric(TextField textField) {
		// force the field to be numeric only (Sekunder)
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	private void makeHashMap() {
		map = new HashMap<String, Genre>();
		String[] stringGenre = { "Alternativ", "Blues", "Country", "Elektronisk", "Folkemusik", "Heavy metal", "HipHop",
				"Indie rock", "Jazz", "Klassisk", "Klassisk rock", "Rap", "RnB", "Rock", "Rock n' roll", "Pop", "Punk",
				"Soul", "Soundtracks", "Andet" };
		Genre[] genreGenre = { Genre.ALTERNATIVE, Genre.BLUES, Genre.COUNTRY, Genre.ELECTRONICA, Genre.FOLK,
				Genre.HEAVYMETAL, Genre.HIPHOP, Genre.INDIEROCK, Genre.JAZZ, Genre.CLASSICAL, Genre.CLASSICROCK,
				Genre.RAP, Genre.RNB, Genre.ROCK, Genre.ROCKANDROLL, Genre.POP, Genre.PUNK, Genre.SOUL,
				Genre.SOUNDTRACKS, Genre.OTHER };

		for (int i = 0; i < stringGenre.length; i++) {
			map.put(stringGenre[i], genreGenre[i]);
		}
	}

	public void controlDisablingOfCB(boolean isDisabled) {
		genreCoB.setDisable(isDisabled);
	}

	public void controlBtnAdd(boolean disable) {
		btnAdd.setDisable(disable);
		if (disable) {
			btnAdd.setStyle("-fx-opacity: ");
		} else {
			btnAdd.setStyle("-fx-opacity: 100.0");
		}
	}

	public void controlBtnDelete(boolean disable) {
		btnDelete.setDisable(disable);
		if (disable) {
			btnDelete.setStyle("-fx-opacity: ");
		} else {
			btnDelete.setStyle("-fx-opacity: 100.0");
		}
	}

	public void controlBtnEdit(boolean disable) {
		btnEdit.setDisable(disable);
		if (disable) {
			btnEdit.setStyle("-fx-opacity: ");
		} else {
			btnEdit.setStyle("-fx-opacity: 100.0");
		}
	}

	public void setTextFieldsFromTable(TableViewInfo selectedRow) {
		Genre genre = map.get(selectedRow.getGenre());
		genreCoB.setValue(genre);
		tfArtist.setText(selectedRow.getArtistName());
		tfSongTitle.setText(selectedRow.getSongName());
		tfTimeMin.setText(Integer.toString(selectedRow.getTime() / 60));
		tfTimeSec.setText(Integer.toString(selectedRow.getTime() % 60));
		tfSongWriter.setText(selectedRow.getSongwriter());
		tfNote.setText(selectedRow.getSongNote());
		tfConductor.setText(selectedRow.getConductorName());
		songId = selectedRow.getSongId();
	}

	public void clearAndDisableTF() {
		tfArtist.clear();
		tfSongTitle.clear();
		tfTimeMin.clear();
		tfTimeSec.clear();
		tfSongWriter.clear();
		tfNote.clear();
		tfConductor.clear();
		genreCoB.getSelectionModel().clearSelection();
		TextField[] textFieldsForControlling = { tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote };
		controlTF(true, textFieldsForControlling);
	}
}