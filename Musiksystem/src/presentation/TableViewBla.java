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
		
		TableColumn<TableViewInfo, String> songName = factory.columnFactoryString("songName", "Titel", 30);
		TableColumn<TableViewInfo, String> artistName = factory.columnFactoryString("artistName", "Artist", 30);
		TableColumn<TableViewInfo, Integer> time = factory.columnFactoryInt("time", "Tid", 3);
		TableColumn<TableViewInfo, String> albumName = factory.columnFactoryString("albumName", "Album", 20);
		TableColumn<TableViewInfo, Integer> yearOfRelease = factory.columnFactoryInt("yearOfRelease", "År", 3);
		TableColumn<TableViewInfo, String> genre = factory.columnFactoryString("genre", "Genre", 10);
		TableColumn<TableViewInfo, String> songwriter = factory.columnFactoryString("songwriter", "Sangskriver", 15);
		TableColumn<TableViewInfo, String> songNote = factory.columnFactoryString("songNote", "Sangnote", 30);
		TableColumn<TableViewInfo, String> type = factory.columnFactoryString("type", "Type", 3);
		
//		time.setCellFactory(e -> {
//			return new TableCell<TableViewInfo, Integer>() {
//			
//				@Override
//				 protected void updateItem(Integer time, boolean empty) {
//				        super.updateItem(time, empty);
//				        
//				        if (time == null || empty) {
//				        	setText(null);
//				        	setStyle("");
//			            } else {
//			            	setText(new TimeConverter().secondsToDisplay(time));
//			            }
//				}
//			};
//		});
		
		List<TableViewInfo> allMusic = impl.searchMusic("", null, true, true);

		table.getColumns().setAll(songName, artistName, time, albumName, yearOfRelease, genre, songwriter, songNote, type);
		table.getItems().setAll(allMusic);
		table.getSortOrder().setAll(songName);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		hBox.getChildren().addAll(table);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setMinWidth(1800);
		border.setCenter(hBox);
	
	}
	
	public void updateTable(String searchText, Genre genre, boolean lp, boolean cd) {
		List<TableViewInfo> musicFound = impl.searchMusic(searchText, genre, lp, cd);
		table.getItems().setAll(musicFound);

	}
}
