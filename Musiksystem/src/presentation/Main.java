package presentation;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.BravoMusic;
import logic.BravoMusicFactory;
import presentation.MainSide.MainSide;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BravoMusic bravoMusic = new BravoMusicFactory().makeBravoMusic();
		MainSide mainSide = new MainSide();
		mainSide.start(bravoMusic);

	}
}