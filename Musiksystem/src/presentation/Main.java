package presentation;

import data.AlbumDBCalls;
import data.SongDBCalls;
import logic.domainClasses.Album;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;

public class Main {

	public static void main(String[] args) {
System.out.println("test");
		Album album = new Album(1, "Test123", "lp", 2007, "21 deluxe");
//
//		
		System.out.println(AlbumDBCalls.addAlbum(album));
	}

}
