package presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.BravoMusic;
import logic.BravoMusicFactory;

public class Main extends Application {

	Scene Main;
	
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
		
		
//		Artist artist = new Artist(1, "Søren");
//		System.out.println(DBCalls.addArtist(artist));
		
//		Conductor conductor = new Conductor(1, "Sune");
//		System.out.println(DBCalls.addConductor(conductor));
		
//		Song song = new Song(5, 2, 2, 2, "Bravo", Genre.FOLK, 100, "Johan", "Dope");
//		System.out.println(SongDBCalls.addSong(song));
		
//		Song song = new Song(-1, 2, 2, 2, "Bravo", Genre.ROCK, 100, "Johan", "Dope");
//		System.out.println(SongDBCalls.deleteSong(song));
//		SongImpl songImpl = new SongImpl();
//		System.out.println(songImpl.createSong(song));
		
//		Song song = new Song(5, 2, 2, 2, "Bravo", Genre.FOLK, 100, "Johan", "Dope");
//		System.out.println(SongDBCalls.updateSong(song));
		
//		Album album = new Album(5, "Sune's Tune", "CD", 2019, "Dope");
//		System.out.println(AlbumDBCalls.addAlbum(album));
		
//		Album album = new Album(5, "Sune's Tune", 2019, "CD", "Dope");
//		System.out.println(AlbumDBCalls.deleteAlbum(album));
		
//		Album album = new Album(5, "Sune's Tune", 2019, "CD", "Dope");
//		System.out.println(AlbumDBCalls.updateAlbum(album));
//		List<TableViewInfo> getAllMusic = DBCalls.getAllMusic();
//			for (TableViewInfo music : getAllMusic) {
//				System.out.println(music);
//			}
//	}


