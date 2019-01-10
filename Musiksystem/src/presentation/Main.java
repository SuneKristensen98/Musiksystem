package presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	Scene Main;
	
	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		BorderPane borderpane = new BorderPane();
		Label label = new Label("test");
		
		Button btnOpret = new Button("Opret");
		
		Stage editor = new Stage();
		btnOpret.setPrefSize(100, 20);
		btnOpret.setOnAction(e -> deleteaction());
		
		
		borderpane.setCenter(btnOpret);
		Main = new Scene(borderpane, 1600, 900);
		window.setScene(Main);
		window.show();
		
// test
	}
	

	private void deleteaction() {
		
		Editor editor = new Editor();
		editor.start();
		
	}
	
}
		
		
//		Artist artist = new Artist(1, "Johan");
//		System.out.println(DBCalls.addArtist(artist));
		
//		Song song = new Conductor(1, "Silke");
//		System.out.println(DBCalls.addConductor(conductor));
		
//		Song song = new Song(5, 2, 2, 2, "Bravo", Genre.FOLK, 100, "Johan", "Dope");
//		System.out.println(SongDBCalls.addSong(song));
		
//		Song song = new Song(-1, 2, 2, 2, "Bravo", Genre.ROCK, 100, "Johan", "Dope");
//		System.out.println(SongDBCalls.deleteSong(song));
//		SongImpl songImpl = new SongImpl();
//		System.out.println(songImpl.createSong(song));
		
//		Song song = new Song(5, 2, 2, 2, "Bravo", Genre.FOLK, 100, "Johan", "Dope");
//		System.out.println(SongDBCalls.updateSong(song));
		
//		Album album = new Album(5, "Sune's Tune", 2019, "CD", "Dope");
//		System.out.println(AlbumDBCalls.addAlbum(album));
		
//		Album album = new Album(5, "Sune's Tune", 2019, "CD", "Dope");
//		System.out.println(AlbumDBCalls.deleteAlbum(album));
		
//		Album album = new Album(5, "Sune's Tune", 2019, "CD", "Dope");
//		System.out.println(AlbumDBCalls.updateAlbum(album));
	

