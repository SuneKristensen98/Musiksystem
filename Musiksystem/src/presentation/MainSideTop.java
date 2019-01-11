package presentation;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.domainClasses.Genre;

public class MainSideTop {
	private Genre genre;
	
	public HBox hBoxTop(TableViewBla table) {
		
		//TableViewBla tableViewBla = new TableViewBla(borderpane);
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(10, 10, 0, 10));

		TextField searchField = new TextField();
		searchField.setPromptText("Søg");
		searchField.setMinWidth(500);
		searchField.textProperty().addListener(e -> {
			table.updateTable(searchField.getText());
		});
		//searchField.setPadding(new Insets(0, 5, 0, 0));
		
		ComboBox<Genre> cb = new ComboBox<Genre>();
		cb.getItems().setAll(Arrays.asList(Genre.values()));
		cb.setPromptText("Genre");
		cb.valueProperty().addListener(e -> {
			//TODO fromString()
			genre = (Genre) cb.getSelectionModel().getSelectedItem();
			table.updateTable(searchField.getText());
		});
		
		CheckBox cb1 = new CheckBox();
		CheckBox cb2 = new CheckBox();

		cb1.setText("LP");
		cb1.setSelected(true);
		//cb1.setPadding(new Insets(0, 5, 0, 5));
		cb2.setText("CD");
		cb2.setSelected(true);
		//cb2.setPadding(new Insets(0, 5, 0, 5));
		
		Button allMusic = new Button("Vis alt musik");
		
		
		
		hBox.getChildren().addAll(searchField, cb, cb1, cb2, allMusic);
		return hBox;
	}

}
