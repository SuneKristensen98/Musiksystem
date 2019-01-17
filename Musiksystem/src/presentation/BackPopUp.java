package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.BravoMusic;

public class BackPopUp {
	String errorMsg = "";

	public String start(BravoMusic bravoMusic) {
		Factory factory = new Factory();
		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setTitle("Bekræft valg");
		popUp.setMinWidth(450);
		
		String labeltext = "Hvis du fortsætter uden at tilføje en sang, vil albummet blive slettet.\nEr du sikker på, at du vil slette albummet?";
		Label confirmation = factory.labelFactory("Øh", 0, 0, 0, 0, 20);
		
//		labeltext = "Navn:\t" + selectedFriend.getNavn() + "\nEmail:\t" + selectedFriend.getEmail() + "\nTelefon:\t"
//				+ selectedFriend.getTelefon() + "\nRelation:\t" + selectedFriend.getRelation();
		Label friendLabel = factory.labelFactory(labeltext, 5, 0, 0, 0, 14);
		friendLabel.setAlignment(Pos.CENTER);
//
//		HBox friendHBox = factory.hBoxFactory();
//		friendHBox.getChildren().addAll(friendLabel);
//		
		Button yesButton = factory.buttonFactory("Ja, slet album", 100, false);	
		yesButton.setOnAction(e -> {
			popUp.hide();
		});

		Button noButton = factory.buttonFactory("Nej", 100, false);
		noButton.setOnAction(e -> {
			popUp.hide();
		});

		HBox buttonHBox = factory.hBoxFactory(15, 0, 0, 0, 0, Pos.CENTER);
		buttonHBox.getChildren().addAll(yesButton, noButton);		
		
		GridPane popUpGrid = new GridPane();
		popUpGrid.setPadding(new Insets(5, 10, 10, 10));
		
		popUpGrid.add(confirmation, 0, 0);
		popUpGrid.add(friendLabel, 0, 1);
		popUpGrid.add(buttonHBox, 0, 2);

		
		Scene scene = new Scene(popUpGrid);
		popUp.setScene(scene);
		popUp.showAndWait();
		
		return errorMsg;
	}
}
