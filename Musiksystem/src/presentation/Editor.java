package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Editor {

	Scene Editor;

	public void start() {

		Label labelSongTitle = new Label("Sang");
		VBox vboxSong = new VBox(5);
		vboxSong.setPrefHeight(1000);
		vboxSong.setPrefWidth(1000);
		vboxSong.setPadding(new Insets(0, 35, 0, 35));
		vboxSong.setAlignment(Pos.CENTER);
		BorderPane borderpane = new BorderPane();
		VBox vboxLeft = new VBox(20);
		vboxLeft.setPadding(new Insets(25, 25, 25, 25));
		vboxLeft.setAlignment(Pos.TOP_CENTER);
		VBox vboxRight = new VBox(20);
		vboxRight.setPadding(new Insets(25, 25, 25, 25));
		vboxRight.setAlignment(Pos.CENTER);
		HBox hboxSongBtn = new HBox(200);
		hboxSongBtn.setAlignment(Pos.CENTER);
		hboxSongBtn.setPadding(new Insets(25, 25, 25, 25));

		HBox hboxAlbumTop = new HBox(20);
		hboxAlbumTop.setAlignment(Pos.TOP_CENTER);
		VBox vboxAlbumButtom = new VBox(20);
		vboxAlbumButtom.minHeight(300);
		vboxAlbumButtom.minWidth(100);
		VBox vboxAlbumRight = new VBox(20);
		vboxAlbumRight.setPrefSize(450, 500);
		vboxAlbumRight.setAlignment(Pos.TOP_CENTER);
		vboxAlbumRight.setPadding(new Insets(5, 5, 5, 5));
		VBox vboxAlbumLeft = new VBox(20);
		vboxAlbumLeft.setPrefSize(450, 500);
		vboxAlbumLeft.setAlignment(Pos.BASELINE_CENTER);
		vboxAlbumLeft.setPadding(new Insets(5, 5, 5, 5));
		HBox hboxAlbumChoice = new HBox(20);
		hboxAlbumChoice.setAlignment(Pos.BASELINE_CENTER);
		

		HBox hboxbottom = new HBox(20);
		vboxLeft.setPrefWidth(800);
		hboxbottom.setPrefHeight(75);
		hboxbottom.setAlignment(Pos.CENTER_LEFT);
		hboxbottom.setPadding(new Insets(25, 25, 25, 25));

		Stage editor = new Stage();
		editor.initModality(Modality.APPLICATION_MODAL);
		editor.setTitle("Editor");
		Editor = new Scene(borderpane, 1600, 900);

		// Song
		vboxRight.setBackground(Background.EMPTY);
		String style1 = "-fx-background-color: rgba(255, 0, 0, 0.5);";
		vboxRight.setStyle(style1);
		vboxLeft.setBackground(Background.EMPTY);
		String style2 = "-fx-background-color: rgba(0, 255, 0, 0.5);";
		vboxLeft.setStyle(style2);
		hboxbottom.setBackground(Background.EMPTY);
		String style3 = "-fx-background-color: rgba(0, 0, 255, 0.5);";
		hboxbottom.setStyle(style3);
		vboxSong.setBackground(Background.EMPTY);
		String style4 = "-fx-background-color: rgba(255, 0, 255, 0.5);";
		vboxSong.setStyle(style4);
		
		
		// Album
		
		vboxAlbumButtom.setBackground(Background.EMPTY);
		vboxAlbumButtom.setStyle(style1);
		
		vboxAlbumRight.setBackground(Background.EMPTY);
		vboxAlbumRight.setStyle(style2);
		
		vboxAlbumLeft.setBackground(Background.EMPTY);
		vboxAlbumLeft.setStyle(style1);

		Label labelGenre = new Label("Genre");
		TextField tfGenre = new TextField();
		tfGenre.setMaxWidth(1000);
		tfGenre.setFont(Font.font("Helvetica", 14));
		labelGenre.setFont(Font.font("Helvetica", 16));

		Label labelKunstner = new Label("Kunstner");
		labelKunstner.setPadding(new Insets(35, 0, 0, 0));
		TextField tfKunstner = new TextField();
		tfKunstner.setMaxWidth(1000);
		tfKunstner.setFont(Font.font("Helvetica", 14));
		labelKunstner.setFont(Font.font("Helvetica", 16));

		Label labelSangTitle = new Label("Sang Title");
		labelSangTitle.setPadding(new Insets(35, 0, 0, 0));
		TextField tfSangTitle = new TextField();
		tfSangTitle.setMaxWidth(1000);
		tfSangTitle.setFont(Font.font("Helvetica", 14));
		labelSangTitle.setFont(Font.font("Helvetica", 16));

		Label labelTid = new Label("Tid");
		labelTid.setPadding(new Insets(35, 0, 0, 0));
		TextField tfTid = new TextField();
		tfTid.setMaxWidth(1000);
		tfTid.setFont(Font.font("Helvetica", 14));
		labelTid.setFont(Font.font("Helvetica", 16));

		Label labelSangSkriver = new Label("Sang Skriver");
		labelSangSkriver.setPadding(new Insets(35, 0, 0, 0));
		TextField tfSangSkriver = new TextField();
		tfSangSkriver.setMaxWidth(1000);
		tfSangSkriver.setFont(Font.font("Helvetica", 14));
		labelSangSkriver.setFont(Font.font("Helvetica", 16));

		Label labelNote = new Label("Note");
		labelNote.setPadding(new Insets(35, 0, 0, 0));
		TextField tfNote = new TextField();
		tfNote.setMaxWidth(1000);
		tfNote.setFont(Font.font("Helvetica", 14));
		labelNote.setFont(Font.font("Helvetica", 16));

		Label labelDirigent = new Label("Dirigent");
		labelDirigent.setPadding(new Insets(35, 0, 0, 0));
		TextField tfDirigent = new TextField();
		tfDirigent.setMaxWidth(1000);
		tfDirigent.setFont(Font.font("Helvetica", 14));
		labelDirigent.setFont(Font.font("Helvetica", 16));

		// Album
		Label labelDescription = new Label("Beskrivelse");
		labelDescription.setPadding(new Insets(35, 0, 0, 0));
		TextArea taDescription = new TextArea();
		taDescription.setPrefHeight(1000);
		taDescription.setFont(Font.font("Helvetica", 14));
		labelDescription.setFont(Font.font("Helvetica", 16));

		// vboxAlbumLeft
		Label labelAlbum = new Label("Album");
		labelDescription.setPadding(new Insets(35, 0, 0, 0));
		TextField tfAlbumName = new TextField();
		TextField tfYearOfRelease = new TextField();
		tfAlbumName.setMaxWidth(1000);
		tfAlbumName.setFont(Font.font("Helvetica", 14));
		tfYearOfRelease.setMaxWidth(1000);
		tfYearOfRelease.setFont(Font.font("Helvetica", 14));
		labelAlbum.setFont(Font.font("Helvetica", 16));

		CheckBox cbCd = new CheckBox("Cd");
		CheckBox cbLp = new CheckBox("Lp");

		Button btnAdd = new Button("Tilføj");
		btnAdd.setPrefSize(100, 20);
		Button btnDelete = new Button("Slet");
		btnDelete.setPrefSize(100, 20);
		Button btnEdit = new Button("Redigerer");
		btnEdit.setPrefSize(100, 20);

		Button btnAlbumDelete = new Button("Slet");
		btnAlbumDelete.setPrefSize(100, 20);
		Button btnAlbumCancel = new Button("Fortryd");
		btnAlbumCancel.setPrefSize(100, 20);
		Button btnAlbumSave = new Button("Gem");
		btnAlbumSave.setPrefSize(100, 20);

		// Album
		vboxRight.getChildren().addAll(labelSongTitle, vboxSong);
		vboxLeft.getChildren().addAll(hboxAlbumTop, vboxAlbumButtom);
		hboxAlbumTop.getChildren().addAll(vboxAlbumLeft, vboxAlbumRight);
		vboxAlbumRight.getChildren().addAll(labelDescription, taDescription);
		vboxAlbumLeft.getChildren().addAll(labelAlbum, tfAlbumName, tfYearOfRelease, hboxAlbumChoice);
		hboxAlbumChoice.getChildren().addAll(cbCd, cbLp);
		vboxAlbumButtom.getChildren().addAll(new Label ("test123"));

		// Song
		hboxbottom.getChildren().addAll(btnAlbumCancel, btnAlbumDelete, btnAlbumSave);
		hboxSongBtn.getChildren().addAll(btnDelete, btnEdit, btnAdd);
		vboxSong.getChildren().addAll(labelGenre, tfGenre, labelKunstner, tfKunstner, labelSangTitle, tfSangTitle,
				labelTid, tfTid, labelSangSkriver, tfSangSkriver, labelNote, tfNote, labelDirigent, tfDirigent,
				hboxSongBtn);

		borderpane.setCenter(vboxRight);
		borderpane.setLeft(vboxLeft);
		borderpane.setBottom(hboxbottom);
		editor.setScene(Editor);
		editor.showAndWait();

	}

}
