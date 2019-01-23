package logic;

import data.SongDBCalls;
import domainClasses.Song;

public class SongImpl {
	SongDBCalls songDBCalls = new SongDBCalls();

	public boolean editSong(Song song) {
		return songDBCalls.updateSong(song);
	}

	public boolean deleteSong(int songId) {
		return songDBCalls.deleteSong(songId);
	}

	public boolean createSong(Song song) {
		return songDBCalls.addSong(song);
	}

}
