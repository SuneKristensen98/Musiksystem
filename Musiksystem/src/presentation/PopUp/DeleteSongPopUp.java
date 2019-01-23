package presentation.PopUp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.BravoMusic;
import presentation.Factory;

public class DeleteSongPopUp {

	boolean deletecompleted;

	public boolean start(BravoMusic bravoMusic, int songId, int albumId) {
		Factory factory = new Factory();
		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setTitle("Bekræft valg");
		
		String labelText1 = "Er du sikker på at du vil slette sangen?";
		Label confirmation = factory.labelFactory(labelText1, 0, 0, 0, 0, 14);
		
		VBox labelVBox = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		labelVBox.getChildren().addAll(confirmation);
	
		Button yesButton = factory.buttonFactory("Ja, slet sang", 100, false);	
		yesButton.setOnAction(e -> {
			bravoMusic.deleteSong(songId);
			popUp.hide();
			deletecompleted = true;
			
		});

		Button noButton = factory.buttonFactory("Nej", 100, false);
		noButton.setOnAction(e -> {
			popUp.hide();
			deletecompleted = false;
		});

		HBox buttonHBox = factory.hBoxFactory(15, 10, 0, 0, 0, Pos.CENTER);
		GridPane.setHgrow(buttonHBox, Priority.ALWAYS);
		buttonHBox.getChildren().addAll(yesButton, noButton);		
		
		GridPane popUpGrid = new GridPane();
		popUpGrid.setPadding(new Insets(5, 10, 10, 10));
		popUpGrid.setAlignment(Pos.CENTER);
		
		popUpGrid.add(labelVBox, 0, 1);
		popUpGrid.add(buttonHBox, 0, 2);

		Scene scene = new Scene(popUpGrid, 500, 85);
		popUp.setScene(scene);
		popUp.showAndWait();
		
		return deletecompleted;
	}
}

