package presentation.MainSide;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import logic.BravoMusic;
import presentation.Factory;
import presentation.Editor.Editor;

public class MainSideAlbumButtons {
	private HBox returningHBox;
	private Button btnAdm;
	private Table table;
	Glow glow = new Glow();

	public HBox hBoxAlbumButtons(BravoMusic bravoMusic, Table tempTable) {
		table = tempTable;
		Factory factory = new Factory();

		returningHBox = factory.hBoxFactory(10, 10, 10, 0, 10, Pos.BOTTOM_RIGHT);

		Button btnCreate = factory.buttonFactory("Opret album", 88, false);
		btnCreate.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");
		btnCreate.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btnCreate.setEffect(glow);
			}
		});

		btnCreate.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btnCreate.setEffect(null);
			}
		});
		
		btnAdm = factory.buttonFactory("Administrer album", 122, true);
		btnAdm.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");
		btnAdm.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btnAdm.setEffect(glow);
			}
		});

		btnAdm.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				btnAdm.setEffect(null);
			}
		});

		btnCreate.setOnAction(e -> createAction(bravoMusic));
		btnAdm.setOnAction(e -> updateAction(bravoMusic));

		returningHBox.getChildren().addAll(btnCreate, btnAdm);

		return returningHBox;
	}

	public void controlAdmButton(boolean disable) {
		btnAdm.setDisable(disable);
	}

	private void createAction(BravoMusic bravoMusic) {
		Editor editor = new Editor();
		editor.start(bravoMusic, table, -1);
	}

	public void updateAction(BravoMusic bravoMusic) {
		Editor editor = new Editor();
		int albumId = table.selectedRow();
		editor.start(bravoMusic, table, albumId);
	}
}
