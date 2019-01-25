package presentation.Editor;

import domainClasses.Artist;
import domainClasses.Conductor;
import domainClasses.Genre;
import domainClasses.Song;
import domainClasses.TableViewInfo;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import presentation.Factory;
import presentation.TimeConverter;
import presentation.PopUp.DeleteSongPopUp;

public class EditorSong {
	private Genre genre;
	private int artistId, conductorId, songId;
	private Button btnEdit, btnDelete, btnAdd;
	private ComboBox<Genre> genreCoB;
	private TextField tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote, tfConductor;
	private CheckBox saveArtistChB;

	public void start(BorderPane borderpane, BravoMusic bravoMusic, int albumId, EditorTable editorTable) {
		Factory factory = new Factory();

		VBox songBox = factory.vBoxFactory(25, 25, 25, 25, Pos.TOP_CENTER);
		songBox.setPrefWidth(400);
		songBox.setBackground(Background.EMPTY);
		songBox.setStyle("-fx-background-color: rgba(100, 0, 255, 0.5);");
		HBox timeBox = factory.hBoxFactory(5, 0, 0, 0, 0, Pos.CENTER);
		HBox songBoxBtn = factory.hBoxFactory(25, 50, 0, 0, 0, Pos.CENTER);
		HBox boxForSaveArtist = factory.hBoxFactory(0, 0, 0, 0, 0, Pos.BASELINE_RIGHT);
		VBox btnBox = new VBox(25);
		btnBox.setAlignment(Pos.BOTTOM_CENTER);
		btnBox.setPrefHeight(1000);

		Label labelSong = factory.labelFactory("Sang", 0, 0, 0, 0, 20);
		Label labelGenre = factory.labelFactory("Genre:", 30, 0, 5, 0, 16);
		Label labelArtist = factory.labelFactory("Artist:", 25, 0, 5, 0, 16);
		Label labelSongTitle = factory.labelFactory("Sangtitel:", 25, 0, 0, 0, 16);
		Label labelTime = factory.labelFactory("Tid:", 25, 0, 5, 0, 16);
		Label labelTimeMin = factory.labelFactory("Minutter:", 0, 0, 0, 0, 14);
		Label labelTimeSec = factory.labelFactory("Sekunder:", 0, 0, 0, 35, 14);
		Label labelSongWriter = factory.labelFactory("Sangskriver:", 25, 0, 5, 0, 16);
		Label labelNote = factory.labelFactory("Note:", 25, 0, 5, 0, 16);
		Label labelConductor = factory.labelFactory("Dirigent:", 25, 0, 5, 0, 16);
		Label labelSongSaved = factory.labelFactory("Sangen er gemt", 80, 0, 0, 0, 16);
		labelSongSaved.setStyle("-fx-font-weight: BOLD");
		labelSongSaved.setVisible(false);
		labelSongSaved.setMinWidth(362);
		labelSongSaved.setAlignment(Pos.CENTER);

		saveArtistChB = factory.checkBoxFactory("Gem artist", false);
		saveArtistChB.setDisable(true);

		tfArtist = factory.textFieldFactory("", 1000, 14);
		tfSongTitle = factory.textFieldFactory("", 1000, 14);
		tfTimeMin = factory.textFieldFactory("", 50, 14);
		onlyNumeric(tfTimeMin);
		tfTimeSec = factory.textFieldFactory("", 50, 14);
		onlyNumeric(tfTimeSec);
		tfSongWriter = factory.textFieldFactory("", 1000, 14);
		tfNote = factory.textFieldFactory("", 1000, 14);
		tfConductor = factory.textFieldFactory("", 1000, 14);

		btnDelete = factory.buttonFactory("Slet", 100, true);
		btnEdit = factory.buttonFactory("Gem", 100, true);
		btnAdd = factory.buttonFactory("Tilføj", 100, true);
		if (albumId != -1) {
			btnAdd.setDisable(false);
		}

		TextField[] textFieldsForControlling = { tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote };
		tfConductor.setDisable(true);
		// TODO --> Factory
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
					controlTF(true, textFieldsForControlling);
				} else {
					setText(genre.stringValue);
					tfArtist.setStyle(null);
					controlTF(false, textFieldsForControlling);
				}
			}
		});

		btnAdd.setOnAction(e -> addAction(bravoMusic, albumId, editorTable, textFieldsForControlling));
		btnDelete.setOnAction(e -> deleteAction(bravoMusic, albumId, editorTable));
		btnEdit.setOnAction(e -> editAction(bravoMusic, albumId, editorTable, labelSongSaved));

		tfArtist.textProperty().addListener(e -> {
			saveArtistChB.setDisable(false);
		});
// TODO fix opret saveartist opacity
		saveArtistChB.selectedProperty().addListener(e -> {
			if (saveArtistChB.isSelected()) {
				tfArtist.setDisable(true);
				tfArtist.setStyle("fx-opacity: ");
			} else {
				tfArtist.setDisable(false);
				tfArtist.setStyle("fx-opacity: 100.0");
			}
		});

		songBoxBtn.getChildren().addAll(btnAdd, btnDelete, btnEdit);
		boxForSaveArtist.getChildren().add(saveArtistChB);
		btnBox.getChildren().addAll(songBoxBtn);
		timeBox.getChildren().addAll(labelTimeMin, tfTimeMin, labelTimeSec, tfTimeSec);
		songBox.getChildren().addAll(labelSong, labelGenre, genreCoB, labelArtist, tfArtist, boxForSaveArtist,
				labelSongTitle, tfSongTitle, labelTime, timeBox, labelSongWriter, tfSongWriter, labelNote, tfNote,
				labelConductor, tfConductor, labelSongSaved, btnBox);

		borderpane.setRight(songBox);
	}

	private void addAction(BravoMusic bravoMusic, int albumId, EditorTable editorTable, TextField[] textFieldArray) {
		int conductorId;

		if (tfArtist.getText().equals("")) {
			tfArtist.setPromptText("Skal udfyldes");
			tfArtist.setStyle("-fx-border-color: RED");
		} else {
			if (saveArtistChB.isSelected()) {
				tfArtist.setStyle("-fx-opacity: ");				
			} else {
				tfArtist.setStyle("-fx-opacity: 100.0");
			}
		}

		if (tfSongTitle.getText().equals("")) {
			tfSongTitle.setPromptText("Skal udfyldes");
			tfSongTitle.setStyle("-fx-border-color: RED");
		} else {
			tfSongTitle.setStyle("-fx-opacity: 100.0");
		}

		if (!tfArtist.getText().equals("") && !tfSongTitle.getText().equals("")) {
			if (tfConductor.getText().equals("")) {
				conductorId = 0;
			} else {
				if (bravoMusic.searchConductor(tfConductor.getText()) == 0) {
					Conductor conductor = new Conductor(-1, tfConductor.getText());
					conductorId = bravoMusic.createConductor(conductor);
				} else {
					conductorId = bravoMusic.searchConductor(tfConductor.getText());
				}
			}

			if (bravoMusic.searchArtist(tfArtist.getText()) == 0) {
				Artist artist = new Artist(-1, tfArtist.getText());
				artistId = bravoMusic.createArtist(artist);
			} else {
				artistId = bravoMusic.searchArtist(tfArtist.getText());
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

	private void deleteAction(BravoMusic bravoMusic, int albumId, EditorTable table) {
		if (new DeleteSongPopUp().start(bravoMusic, songId, albumId)) {
			clearAndDisableTF();
			table.updateTable(albumId);
		}
	}

	private void editAction(BravoMusic bravoMusic, int albumId, EditorTable editorTable, Label labelSongSaved) {
		int time;
		if (tfArtist.getText().equals("")) {
			tfArtist.setPromptText("Skal udfyldes");
			tfArtist.setStyle("-fx-border-color: RED");
		} else {
			if (saveArtistChB.isSelected()) {
				tfArtist.setStyle("-fx-opacity: ");				
			} else {
				tfArtist.setStyle("-fx-opacity: 100.0");
			}
		}

		if (tfSongTitle.getText().equals("")) {
			tfSongTitle.setPromptText("Skal udfyldes");
			tfSongTitle.setStyle("-fx-border-color: RED");
		} else {
			tfSongTitle.setStyle("-fx-opacity: 100.0");
		}
		
		if (!tfArtist.getText().equals("") && !tfSongTitle.getText().equals("")) {
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
		saveArtistChB.setDisable(false);
		saveArtistChB.setStyle("-fx-opacity: 100.0");

		if (Genre.CLASSICAL == genreCoB.getValue()) {
			tfConductor.setDisable(false);
			tfConductor.setStyle("-fx-opacity: 100.0");
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

			if (saveArtistChB.isSelected()) {
				textFields[0].setDisable(true);
				textFields[0].setStyle("-fx-opacity: ");
			}
		}
	}

// TODO --> Factory
	private void onlyNumeric(TextField textField) {
		// force the field to be numeric only (Sekunder)
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d]", ""));
				}

				// Sørger for at der kun kan skrives to cifre
				if (textField == tfTimeSec) {
					if (!newValue.matches("^\\d{0,2}")) {
						textField.setText(oldValue);
					}
				}
			}
		});
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

	public void setTFFromTable(TableViewInfo selectedRow) {
		genreCoB.setValue(selectedRow.getGenre());
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
		if (!saveArtistChB.isSelected()) {
			tfArtist.clear();
		}
		tfSongTitle.clear();
		tfTimeMin.clear();
		tfTimeSec.clear();
		tfSongWriter.clear();
		tfNote.clear();
		tfConductor.clear();
		genreCoB.getSelectionModel().clearSelection();
		TextField[] textFieldsForControlling = { tfArtist, tfSongTitle, tfTimeMin, tfTimeSec, tfSongWriter, tfNote };
		controlTF(true, textFieldsForControlling);
		saveArtistChB.setDisable(true);
		saveArtistChB.setStyle("-fx-opacity: ");
	}
}