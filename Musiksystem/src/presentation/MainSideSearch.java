package presentation;

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

		CheckBox lpChB = factory.checkBoxFactory("LP");
		CheckBox cdChB = factory.checkBoxFactory("CD");

		TextField searchField = factory.textFieldFactory("Søg", 500, -1);
		searchField.textProperty().addListener(e -> {
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});
		
		ComboBox<Genre> genreCoB = new ComboBox<Genre>();
		genreCoB.getItems().setAll(Genre.values());
		genreCoB.setPromptText("Genre");
		genreCoB.valueProperty().addListener(e -> {
			//TODO fromString()
			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});
		
	    genreCoB.setButtonCell(new ListCell<Genre>() {
	        @Override
	        protected void updateItem(Genre genre, boolean empty) {
	            super.updateItem(genre, empty) ;
	            if (empty || genre == null) {
	                setText("Genre");
	            } else {
	            	setText(genre.stringValue);
	            }
	        }
	    });
		
		lpChB.selectedProperty().addListener(e -> {
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});

		cdChB.selectedProperty().addListener(e -> {
			table.updateTable(bravoMusic.searchMusic(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected()));
		});
		
		Button btnShowAllMusic = factory.buttonFactory("Vis alt musik", 83, false);
		btnShowAllMusic.setOnAction(e -> showAllMusiAction(searchField, genreCoB, lpChB, cdChB));
		
		
		returningHBox.getChildren().addAll(searchField, genreCoB, lpChB, cdChB, btnShowAllMusic);
		return returningHBox;
	}
	
	private void showAllMusiAction(TextField searchField, ComboBox<Genre> genreCoB, CheckBox lpChB, CheckBox cdChB) {
		searchField.setText("");
		genreCoB.getSelectionModel().clearSelection();
		lpChB.setSelected(true);
		cdChB.setSelected(true);
		
	}
}
