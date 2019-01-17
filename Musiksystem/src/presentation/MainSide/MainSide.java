package presentation.MainSide;


import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.BravoMusic;

public class MainSide {
	 
	public void start(BravoMusic bravoMusic) {
		MainSideSearch mainSideSearch = new MainSideSearch();
		MainSideAlbumButtons mainSideAlbumButtons = new MainSideAlbumButtons();

		Stage mainSideStage = new Stage();
		BorderPane mainSideBorderPane = new BorderPane();
		BorderPane topBorderPane = new BorderPane();
		
		Scene mainSideScene = new Scene(mainSideBorderPane);
		mainSideStage.setTitle("Overblik over musik");
		mainSideStage.setMaximized(true);
		mainSideStage.setResizable(true);
		mainSideStage.setScene(mainSideScene);
		mainSideStage.show();
		
		Table table = new Table(bravoMusic, mainSideBorderPane, mainSideStage.getWidth(), mainSideAlbumButtons);

		topBorderPane.setLeft(mainSideSearch.hBoxSearch(bravoMusic, table));
		topBorderPane.setRight(mainSideAlbumButtons.hBoxAlbumButtons(bravoMusic, table));
		
		mainSideBorderPane.setTop(topBorderPane);
	}
}
