package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.BravoMusic;

public class MainSideAlbumButtons {
	private HBox returningHBox = new HBox();
	private Button btnAdm;
	
	public HBox hBoxAlbumButtons(BravoMusic bravoMusic, Table table) {
		Factory factory = new Factory();

		returningHBox.setPadding(new Insets(10, 10, 0, 10));
		returningHBox.setSpacing(10);
		returningHBox.setAlignment(Pos.BOTTOM_RIGHT);

		btnAdm = factory.buttonFactory("Adminstrer album", 119);
		btnAdm.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");
		btnAdm.setDisable(true);
		Button btnCreate = factory.buttonFactory("Opret album", 88);
		btnCreate.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");

		btnAdm.setOnAction(e -> deleteAction());
		btnCreate.setOnAction(e -> deleteAction());
		
		returningHBox.getChildren().addAll(btnCreate, btnAdm);

		return returningHBox;
	}
	
	public void controlAdmButton(boolean disable) {
		btnAdm.setDisable(disable);
	}
	
	private void deleteAction() {
		Editor editor = new Editor();
		editor.start();
	}
}
