package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import logic.BravoMusic;

public class MainSideAlbumButtons {
	private HBox returningHBox = new HBox();
	Factory factory = new Factory();
	private Button btnAdm = factory.buttonFactory("Adminstrer album", 119);
	
	public HBox hBoxAlbumButtons(BravoMusic bravoMusic, Table table) {
 
		returningHBox.setPadding(new Insets(10, 10, 0, 10));
		returningHBox.setSpacing(10);
		returningHBox.setAlignment(Pos.BOTTOM_RIGHT);

		
		btnAdm.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");
		btnAdm.setDisable(true);
		Button btnCreate = factory.buttonFactory("Opret album", 88);
		btnCreate.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");


		btnAdm.setOnAction(e -> deleteAction());
		btnCreate.setOnAction(e -> deleteAction());
		
		returningHBox.getChildren().add(btnCreate);
		//controlAdmButton(false);
		insertButton();
		
		//returningHBox.getChildren().addAll(btnCreate, btnAdm);

		return returningHBox;
	}
	
	public void controlAdmButton(boolean enable) {
		if (enable) {
			System.out.println("enable");
			//returningHBox.getChildren().remove(btnAdm);
			btnAdm.setDisable(false);
			//returningHBox.getChildren().add(btnAdm);

		} else {
			//System.out.println("disable");
			returningHBox.getChildren().remove(btnAdm);
			btnAdm.setDisable(true);
		}
	}
	
	public void insertButton() {
		returningHBox.getChildren().add(btnAdm);
	}
	
	
	private void deleteAction() {
		
		Editor editor = new Editor();
		editor.start();
		
	}
}
