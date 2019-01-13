package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class EditorBottom {

	public HBox editorBottom() {
		
		//Setup
		HBox btnBox = new HBox(25);
		btnBox.setPadding(new Insets(25, 25 ,25 ,25));
		btnBox.setAlignment(Pos.CENTER);
		btnBox.setPrefHeight(100);
		
		//Buttons
		btnBox.setPadding(new Insets(0, 0, 0, 25));
		btnBox.setAlignment(Pos.CENTER_LEFT);
		
		Button btnAlbumDelete = new Button("Slet");
		btnAlbumDelete.setPrefSize(100, 20);
		Button btnAlbumCancel = new Button("Fortryd");
		btnAlbumCancel.setPrefSize(100, 20);
		Button btnAlbumSave = new Button("Gem");
		btnAlbumSave.setPrefSize(100, 20);
		
		//Placement
		btnBox.getChildren().addAll(btnAlbumCancel, btnAlbumDelete, btnAlbumSave);
		
		//Return
		return btnBox;
	}
}
