package presentation;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainSideTop {
	public HBox hBoxTop() {
		HBox hBox = new HBox();
		TextField searchField = new TextField();
		searchField.setPromptText("Søg");
		hBox.getChildren().addAll(searchField);
		return hBox;
	}

}
