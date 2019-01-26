package presentation.MainSide;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

		TabPane tabPane = new TabPane();
		mainSideBorderPane.setCenter(tabPane);
		Tab tab = new Tab("Alle sange");
		tab.setClosable(false);
		tab.setStyle(
				"-fx-focus-color: TRANSPARENT; -fx-border-style: solid solid hidden solid; -fx-border-color: MEDIUMPURPLE; -fx-pref-width: 100");
		tabPane.setSide(Side.BOTTOM);
		tabPane.setStyle("-fx-outer-border: TRANSPARENT; -fx-text-box-border: TRANSPARENT");
		tabPane.getTabs().addAll(tab);

		Table table = new Table(bravoMusic, tab, mainSideStage.getWidth(), mainSideAlbumButtons);

		topBorderPane.setLeft(mainSideSearch.hBoxSearch(bravoMusic, table));
		topBorderPane.setRight(mainSideAlbumButtons.hBoxAlbumButtons(bravoMusic, table, mainSideSearch));

		mainSideBorderPane.setTop(topBorderPane);
	}
}
