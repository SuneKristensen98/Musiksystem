package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Table {

	 Scene Table;
	
	public void start() {
		//TableViewBla tableViewBla = new TableViewBla();
		MainSideTop mainSideTop = new MainSideTop();
		MainSideBottom mainSideBottom = new MainSideBottom();
		
		Stage tableStage = new Stage();
		Label label = new Label("test123");
//		Button btnOpret = new Button("Opret");
//		btnOpret.setPrefSize(100, 20);
//		btnOpret.setOnAction(e -> deleteaction());
		BorderPane borderpane = new BorderPane();
		TableViewBla tableViewBla = new TableViewBla(borderpane);
		BorderPane test = new BorderPane();
		test.setLeft(mainSideTop.hBoxTop(tableViewBla));
		test.setRight(mainSideBottom.hBoxBottom());
//		HBox hBoxLeft = new HBox();
//		HBox hBoxRight = new HBox();
//		hBoxRight.setAlignment(Pos.BASELINE_RIGHT);
//		HBox hBox = new HBox();
		
//		hBoxLeft.getChildren().add(mainSideTop.hBoxTop());
//		hBoxRight.getChildren().add(mainSideBottom.hBoxBottom());
//		hBox.getChildren().addAll(hBoxLeft, hBoxRight);
		
		//GridPane hBox = new GridPane();
//		hBox.getChildren().addAll(mainSideTop.hBoxTop(), mainSideBottom.hBoxBottom());
		
//		hBox.add(mainSideTop.hBoxTop(), 0, 0);
//		hBox.add(mainSideBottom.hBoxBottom(), 1, 0);
		
		borderpane.setTop(test);
		//tableViewBla
		//borderpane.setBottom(mainSideBottom.hBoxBottom());
		tableStage.setTitle("table");
		Table = new Scene(borderpane, 1600, 900);
		tableStage.setScene(Table);
		tableStage.setMaximized(true);
		tableStage.show();
		System.out.println("test1");
	
}
	
//private void deleteaction() {
//		
//		Editor editor = new Editor();
//		editor.start();
//		
//	}
}
