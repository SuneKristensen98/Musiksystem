package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainSideAlbumButtons {
	public HBox hBoxAlbumButtons() {
		Factory factory = new Factory();

		HBox returningHBox = new HBox();
		returningHBox.setPadding(new Insets(10, 10, 0, 10));
		returningHBox.setSpacing(10);
		returningHBox.setAlignment(Pos.BOTTOM_RIGHT);

		
		Button btnAdm = factory.buttonFactory("Adminstrer album", 119);
		btnAdm.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");
		Button btnCreate = factory.buttonFactory("Opret album", 88);
		btnCreate.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");


		btnAdm.setOnAction(e -> deleteAction());
		btnCreate.setOnAction(e -> deleteAction());
		
		returningHBox.getChildren().addAll(btnCreate, btnAdm);
		
		return returningHBox;
	}
	
	
	private void deleteAction() {
		
		Editor editor = new Editor();
		editor.start();
		
	}
}
