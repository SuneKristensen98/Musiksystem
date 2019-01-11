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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Editor {

	Scene Editor;
	TextField tf;

	public void start() {

		Label labelSongTitle = new Label("Sang");
		VBox vboxSong = new VBox(20);
		vboxSong.setPrefHeight(1000);
		vboxSong.setPrefWidth(1000);
		vboxSong.setPadding(new Insets(10, 35, 10, 35));
		vboxSong.setAlignment(Pos.CENTER);
		BorderPane borderpane = new BorderPane();
		HBox hboxLeft = new HBox(20);
		VBox vboxRight = new VBox(20);
		vboxRight.setPadding(new Insets(25, 25, 25, 25));
		vboxRight.setAlignment(Pos.CENTER);
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
		
		Label labelKunstner = new Label("Kunstner");
		TextField tfKunstner = new TextField();
		tfKunstner.setMaxWidth(1000);
		
		Label labelSangTitle = new Label("Sang Title");
		TextField tfSangTitle = new TextField();
		tfSangTitle.setMaxWidth(1000);
		
		Label labelTid = new Label("Tid");
		TextField tfTid = new TextField();
		tfTid.setMaxWidth(1000);
		
		Label labelSangSkriver = new Label("Sang Skriver");
		TextField tfSangSkriver = new TextField();
		tfSangSkriver.setMaxWidth(1000);
		
		Label labelNote = new Label("Note");
		TextField tfNote = new TextField();
		tfNote.setMaxWidth(1000);
		
		Label labelDirigent = new Label("Dirigent");
		TextField tfDirigent = new TextField();
		tfDirigent.setMaxWidth(1000);
		
		

		vboxRight.getChildren().addAll(labelSongTitle, vboxSong);
		hboxLeft.getChildren().addAll(new Label("ST Verification Studio is loading.."));
		vboxbottom.getChildren().addAll(new Label("ST Verification Studio is loading.."));
		vboxSong.getChildren().addAll(labelGenre, tfGenre, labelKunstner, tfKunstner, labelSangTitle, tfSangTitle, labelTid, tfTid, labelSangSkriver, tfSangSkriver, labelNote, tfNote, labelDirigent, tfDirigent);
		

		borderpane.setCenter(vboxRight);
		borderpane.setLeft(hboxLeft);
		borderpane.setBottom(vboxbottom);
		editor.setScene(Editor);
		editor.showAndWait();

	}

}
