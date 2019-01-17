package presentation.MainSide;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.BravoMusic;
import presentation.Factory;
import presentation.Editor.Editor;

public class MainSideAlbumButtons {
	private HBox returningHBox;
	private Button btnAdm;
	
	public HBox hBoxAlbumButtons(BravoMusic bravoMusic, Table table) {
		Factory factory = new Factory();
		
		returningHBox = factory.hBoxFactory(10, 10, 10, 0, 10, Pos.BOTTOM_RIGHT);

		Button btnCreate = factory.buttonFactory("Opret album", 88, false);
		btnCreate.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");
		btnAdm = factory.buttonFactory("Adminstrer album", 119, true);
		btnAdm.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");

		btnCreate.setOnAction(e -> createAction(bravoMusic, table));
		btnAdm.setOnAction(e -> updateAction(bravoMusic, table));
		
		returningHBox.getChildren().addAll(btnCreate, btnAdm);

		return returningHBox;
	}
	
	public void controlAdmButton(boolean disable) {
		btnAdm.setDisable(disable);
	}
	
	private void createAction(BravoMusic bravoMusic, Table table) {
		Editor editor = new Editor();
		editor.start(bravoMusic, table, -1);
	}
	
	private void updateAction(BravoMusic bravoMusic, Table table) {
		Editor editor = new Editor();
		int albumId = table.selectedRow();
		editor.start(bravoMusic, table, albumId);
	}
}
