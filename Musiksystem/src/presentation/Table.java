package presentation;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.*;
import logic.BravoMusic;
import logic.Impl;
import logic.domainClasses.Genre;
import logic.domainClasses.TableViewInfo;

public class Table {
	Impl impl = new Impl();
	private TableView<TableViewInfo> table = new TableView<>();
	 
	public Table(BravoMusic bravoMusic, BorderPane border, double width, MainSideAlbumButtons mainSideAlbumButtons) {
		Factory factory = new Factory();
		HBox tableHBox = new HBox();
		table.setPrefWidth(width);
		
		TableColumn<TableViewInfo, String> songName = factory.columnFactoryString("songName", "Titel", 30);
		TableColumn<TableViewInfo, String> artistName = factory.columnFactoryString("artistName", "Artist", 30);
		TableColumn<TableViewInfo, Integer> time = factory.columnFactoryInt("time", "Tid", 3);
		TableColumn<TableViewInfo, String> albumName = factory.columnFactoryString("albumName", "Album", 20);
		TableColumn<TableViewInfo, Integer> yearOfRelease = factory.columnFactoryInt("yearOfRelease", "År", 3);
		TableColumn<TableViewInfo, String> genre = factory.columnFactoryString("genre", "Genre", 10);
		TableColumn<TableViewInfo, String> songwriter = factory.columnFactoryString("songwriter", "Sangskriver", 15);
		TableColumn<TableViewInfo, String> songNote = factory.columnFactoryString("songNote", "Sangnote", 30);
		TableColumn<TableViewInfo, String> type = factory.columnFactoryString("type", "Type", 3);
		
		time.setCellFactory(e -> {
			return new TableCell<TableViewInfo, Integer>() {
			
				@Override
				 protected void updateItem(Integer time, boolean empty) {
				        super.updateItem(time, empty);
				        
				        if (time == null || empty) {
				        	setText(null);
				        	setStyle("");
			            } else {
			            	setText(new TimeConverter().secondsToDisplay(time));
			            }
				}
			};
		});
		
		table.getSelectionModel().selectedItemProperty().addListener(selection -> {
			if (selection != null) {
				mainSideAlbumButtons.controlAdmButton(false);
			}
		});
		
		List<TableViewInfo> allMusic = bravoMusic.searchMusic("", null, true, true);

		table.getColumns().setAll(songName, artistName, time, albumName, yearOfRelease, genre, songwriter, songNote, type);
		table.getItems().setAll(allMusic);
		table.getSortOrder().setAll(songName);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tableHBox.getChildren().addAll(table);
		tableHBox.setPadding(new Insets(10, 10, 10, 10));
		tableHBox.setMinWidth(1800);
		border.setCenter(tableHBox);
	}
	
	public void updateTable(List<TableViewInfo> musicFound) {
//		List<TableViewInfo> musicFound = bravoMusic.searchMusic(searchText, genre, lp, cd);
		table.getItems().setAll(musicFound);

	}
}
