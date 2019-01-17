package presentation.PopUp;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import logic.BravoMusic;
import presentation.Factory;

public class BackPopUp {
	String errorMsg = "";

	public void start(BravoMusic bravoMusic, Stage editor, int albumId) {
		Factory factory = new Factory();
		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setTitle("Bekræft valg");
		popUp.setMinWidth(450);
		
		String labelText1 = "Hvis du fortsætter uden at tilføje en sang, vil albummet blive slettet.";
		String labelText2 = "Er du sikker på, at du vil slette albummet?";
		Label confirmation = factory.labelFactory(labelText1, 0, 0, 0, 0, 14);
		Label friendLabel = factory.labelFactory(labelText2, 0, 0, 0, 0, 14);
		
		VBox labelVBox = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		labelVBox.getChildren().addAll(confirmation, friendLabel);
	
		Button yesButton = factory.buttonFactory("Ja, slet album", 100, false);	
		yesButton.setOnAction(e -> {
			bravoMusic.deleteAlbum(albumId);
			popUp.hide();
			editor.hide();
		});

		Button noButton = factory.buttonFactory("Nej", 100, false);
		noButton.setOnAction(e -> {
			popUp.hide();
		});

		HBox buttonHBox = factory.hBoxFactory(15, 10, 0, 0, 0, Pos.CENTER);
		buttonHBox.getChildren().addAll(yesButton, noButton);		
		
		GridPane popUpGrid = new GridPane();
		popUpGrid.setPadding(new Insets(5, 10, 10, 10));
		
		popUpGrid.add(labelVBox, 0, 1);
		popUpGrid.add(buttonHBox, 0, 2);

		
		Scene scene = new Scene(popUpGrid);
		popUp.setScene(scene);
		popUp.showAndWait();
	}
}
