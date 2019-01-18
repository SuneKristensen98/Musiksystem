package presentation.Editor;

import java.util.HashMap;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.BravoMusic;
import logic.domainClasses.*;
import presentation.Factory;
import presentation.TimeConverter;

public class EditorSong {
	private Genre genre;
	private Button btnEdit, btnDelete;
	private ComboBox<Genre> genreCoB;
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
		Button btnAdd = factory.buttonFactory("Tilføj", 100, true);
		if (albumId != -1) {
			btnAdd.setDisable(false);
		}

		// Disable textfields
		TextField[] textFieldsForControlling = {tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote};
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

		// setOnActions
		btnAdd.setOnAction(e -> addAction(bravoMusic, btnAdd, albumId, editorTable, textFieldsForControlling));
		btnDelete.setOnAction(e -> deleteAction(bravoMusic));
		btnEdit.setOnAction(e -> editAction(bravoMusic));

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

	private void addAction(BravoMusic bravoMusic,Button btnAdd, int albumId, EditorTable editorTable, TextField[] textFieldArray) {
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
			Song song = new Song(-1, albumId, artistId, conductorId, tfSongTitle.getText(), genre, time,
					tfSongWriter.getText(), tfNote.getText());
			bravoMusic.createSong(song);
			clearAndDisableTF();
			editorTable.updateTable(albumId);
		}
	}

	private void deleteAction(BravoMusic bravoMusic) {
//		bravoMusic.deleteSong(song);
	}

	private void editAction(BravoMusic bravoMusic) {
//		bravoMusic.createSong(song);
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
		String[] stringGenre = {"Alternativ", "Blues"};
		Genre[] genreGenre = {Genre.ALTERNATIVE, Genre.BLUES};
		
		for (int i = 0; i < stringGenre.length; i++) {
			map.put(stringGenre[i], genreGenre[i]);
		}
	}
	
	public void controlDisablingOfCB(boolean isDisabled) {
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
	
	public void setTextFieldsFromTable(TableViewInfo selectedRow) {
		Genre genre = map.get(selectedRow.getGenre());
		//TODO Brug rigtig genre
		genreCoB.setValue(Genre.BLUES);
		tfArtist.setText(selectedRow.getArtistName());
		tfSongTitle.setText(selectedRow.getSongName());
		tfTimeMin.setText(Integer.toString(selectedRow.getTime() / 60));
		tfTimeSec.setText(Integer.toString(selectedRow.getTime() % 60));
		tfSongWriter.setText(selectedRow.getSongwriter());
		tfNote.setText(selectedRow.getSongNote());
		tfConductor.setText(selectedRow.getConductorName());
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
		TextField[] textFieldsForControlling = {tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote};
		controlTF(true, textFieldsForControlling);
	}
}