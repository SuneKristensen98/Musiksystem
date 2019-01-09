package presentation;

import java.sql.Time;

import data.SongDBCalls;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("1");
		Song song = new Song(-1, 1, 2, 3, "21", Genre.ALTERNATIVE, "Blank", "Hej");

		
		System.out.println(SongDBCalls.addSong(song));
	}

}
