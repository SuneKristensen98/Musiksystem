package presentation;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import logic.domainClasses.TableViewInfo;

public class Factory {
	public TableColumn<TableViewInfo, String> columnFactory(String nameInClass, String columnName, double columnWidth) {	
		
		TableColumn<TableViewInfo, String> template = new TableColumn<TableViewInfo, String>(columnName);

		template.setStyle("-fx-alignment: CENTER_LEFT");
		template.setCellValueFactory(new PropertyValueFactory<TableViewInfo, String>(nameInClass));

		
		template.setMaxWidth(1f * Integer.MAX_VALUE * columnWidth);
		return template;
	}
}
