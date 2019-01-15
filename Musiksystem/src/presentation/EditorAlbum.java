package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.BravoMusic;
import logic.domainClasses.Album;

public class EditorAlbum {
	public VBox editorAlbum(Stage editor, BravoMusic bravoMusic, int albumId) {
		
		//Class Call
		EditorBottom editorbottom = new EditorBottom();

		// Setup
		VBox albumBox = new VBox();
		albumBox.setPadding(new Insets(25, 25, 25, 25));
		albumBox.setAlignment(Pos.CENTER);

		// TableView table2 = new TableView<>();

		// Box Setup
		HBox albumTop = new HBox(25);
		albumTop.setPadding(new Insets(0, 0, 25, 0));

		VBox albumBot = new VBox();
		albumBot.setMinHeight(300);
		albumBot.setPadding(new Insets(0, 0, 0, 0));

		VBox albumRight = new VBox();
		albumRight.setAlignment(Pos.CENTER);

		VBox albumLeft = new VBox();
		albumLeft.setAlignment(Pos.TOP_CENTER);

		HBox choiceBox = new HBox(15);
		choiceBox.setPadding(new Insets(35, 0, 0, 0));
		choiceBox.setAlignment(Pos.CENTER);

		VBox albumTitle = new VBox();
		albumTitle.setAlignment(Pos.CENTER);

		// Label
		Label labelAlbum = new Label("Album");
		labelAlbum.setPadding(new Insets(0, 0, 5, 0));
		labelAlbum.setFont(Font.font("Helvetica", 20));

		Label labelDescription = new Label("Beskrivelse:");
		labelDescription.setPadding(new Insets(24, 0, 5, 0));
		labelDescription.setFont(Font.font("Helvetica", 16));

		Label albumName = new Label("Album Navn:");
		albumName.setFont(Font.font("Helvetica", 16));
		albumName.setPadding(new Insets(25, 0, 5, 0));

		Label albumYear = new Label("Udgivelsesår:");
		albumYear.setFont(Font.font("Helvetica", 16));
		albumYear.setPadding(new Insets(25, 0, 5, 0));

		// Textfield
		TextField tfAlbumName = new TextField();
		tfAlbumName.setPrefWidth(350);
		tfAlbumName.setFont(Font.font("Helvetica", 14));

		TextField tfYearOfRelease = new TextField();
		tfYearOfRelease.setPrefWidth(350);
		tfYearOfRelease.setFont(Font.font("Helvetica", 14));

		// Textarea
		TextArea taDescription = new TextArea();
		taDescription.setPrefHeight(1000);
		taDescription.setPrefWidth(400);
		taDescription.setFont(Font.font("Helvetica", 14));

		// Radio Buttons
		RadioButton radioButton1 = new RadioButton("LP");
		radioButton1.setUserData("LP");
		RadioButton radioButton2 = new RadioButton("CD");
		radioButton2.setUserData("CD");

		ToggleGroup radioGroup = new ToggleGroup();
		radioButton1.setToggleGroup(radioGroup);
		radioButton2.setToggleGroup(radioGroup);

		EditorTable editorTable = new EditorTable(albumBot);
		
		if (albumId != -1) {
			Album album = bravoMusic.searchAlbumWithId(albumId);
			tfAlbumName.setText(album.getAlbumName());
			tfYearOfRelease.setText(Integer.toString(album.getYearOfRelease()));
			taDescription.setText(album.getAlbumDescription());
			System.out.println(album.getType());
			if (album.getType().equals("LP")) {
				radioButton1.setSelected(true);
			} else if (album.getType().equals("CD")) {
				radioButton2.setSelected(true);			
			}
		}
		
		// Placement
		albumBox.getChildren().addAll(albumTitle, albumTop, albumBot, editorbottom.editorBottom(bravoMusic, editor, tfAlbumName, tfYearOfRelease, taDescription, radioGroup));
		albumTitle.getChildren().addAll(labelAlbum);
		albumTop.getChildren().addAll(albumLeft, albumRight);
		albumRight.getChildren().addAll(labelDescription, taDescription);
		albumLeft.getChildren().addAll(albumName, tfAlbumName, albumYear, tfYearOfRelease, choiceBox);
		choiceBox.getChildren().addAll(radioButton1, radioButton2);

		// Return
		return albumBox;
	}
}
