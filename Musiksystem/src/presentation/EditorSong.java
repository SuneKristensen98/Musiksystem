package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EditorSong {

	public VBox editorSong() {
		
		//Setup
		VBox songBox = new VBox();
		songBox.setBackground(Background.EMPTY);
		String style = "-fx-background-color: rgba(255, 0, 0, 0.5);";
		songBox.setStyle(style);
		songBox.setPadding(new Insets(25, 25 ,25 ,25));
		songBox.setAlignment(Pos.TOP_CENTER);
		songBox.setPrefWidth(400);
		
		//Label
		Label labelSongTitle = new Label("Sang");
		labelSongTitle.setFont(Font.font("Helvetica", 20));
		
		Label labelGenre = new Label("Genre:");
		labelGenre.setFont(Font.font("Helvetica", 16));
		labelGenre.setPadding(new Insets(50, 0, 0, 0));
		
		Label labelKunstner = new Label("Kunstner:");
		labelKunstner.setPadding(new Insets(25, 0, 0, 0));
		labelKunstner.setFont(Font.font("Helvetica", 16));
		
		Label labelSangTitle = new Label("Sang Title:");
		labelSangTitle.setPadding(new Insets(25, 0, 0, 0));
		labelSangTitle.setFont(Font.font("Helvetica", 16));
		
		Label labelTid = new Label("Tid:");
		labelTid.setPadding(new Insets(25, 0, 0, 0));
		labelTid.setFont(Font.font("Helvetica", 16));
		
		Label labelSangSkriver = new Label("Sang Skriver:");
		labelSangSkriver.setPadding(new Insets(25, 0, 0, 0));
		labelSangSkriver.setFont(Font.font("Helvetica", 16));
		
		Label labelNote = new Label("Note:");
		labelNote.setPadding(new Insets(25, 0, 0, 0));
		labelNote.setFont(Font.font("Helvetica", 16));
		
		Label labelDirigent = new Label("Dirigent:");
		labelDirigent.setPadding(new Insets(25, 0, 0, 0));
		labelDirigent.setFont(Font.font("Helvetica", 16));
			
		//Textfield
		TextField tfGenre = new TextField();
		tfGenre.setMaxWidth(1000);
		tfGenre.setFont(Font.font("Helvetica", 14));
		
		TextField tfKunstner = new TextField();
		tfKunstner.setMaxWidth(1000);
		tfKunstner.setFont(Font.font("Helvetica", 14));
		
		TextField tfSangTitle = new TextField();
		tfSangTitle.setMaxWidth(1000);
		tfSangTitle.setFont(Font.font("Helvetica", 14));
		
		TextField tfTid = new TextField();
		tfTid.setMaxWidth(1000);
		tfTid.setFont(Font.font("Helvetica", 14));
		
		TextField tfSangSkriver = new TextField();
		tfSangSkriver.setMaxWidth(1000);
		tfSangSkriver.setFont(Font.font("Helvetica", 14));
		
		TextField tfNote = new TextField();
		tfNote.setMaxWidth(1000);
		tfNote.setFont(Font.font("Helvetica", 14));
		
		TextField tfDirigent = new TextField();
		tfDirigent.setMaxWidth(1000);
		tfDirigent.setFont(Font.font("Helvetica", 14));
		
		//Buttons
		HBox songBoxBtn = new HBox(25);
		songBoxBtn.setPadding(new Insets(50, 0, 0, 0));
		songBoxBtn.setAlignment(Pos.CENTER);
		
		Button btnAdd = new Button("Tilføj");
		btnAdd.setPrefSize(100, 20);
		Button btnDelete = new Button("Slet");
		btnDelete.setPrefSize(100, 20);
		Button btnEdit = new Button("Redigerer");
		btnEdit.setPrefSize(100, 20);
		
		//Placement
		songBox.getChildren().addAll(labelSongTitle, labelGenre, tfGenre, labelKunstner, tfKunstner, labelSangTitle, tfSangTitle,
				labelTid, tfTid, labelSangSkriver, tfSangSkriver, labelNote, tfNote, labelDirigent, tfDirigent, songBoxBtn);
		
		songBoxBtn.getChildren().addAll(btnAdd, btnDelete, btnEdit);
		
		//Return
		return songBox;
	}
	
}

