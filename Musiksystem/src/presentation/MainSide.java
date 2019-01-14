package presentation;


import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainSide {
	
	public void start() {
		MainSideSearch mainSideSearch = new MainSideSearch();
		MainSideAlbumButtons mainSideAlbumButtons = new MainSideAlbumButtons();

		Stage mainSideStage = new Stage();
		BorderPane mainSideBorderPane = new BorderPane();
		BorderPane topBorderPane = new BorderPane();
		
		mainSideStage.setMaximized(true);
		mainSideStage.show();
		Scene mainSideScene = new Scene(mainSideBorderPane, mainSideStage.getWidth(), mainSideStage.getHeight());
		mainSideStage.setTitle("Overblik over musik");
		mainSideStage.setScene(mainSideScene);
		
		Table table = new Table(mainSideBorderPane /*, mainSideStage.getWidth()*/);

		topBorderPane.setLeft(mainSideSearch.hBoxSearch(table));
		topBorderPane.setRight(mainSideAlbumButtons.hBoxAlbumButtons());
		
		mainSideBorderPane.setTop(topBorderPane);
		//mainSideStage.getw
	}
}
