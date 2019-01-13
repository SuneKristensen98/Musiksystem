package presentation;

import java.util.Arrays;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.domainClasses.Genre;

public class MainSideSearch {
	private Genre genre;
	
	public HBox hBoxSearch(Table table) {
		Factory factory = new Factory();
		
		HBox returningHBox = new HBox();
		returningHBox.setSpacing(10);
		returningHBox.setPadding(new Insets(10, 10, 0, 10));

		CheckBox lpChB = new CheckBox();
		CheckBox cdChB = new CheckBox();

		TextField searchField = factory.textFieldFactory("Søg", 500);
		searchField.textProperty().addListener(e -> {
			table.updateTable(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected());
		});
		
		ComboBox<Genre> genreCoB = new ComboBox<Genre>();
		genreCoB.getItems().setAll(Arrays.asList(Genre.values()));
		genreCoB.setPromptText("Genre");
		genreCoB.valueProperty().addListener(e -> {
			//TODO fromString()
			genre = (Genre) genreCoB.getSelectionModel().getSelectedItem();
			table.updateTable(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected());
		});
		

		lpChB.setText("LP");
		lpChB.setSelected(true);
		lpChB.selectedProperty().addListener(e -> {
			table.updateTable(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected());
		});

		cdChB.setText("CD");
		cdChB.setSelected(true);
		cdChB.selectedProperty().addListener(e -> {
			table.updateTable(searchField.getText(), genre, lpChB.isSelected(), cdChB.isSelected());
		});
		
		Button btnShowAllMusic = new Button("Vis alt musik");
//		btnShowAllMusic.setOnAction(e -> showAllMusiAction());
		
		
		returningHBox.getChildren().addAll(searchField, genreCoB, lpChB, cdChB, btnShowAllMusic);
		return returningHBox;
	}
	
//	private void showAllMusiAction() {		
//	}

}
