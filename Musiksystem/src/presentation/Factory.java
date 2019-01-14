package presentation;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
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
	
	public Button buttonFactory(String text, int width) {
		Button template = new Button(text);
		template.setPrefWidth(width);
		return template;
	}
	
	public TextField textFieldFactory(String text, int width) {
		TextField template = new TextField();
		template.setPromptText(text);
		template.setPrefWidth(width);
		return template; 
	}
	
	public CheckBox chechBoxFactory(String text) {
		CheckBox template = new CheckBox();
		template.setText(text);
		template.setSelected(true);
		template.setPadding(new Insets(3.5, 0, 0, 0));
		return template;
	}
	
}
