package presentation.MainSide;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import logic.BravoMusic;
import logic.Impl;
import logic.domainClasses.TableViewInfo;
import presentation.Factory;
import presentation.TimeConverter;
import presentation.PopUp.BackPopUp;
import presentation.PopUp.DeleteSongAndAlbumPopUp;
import presentation.PopUp.DeleteSongPopUp;

public class Table {
	Impl impl = new Impl();
	private TableView<TableViewInfo> table = new TableView<>();
	private HBox tableHBox;

	public Table(BravoMusic bravoMusic, Tab songTab, double width, MainSideAlbumButtons mainSideAlbumButtons) {
		Factory factory = new Factory();
		tableHBox = new HBox();
		table.setPrefWidth(width);
		table.setTableMenuButtonVisible(true);

		TableColumn<TableViewInfo, String> songName = factory.columnFactoryString("songName", "Titel", 30);
		TableColumn<TableViewInfo, String> conductorWithArtist = factory.columnFactoryString("conductorWithArtist",
				"Artist", 30);
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

					if (time == null || empty || time == 0) {
						setText(null);
						// setStyle("");
					} else {
						setText(new TimeConverter().secondsToDisplay(time));
					}
				}
			};
		});

		yearOfRelease.setCellFactory(e -> {
			return new TableCell<TableViewInfo, Integer>() {

				@Override
				protected void updateItem(Integer yearOfRelease, boolean empty) {
					super.updateItem(yearOfRelease, empty);

					if (yearOfRelease == null || empty || yearOfRelease == 0) {
						setText(null);
						// setStyle("");
					} else {
						setText(Integer.toString(yearOfRelease));
					}
				}
			};
		});

		table.setRowFactory(e -> {
			TableRow<TableViewInfo> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					mainSideAlbumButtons.updateAction(bravoMusic);
				}
			});
			return row;
		});

		table.getSelectionModel().selectedItemProperty().addListener(e -> {
			if (table.getSelectionModel().selectedItemProperty().getValue() != null) {
				mainSideAlbumButtons.controlAdmButton(false);
			} else {
				mainSideAlbumButtons.controlAdmButton(true);
			}
		});

		ContextMenu cm = new ContextMenu();
		MenuItem menuSletSang = new MenuItem("Slet sang");
		cm.getItems().addAll(menuSletSang);
		table.setContextMenu(cm);

		table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {
					cm.show(table, t.getScreenX(), t.getScreenY());
				}

			}
		});

		menuSletSang.setOnAction(e -> {
			DeleteSongPopUp deleteSongPopUp = new DeleteSongPopUp();
			int songId = table.getSelectionModel().getSelectedItem().getSongId();
			int albumId = table.getSelectionModel().getSelectedItem().getAlbumId();

			
			if (bravoMusic.searchMusic("", null, true, true, albumId).size() == 1) {
				DeleteSongAndAlbumPopUp deleteSongAndAlbumPopUp = new DeleteSongAndAlbumPopUp();
				if (deleteSongAndAlbumPopUp.start(bravoMusic, albumId, songId)) {
					updateTable(bravoMusic.searchMusic("", null, true, true, -2));
				} 

			} else {
				if (deleteSongPopUp.start(bravoMusic, songId, albumId)) {
					updateTable(bravoMusic.searchMusic("", null, true, true, -2));
				}
			}
		});

		List<TableViewInfo> allMusic = bravoMusic.searchMusic("", null, true, true, -2);

		table.getColumns().setAll(songName, conductorWithArtist, time, albumName, yearOfRelease, genre, songwriter,
				type, songNote);
		table.getItems().setAll(allMusic);
		table.getSortOrder().setAll(songName);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableHBox.getChildren().addAll(table);
		tableHBox.setPadding(new Insets(10, 10, 0.2, 10));
		tableHBox.setMinWidth(1800);
		tableHBox.setStyle("-fx-border-style: hidden hidden solid hidden; -fx-border-color: MEDIUMPURPLE");
		songTab.setContent(tableHBox);
	}

	public void updateTable(List<TableViewInfo> musicFound) {
		table.getItems().setAll(musicFound);
	}

	public int selectedRow() {
		int albumId = table.getSelectionModel().getSelectedItem().getAlbumId();
		return albumId;
	}
}
