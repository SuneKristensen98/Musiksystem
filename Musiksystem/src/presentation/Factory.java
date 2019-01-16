package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.domainClasses.TableViewInfo;

public class Factory {
	public TableColumn<TableViewInfo, String> columnFactoryString(String nameInClass, String columnName, double columnWidth) {		
		TableColumn<TableViewInfo, String> template = new TableColumn<TableViewInfo, String>(columnName);

		template.setStyle("-fx-alignment: CENTER_LEFT");
		template.setCellValueFactory(new PropertyValueFactory<TableViewInfo, String>(nameInClass));
		
		template.setMaxWidth(1f * Integer.MAX_VALUE * columnWidth);
		return template;
	}
	
	public TableColumn<TableViewInfo, Integer> columnFactoryInt(String nameInClass, String columnName, double columnWidth) {	
		TableColumn<TableViewInfo, Integer> template = new TableColumn<TableViewInfo, Integer>(columnName);

		template.setStyle("-fx-alignment: CENTER_LEFT");
		template.setCellValueFactory(new PropertyValueFactory<TableViewInfo, Integer>(nameInClass));
		
		template.setMaxWidth(1f * Integer.MAX_VALUE * columnWidth);
		return template;
	}
	
	public Button buttonFactory(String text, int width, boolean disable) {
		Button template = new Button(text);
		template.setPrefWidth(width);
		template.setDisable(disable);
		return template;
	}
	
	public TextField textFieldFactory(String text, int width, int fontSize) {
		TextField template = new TextField();
		template.setPromptText(text);
		template.setPrefWidth(width);
		template.setFont(Font.font(fontSize));

		return template; 
	}
	
	public CheckBox checkBoxFactory(String text) {
		CheckBox template = new CheckBox();
		template.setText(text);
		template.setSelected(true);
		template.setPadding(new Insets(3.5, 0, 0, 0));
		return template;
	}
	
	public Label labelFactory(String text, int t, int r, int b, int l, int fontSize) {
		Label template = new Label(text);
		template.setPadding(new Insets(t, r, b, l));
		template.setFont(Font.font(/*"Helvetica", */fontSize));
		return template;
	}
	
	public VBox vBoxFactory(int t, int r, int b, int l, Pos alignment/*, int height*/) {
		VBox template = new VBox();
		template.setPadding(new Insets(t, r, b, l));
		template.setAlignment(alignment);
		//template.setMinHeight(height);
		return template;
	}
	
	public HBox hBoxFactory(int space, int t, int r, int b, int l, Pos alignment) {
		HBox template = new HBox(space);
		template.setPadding(new Insets(t, r, b, l));
		template.setAlignment(alignment);
		return template;
	}
}