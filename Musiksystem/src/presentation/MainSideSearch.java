package presentation;

import java.util.Arrays;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.BravoMusic;
import logic.domainClasses.Genre;

public class MainSideSearch {
	private Genre genre;
	
	public HBox hBoxSearch(BravoMusic bravoMusic, Table table) {
		Factory factory = new Factory();
		
		HBox returningHBox = new HBox();
		returningHBox.setSpacing(10);
		returningHBox.setPadding(new Insets(10, 10, 0, 10));

		CheckBox lpChB = factory.chechBoxFactory("LP");
		CheckBox cdChB = factory.chechBoxFactory("CD");

		TextField searchField = factory.textFieldFactory("Søg", 500);
		searchField.textProperty().addListener(e -> {
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});
		
		ComboBox<Genre> genreCoB = new ComboBox<Genre>();
		genreCoB.getItems().setAll(Arrays.asList(Genre.values()));
		genreCoB.setPromptText("Genre");
		genreCoB.valueProperty().addListener(e -> {
			//TODO fromString()
			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});
		
		lpChB.selectedProperty().addListener(e -> {
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});

		cdChB.selectedProperty().addListener(e -> {
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});
		
		Button btnShowAllMusic = new Button("Vis alt musik");
		btnShowAllMusic.setOnAction(e -> showAllMusiAction(searchField, genreCoB, lpChB, cdChB));
		
		
		returningHBox.getChildren().addAll(searchField, genreCoB, lpChB, cdChB, btnShowAllMusic);
		return returningHBox;
	}
	
	private void showAllMusiAction(TextField searchField, ComboBox<Genre> genreCoB, CheckBox lpChB, CheckBox cdChB) {
		searchField.setText("");
		//TODO mangler prompt text?
		genreCoB.getSelectionModel().clearSelection();
		//genreCoB.setPromptText("Genre");
		lpChB.setSelected(true);
		cdChB.setSelected(true);
		
	}

}
