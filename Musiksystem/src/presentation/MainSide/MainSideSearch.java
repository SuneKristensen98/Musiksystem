package presentation.MainSide;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.BravoMusic;
import logic.domainClasses.Genre;
import presentation.Factory;

public class MainSideSearch {
	private Genre genre;

	public HBox hBoxSearch(BravoMusic bravoMusic, Table table) {
		Factory factory = new Factory();

		HBox returningHBox = factory.hBoxFactory(10, 10, 10, 0, 10, Pos.BASELINE_LEFT);

		CheckBox lpChB = factory.checkBoxFactory("LP");
		CheckBox cdChB = factory.checkBoxFactory("CD");

		TextField searchField = factory.textFieldFactory("Søg", 500, -1);
		searchField.textProperty().addListener(e -> {
			table.updateTable(
					bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
		});

		ComboBox<Genre> genreCoB = new ComboBox<Genre>();
		genreCoB.getItems().setAll(Genre.values());
		genreCoB.setPromptText("Genre");
		genreCoB.valueProperty().addListener(e -> {
			// TODO fromString()
			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
			table.updateTable(
					bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
		});

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

		lpChB.selectedProperty().addListener(e -> {
			table.updateTable(
					bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
		});

		cdChB.selectedProperty().addListener(e -> {
			table.updateTable(
					bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
		});

		Button btnShowAllMusic = factory.buttonFactory("Vis alt musik", 83, false);
		btnShowAllMusic.setOnAction(e -> showAllMusicAction(searchField, genreCoB, lpChB, cdChB));

		returningHBox.getChildren().addAll(searchField, genreCoB, lpChB, cdChB, btnShowAllMusic);
		return returningHBox;
	}

	private void showAllMusicAction(TextField searchField, ComboBox<Genre> genreCoB, CheckBox lpChB, CheckBox cdChB) {
		searchField.setText("");
		genreCoB.getSelectionModel().clearSelection();
		lpChB.setSelected(true);
		cdChB.setSelected(true);
	}
}
