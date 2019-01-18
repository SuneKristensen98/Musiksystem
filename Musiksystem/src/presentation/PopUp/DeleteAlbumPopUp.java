package presentation.PopUp;

import java.util.List;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import logic.BravoMusic;
import logic.domainClasses.TableViewInfo;
import presentation.Factory;
import presentation.MainSide.Table;

public class DeleteAlbumPopUp {
	private Stage popUp;
	public void start(BravoMusic bravoMusic, Stage editor, int albumId, Table table) {
		Factory factory = new Factory();
		popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setTitle("Bekræft valg");
		
		String labelText1 = "Hvis du sletter albummet, sletter du samtidig alle sangene på dette";
		String labelText2 = "Er du sikker på, at du vil slette albummet med tilhørende sange?";
		Label confirmation1 = factory.labelFactory(labelText1, 0, 0, 0, 0, 14);
		Label confirmation2 = factory.labelFactory(labelText2, 0, 0, 0, 0, 14);
		
		VBox labelVBox = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		labelVBox.getChildren().addAll(confirmation1, confirmation2);
	
		Button yesButton = factory.buttonFactory("Ja, slet album", 100, false);	
		yesButton.setOnAction(e -> yesAction(bravoMusic, editor, albumId, table));

		Button noButton = factory.buttonFactory("Nej", 100, false);
		noButton.setOnAction(e -> {
			popUp.hide();
		});

		HBox buttonHBox = factory.hBoxFactory(15, 15, 0, 0, 0, Pos.CENTER);
		GridPane.setHgrow(buttonHBox, Priority.ALWAYS);
		buttonHBox.getChildren().addAll(yesButton, noButton);		

		GridPane popUpGrid = new GridPane();
		popUpGrid.setPadding(new Insets(5, 10, 10, 10));
		popUpGrid.setAlignment(Pos.CENTER);
		
		popUpGrid.add(labelVBox, 0, 1);
		popUpGrid.add(buttonHBox, 0, 2);

		Scene scene = new Scene(popUpGrid, 500, 110);
		popUp.setScene(scene);
		popUp.showAndWait();
	}
	
	private void yesAction(BravoMusic bravoMusic, Stage editor, int albumId, Table table) {
		List<TableViewInfo> songOnAlbum = bravoMusic.searchMusic("", null, true, true, albumId);
		for (int i = 0; i < songOnAlbum.size(); i++) {
			bravoMusic.deleteSong(songOnAlbum.get(i).getSongId());
		}
		
		bravoMusic.deleteAlbum(albumId);
		table.updateTable(bravoMusic.searchMusic("", null, true, true, -2));
		editor.hide();
		popUp.hide();
	}
}
