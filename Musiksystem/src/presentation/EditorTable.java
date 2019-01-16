package presentation;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.Impl;
import logic.domainClasses.Genre;
import logic.domainClasses.TableViewInfo;

public class EditorTable {


		Impl impl = new Impl();
		private TableView<TableViewInfo> table2 = new TableView<>();
		
		public EditorTable(VBox albumBot, int albumId) {
			Factory factory = new Factory();
			//table.setPrefWidth(width);
			table2.setStyle("-fx-pref-height: 500px; -fx-pref-width: 1600px");
			
			TableColumn<TableViewInfo, String> songName = factory.columnFactoryString("songName", "Titel", 30);
			TableColumn<TableViewInfo, String> artistName = factory.columnFactoryString("artistName", "Artist", 30);
			
			List<TableViewInfo> allMusic = impl.searchMusic("", null, true, true, albumId);

			table2.getColumns().setAll(songName, artistName);
			table2.getItems().setAll(allMusic);
			table2.getSortOrder().setAll(songName);
			table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
			albumBot.getChildren().addAll(table2);
			
			
		}
		
		public void updateTable(String searchText, Genre genre, boolean lp, boolean cd, int albumId) {
			List<TableViewInfo> musicFound = impl.searchMusic(searchText, genre, lp, cd, albumId);
			table2.getItems().setAll(musicFound);

		}
	}
	

