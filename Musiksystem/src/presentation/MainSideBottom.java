package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainSideBottom {
	public HBox hBoxBottom() {
		Factory factory = new Factory();

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 0, 10));
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);

		
		Button btnAdm = factory.buttonFactory("Adminstrer album", 120, 20);
		btnAdm.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");
		Button btnCreate = factory.buttonFactory("Opret album", 120, 20);
		btnCreate.setStyle("-fx-background-color: MEDIUMPURPLE; -fx-font-weight: BOLD");


		btnAdm.setOnAction(e -> deleteaction());
		btnCreate.setOnAction(e -> deleteaction());
		
		hBox.getChildren().addAll(btnCreate, btnAdm);
		
		return hBox;
	}
	
	
private void deleteaction() {
		
		Editor editor = new Editor();
		editor.start();
		
	}
}
