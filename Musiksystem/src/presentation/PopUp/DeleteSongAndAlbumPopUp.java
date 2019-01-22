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

public class DeleteSongAndAlbumPopUp {
	boolean deletecompleted;

	public boolean start(BravoMusic bravoMusic, int albumId, int songId) {
		Factory factory = new Factory();
		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setTitle("Bekræft valg");

		String labelText1 = "Denne sang er den sidste på albummet. Hvis du sletter sangen, bliver albummet slettet";
		String labelText2 = "Er du sikker på, at du vil slette sangen og albummet?";
		Label confirmation1 = factory.labelFactory(labelText1, 0, 0, 0, 0, 14);
		Label confirmation2 = factory.labelFactory(labelText2, 0, 0, 0, 0, 14);

		VBox labelVBox = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		labelVBox.getChildren().addAll(confirmation1, confirmation2);

		Button yesButton = factory.buttonFactory("Ja, slet begge", 100, false);
		yesButton.setOnAction(e -> {
			bravoMusic.deleteSong(songId);
			bravoMusic.deleteAlbum(albumId);
			popUp.hide();
			deletecompleted = true;
		});

		Button noButton = factory.buttonFactory("Nej", 100, false);
		noButton.setOnAction(e -> {
			popUp.hide();
			deletecompleted = false;
		});

		HBox buttonHBox = factory.hBoxFactory(15, 15, 0, 0, 0, Pos.CENTER);
		GridPane.setHgrow(buttonHBox, Priority.ALWAYS);
		buttonHBox.getChildren().addAll(yesButton, noButton);

		GridPane popUpGrid = new GridPane();
		popUpGrid.setPadding(new Insets(5, 10, 10, 10));
		popUpGrid.setAlignment(Pos.CENTER);

		popUpGrid.add(labelVBox, 0, 1);
		popUpGrid.add(buttonHBox, 0, 2);

		Scene scene = new Scene(popUpGrid, 580, 110);
		popUp.setScene(scene);
		popUp.showAndWait();

		return deletecompleted;

	}

}
