package presentation;

import data.AlbumDBCalls;
import logic.domainClasses.Album;

public class Main {

	public static void main(String[] args) {
		Album album = new Album(1, "Tester1234", "cd", 2019, "21");
	
		System.out.println(AlbumDBCalls.deleteAlbum(album));
	}

}
