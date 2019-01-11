package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
		HBox hboxLeft = new HBox(20);
		VBox vboxRight = new VBox(20);
		vboxRight.setPadding(new Insets(25, 25, 25, 25));
		vboxRight.setAlignment(Pos.CENTER);
		HBox hboxSongBtn = new HBox(20);
		VBox vboxbottom = new VBox(20);
		hboxLeft.setPrefWidth(800);
		vboxbottom.setPrefHeight(75);
		Stage editor = new Stage();
		editor.initModality(Modality.APPLICATION_MODAL);
		editor.setTitle("Editor");
		Editor = new Scene(borderpane, 1600, 900);

		vboxRight.setBackground(Background.EMPTY);
		String style1 = "-fx-background-color: rgba(255, 0, 0, 0.5);";
		vboxRight.setStyle(style1);

		hboxLeft.setBackground(Background.EMPTY);
		String style2 = "-fx-background-color: rgba(0, 255, 0, 0.5);";
		hboxLeft.setStyle(style2);

		vboxbottom.setBackground(Background.EMPTY);
		String style3 = "-fx-background-color: rgba(0, 0, 255, 0.5);";
		vboxbottom.setStyle(style3);

		vboxSong.setBackground(Background.EMPTY);
		String style4 = "-fx-background-color: rgba(255, 0, 255, 0.5);";
		vboxSong.setStyle(style4);

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

		vboxRight.getChildren().addAll(labelSongTitle, vboxSong);
		hboxLeft.getChildren().addAll(new Label("ST Verification Studio is loading.."));
		hboxSongBtn.getChildren().addAll(new Label("ST Verification Studio is loading.."));
		vboxbottom.getChildren().addAll(new Label("ST Verification Studio is loading.."));
		vboxSong.getChildren().addAll(labelGenre, tfGenre, labelKunstner, tfKunstner, labelSangTitle, tfSangTitle,
				labelTid, tfTid, labelSangSkriver, tfSangSkriver, labelNote, tfNote, labelDirigent, tfDirigent, hboxSongBtn);

		borderpane.setCenter(vboxRight);
		borderpane.setLeft(hboxLeft);
		borderpane.setBottom(vboxbottom);
		editor.setScene(Editor);
		editor.showAndWait();

	}

}
