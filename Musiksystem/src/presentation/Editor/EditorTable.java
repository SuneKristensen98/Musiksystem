package presentation.Editor;

import java.util.List;

import domainClasses.TableViewInfo;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import logic.Impl;
import presentation.Factory;

public class EditorTable {
	Impl impl = new Impl();
	private TableColumn<TableViewInfo, String> songName;
	private TableView<TableViewInfo> table = new TableView<>();

	public EditorTable(VBox albumBot, int albumId, EditorSong editorSong) {
		Factory factory = new Factory();

		songName = factory.columnFactoryString("songName", "Titel", 30);
		TableColumn<TableViewInfo, String> artistName = factory.columnFactoryString("conductorWithArtist", "Artist", 30);
		List<TableViewInfo> allMusic = impl.searchMusic("", null, true, true, albumId);

		table.getColumns().setAll(songName, artistName);
		table.getItems().setAll(allMusic);
		table.getSortOrder().setAll(songName);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
 
		albumBot.getChildren().addAll (table); 

		table.getSelectionModel().selectedItemProperty().addListener(e -> {
			if (table.getSelectionModel().selectedItemProperty().getValue() != null) {
				editorSong.setTFFromTable(table.getSelectionModel().getSelectedItem());
				editorSong.controlBtnDelete(false);
				editorSong.controlBtnEdit(false);
				editorSong.controlBtnAdd(true);
			}
		});
	}

	public void updateTable(int albumId) {
		List<TableViewInfo> musicFound = impl.searchMusic("", null, true, true, albumId);
		table.getItems().setAll(musicFound);
		table.getSortOrder().setAll(songName);
	}
}