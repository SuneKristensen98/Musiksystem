package presentation.MainSide;

import domainClasses.Genre;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.BravoMusic;
import presentation.Factory;

public class MainSideSearch {
	private Genre genre;
	private CheckBox lpChB, cdChB;
	private TextField searchField;
	private ComboBox<Genre> genreCoB;

	public HBox hBoxSearch(BravoMusic bravoMusic, Table table) {
		Factory factory = new Factory();

		HBox returningHBox = factory.hBoxFactory(10, 10, 10, 0, 10, Pos.BASELINE_LEFT);

		lpChB = factory.checkBoxFactory("LP", true);
		cdChB = factory.checkBoxFactory("CD", true);

		searchField = factory.textFieldFactory("Søg", 500, -1);
		searchField.textProperty().addListener(e -> {
			table.updateTable(
					bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected(), -2));
		});

		genreCoB = new ComboBox<Genre>();
		genreCoB.getItems().setAll(Genre.values());
		genreCoB.setPromptText("Genre");
		genreCoB.valueProperty().addListener(e -> {
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
		btnShowAllMusic.setOnAction(e -> showAllMusicAction());

		returningHBox.getChildren().addAll(searchField, genreCoB, lpChB, cdChB, btnShowAllMusic);
		return returningHBox;
	}

	public void showAllMusicAction() {
		searchField.setText("");
		genreCoB.getSelectionModel().clearSelection();
		lpChB.setSelected(true);
		cdChB.setSelected(true);
	}
}