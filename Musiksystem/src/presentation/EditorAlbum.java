package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EditorAlbum {

	public VBox editorAlbum() {
		
		//Setup
		VBox albumBox = new VBox();
		albumBox.setPadding(new Insets(25, 25 ,25 ,25));
		albumBox.setAlignment(Pos.CENTER);
	
	//	TableView table2 = new TableView<>();
		
		//Box Setup
		HBox albumTop = new HBox(25);
		albumTop.setPadding(new Insets(0, 0 ,25 ,0));
		
		VBox albumBot = new VBox();
		albumBot.setMinHeight(300);
		albumBot.setPadding(new Insets(25, 25 ,25 ,25));
		
		VBox albumRight = new VBox();
		albumRight.setAlignment(Pos.CENTER);
		
		VBox albumLeft = new VBox();
		albumLeft.setAlignment(Pos.TOP_CENTER);
		
		HBox choiceBox = new HBox(15);
		choiceBox.setPadding(new Insets(35, 0, 0, 0));
		choiceBox.setAlignment(Pos.CENTER);
		
		//Label
		Label labelAlbum = new Label("Album");
		labelAlbum.setPadding(new Insets(0, 0, 5, 0));
		labelAlbum.setFont(Font.font("Helvetica", 16));
		
		Label labelDescription = new Label("Beskrivelse:");
		labelDescription.setPadding(new Insets(0, 0, 5, 0));
		labelDescription.setFont(Font.font("Helvetica", 16));
		
		Label albumName = new Label("Album Name:");
		
		Label albumYear = new Label("Year Of Release:");
		albumYear.setPadding(new Insets(35, 0, 5, 0));
		
		//Textfield
		TextField tfAlbumName = new TextField();
		tfAlbumName.setPrefWidth(350);
		tfAlbumName.setFont(Font.font("Helvetica", 14));
		
		TextField tfYearOfRelease = new TextField();
		tfYearOfRelease.setPrefWidth(350);
		tfYearOfRelease.setFont(Font.font("Helvetica", 14));
		
		//Textarea
		TextArea taDescription = new TextArea();
		taDescription.setPrefHeight(1000);
		taDescription.setPrefWidth(400);
		taDescription.setFont(Font.font("Helvetica", 14));
		
		//Choice Box
		CheckBox cbCd = new CheckBox("Cd");
		
		CheckBox cbLp = new CheckBox("Lp");
		
		//Temp AlbumBot
		
		EditorTable editorTable = new EditorTable(albumBot);
		
		//Placement
		albumBox.getChildren().addAll(albumTop, albumBot);
		albumTop.getChildren().addAll(albumLeft, albumRight);
		albumRight.getChildren().addAll(labelDescription, taDescription);
		albumLeft.getChildren().addAll(labelAlbum, albumName, tfAlbumName, albumYear, tfYearOfRelease, choiceBox);
		choiceBox.getChildren().addAll(cbCd, cbLp);
		
		//Return
		return albumBox;
	}
}
