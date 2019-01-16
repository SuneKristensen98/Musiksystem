package presentation;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.BravoMusic;
import logic.domainClasses.Album;

public class EditorAlbum {
	public VBox editorAlbum(Stage editor, BravoMusic bravoMusic, int albumId, EditorSong editorSong) {
		Factory factory = new Factory();
		
		//Class Call
		EditorBottom editorbottom = new EditorBottom();

		// Setup
		VBox albumVBox = factory.vBoxFactory(25, 25, 25, 25, Pos.CENTER);

		// Box Setup
		HBox albumTop = factory.hBoxFactory(25, 0, 0, 25, 0, Pos.BASELINE_LEFT);
		VBox albumBot = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		albumBot.setMinHeight(300);
		VBox albumRight = new VBox();
		VBox albumLeft = new VBox();
		HBox choiceBox = factory.hBoxFactory(15, 35, 0, 0, 0, Pos.CENTER);
		VBox albumTitle = factory.vBoxFactory(0, 0, 0, 0, Pos.CENTER);
		
		// Label
		Label labelAlbum = factory.labelFactory("Album", 0, 0, 5, 0, 20);
		Label labelDescription = factory.labelFactory("Beskrivelse", 24, 0, 5, 0, 16);
		Label albumName = factory.labelFactory("Albumtitel", 25, 0, 5, 0, 16);
		Label albumYear = factory.labelFactory("Udgivelsesår", 25, 0, 5, 0, 16);
		
		// Textfield
		TextField tfAlbumName = factory.textFieldFactory("", 362, 14);
		TextField tfYearOfRelease = factory.textFieldFactory("", 362, 14);

		// Textarea
		TextArea taDescription = new TextArea();
		taDescription.setPrefHeight(1000);
		taDescription.setPrefWidth(362);
		taDescription.setFont(Font.font("Helvetica", 14));

		// Radio Buttons
		RadioButton radioButton1 = new RadioButton("LP");
		radioButton1.setUserData("LP");
		RadioButton radioButton2 = new RadioButton("CD");
		radioButton2.setUserData("CD");

		ToggleGroup radioGroup = new ToggleGroup();
		radioButton1.setToggleGroup(radioGroup);
		radioButton2.setToggleGroup(radioGroup);

		
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

		EditorTable editorTable = new EditorTable(albumBot, albumId);

		Label toggleErrorMsg = factory.labelFactory("LP eller CD skal vælges", 5, 0, 0, 0, -1);
		toggleErrorMsg.setTextFill(Color.RED);
		toggleErrorMsg.setVisible(false);

		HBox toogleErrorMsgHBox = factory.hBoxFactory(0, 0, 0, 0, 0, Pos.CENTER);
		toogleErrorMsgHBox.getChildren().add(toggleErrorMsg);
		
		// Placement
		albumVBox.getChildren().addAll(albumTitle, albumTop, albumBot, editorbottom.editorBottom(bravoMusic, editorSong, editor, tfAlbumName, tfYearOfRelease, taDescription, radioGroup, toggleErrorMsg));
		albumTitle.getChildren().addAll(labelAlbum);
		albumTop.getChildren().addAll(albumLeft, albumRight);
		albumRight.getChildren().addAll(labelDescription, taDescription);
		albumLeft.getChildren().addAll(albumName, tfAlbumName, albumYear, tfYearOfRelease, choiceBox, toogleErrorMsgHBox);
		choiceBox.getChildren().addAll(radioButton1, radioButton2);

		// Return
		return albumVBox;
	}
}

//		VBox albumVBox = new VBox();
//		albumVBox.setPadding(new Insets(25, 25, 25, 25));
//		albumVBox.setAlignment(Pos.CENTER);
//		HBox albumTop = new HBox(25);
//		albumTop.setPadding(new Insets(0, 0, 25, 0));
//		VBox albumBot = new VBox();
//		albumBot.setPadding(new Insets(0, 0, 0, 0));
//albumRight.setAlignment(Pos.CENTER);
//albumLeft.setAlignment(Pos.TOP_CENTER);
//		HBox choiceBox = new HBox(15);
//		choiceBox.setPadding(new Insets(35, 0, 0, 0));
//		choiceBox.setAlignment(Pos.CENTER);
//		VBox albumTitle = new VBox();		
//		albumTitle.setAlignment(Pos.CENTER);
//		Label labelAlbum = new Label("Album");
//		labelAlbum.setPadding(new Insets(0, 0, 5, 0));
//		labelAlbum.setFont(Font.font("Helvetica", 20));
//
//		Label labelDescription = new Label("Beskrivelse:");
//		labelDescription.setPadding(new Insets(24, 0, 5, 0));
//		labelDescription.setFont(Font.font("Helvetica", 16));
//
//		Label albumName = new Label("Album Navn:");
//		albumName.setFont(Font.font("Helvetica", 16));
//		albumName.setPadding(new Insets(25, 0, 5, 0));
//
//		Label albumYear = new Label("Udgivelsesår:");
//		albumYear.setFont(Font.font("Helvetica", 16));
//		albumYear.setPadding(new Insets(25, 0, 5, 0));
//		TextField tfAlbumName = new TextField();
//		tfAlbumName.setPrefWidth(362);
//		tfAlbumName.setFont(Font.font("Helvetica", 14));
//
//		TextField tfYearOfRelease = new TextField();
//		tfYearOfRelease.setPrefWidth(362);
//		tfYearOfRelease.setFont(Font.font("Helvetica", 14));
//		HBox toogleErrorMsgHBox = new HBox();
//		toogleErrorMsgHBox.setAlignment(Pos.CENTER);