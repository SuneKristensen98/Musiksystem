package presentation;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.Impl;
import logic.domainClasses.Genre;
import logic.domainClasses.TableViewInfo;

public class TableViewBla {
	Impl impl = new Impl();
	private TableView<TableViewInfo> table = new TableView<>();
//	private List<TableViewInfo> allMusic;
	
	public TableViewBla(BorderPane border) {
		Factory factory = new Factory();
		HBox hBox = new HBox();
		table.setStyle("-fx-min-height: 500px; -fx-min-width: 1800px");
		
		TableColumn<TableViewInfo, String> songName = factory.columnFactory("songName", "Titel", 30);
		TableColumn<TableViewInfo, String> artistName = factory.columnFactory("artistName", "Artist", 30);
		TableColumn<TableViewInfo, String> time = factory.columnFactory("time", "Tid", 3);
		TableColumn<TableViewInfo, String> albumName = factory.columnFactory("albumName", "Album", 20);
		TableColumn<TableViewInfo, String> yearOfRelease = factory.columnFactory("yearOfRelease", "År", 3);
		TableColumn<TableViewInfo, String> genre = factory.columnFactory("genre", "Genre", 10);
		TableColumn<TableViewInfo, String> songwriter = factory.columnFactory("songwriter", "Sangskriver", 15);
		TableColumn<TableViewInfo, String> songNote = factory.columnFactory("songNote", "Sangnote", 30);
		TableColumn<TableViewInfo, String> type = factory.columnFactory("type", "Type", 3);

		List<TableViewInfo> allMusic = impl.searchMusic("");

		table.getColumns().setAll(songName, artistName, time, albumName, yearOfRelease, genre, songwriter, songNote, type);
		table.getItems().setAll(allMusic);
		table.getSortOrder().setAll(songName);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		hBox.getChildren().addAll(table);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setMinWidth(1800);
		border.setCenter(hBox);
	
	}
	
	public void updateTable(String searchText) {
		List<TableViewInfo> musicFound = impl.searchMusic(searchText);
		table.getItems().setAll(musicFound);

	}
}
