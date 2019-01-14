package logic;

import data.SongDBCalls;
import logic.domainClasses.Song;

public class SongImpl {	
	public boolean editSong(Song song) {
		return SongDBCalls.updateSong(song);
	}
	
	public boolean deleteSong(Song song) {
		return SongDBCalls.deleteSong(song);
	}
	
	public boolean createSong(Song song) {
		return SongDBCalls.addSong(song);
	}
	
}
