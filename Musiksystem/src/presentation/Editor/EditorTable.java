package presentation.Editor;

import java.util.List;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import logic.Impl;
import logic.domainClasses.TableViewInfo;
import presentation.Factory;

public class EditorTable {

	Impl impl = new Impl();
	private TableView<TableViewInfo> table = new TableView<>();

	public EditorTable(VBox albumBot, int albumId, EditorSong editorSong) {
		Factory factory = new Factory();

		TableColumn<TableViewInfo, String> songName = factory.columnFactoryString("songName", "Titel", 30);
		TableColumn<TableViewInfo, String> artistName = factory.columnFactoryString("conductorWithArtist", "Artist", 30);
		List<TableViewInfo> allMusic = impl.searchMusic("", null, true, true, albumId);

		table.getColumns().setAll(songName, artistName);
		table.getItems().setAll(allMusic);
		table.getSortOrder().setAll(songName);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		albumBot.getChildren().addAll (table); 

		table.getSelectionModel().selectedItemProperty().addListener(e -> {
			if (table.getSelectionModel().selectedItemProperty().getValue() != null) {
				editorSong.setTextFieldsFromTable(table.getSelectionModel().getSelectedItem());
				editorSong.controlBtnDelete(false);
				editorSong.controlBtnEdit(false);
				editorSong.controlBtnAdd(true);
			}
		});
	}

	public void updateTable(int albumId) {
		// table.getSelectionModel().select(null);
		List<TableViewInfo> musicFound = impl.searchMusic("", null, true, true, albumId);
		table.getItems().setAll(musicFound);
	}

//	public TableViewInfo selectedRow() {
//		TableViewInfo selectedRow = table.getSelectionModel().getSelectedItem();
//		return selectedRow();
//	}

}
