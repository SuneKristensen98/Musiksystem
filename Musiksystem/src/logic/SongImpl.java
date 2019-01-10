package logic;

import data.SongDBCalls;
import logic.domainClasses.Song;

public class SongImpl {
	//SongDBCalls songDBCalls = new SongDBCalls();
	
	public boolean createSong(Song song) {
		return SongDBCalls.addSong(song);
	}
	
	public boolean editSong(Song song) {
		return SongDBCalls.updateSong(song);
	}
	
	public boolean deleteSong(Song song) {
		return SongDBCalls.deleteSong(song);
	}
	
}
