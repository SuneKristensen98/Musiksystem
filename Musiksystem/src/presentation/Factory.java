package presentation;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
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
	
	public Button buttonFactory(String text, int width, int height) {
		Button template = new Button(text);
		//template.setPrefSize(width, height);

		return template;
	}
}
