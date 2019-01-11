package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Table {

	 Scene Table;
	
	public void start() {
		
		Stage tableStage = new Stage();
		Label label = new Label("test123");
		Button btnOpret = new Button("Opret");
		btnOpret.setPrefSize(100, 20);
		btnOpret.setOnAction(e -> deleteaction());
		BorderPane borderpane = new BorderPane();
		borderpane.setCenter(btnOpret);
		tableStage.setTitle("table");
		Table = new Scene(borderpane, 1600, 900);
		tableStage.setScene(Table);
		tableStage.setMaximized(true);
		tableStage.show();
		System.out.println("test1");
	
}
	
private void deleteaction() {
		
		Editor editor = new Editor();
		editor.start();
		
	}
}
