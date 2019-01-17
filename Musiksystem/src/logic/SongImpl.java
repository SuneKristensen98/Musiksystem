package logic;

import data.SongDBCalls;
import logic.domainClasses.Song;

public class SongImpl {	
	public boolean editSong(Song song) {
		return SongDBCalls.updateSong(song);
	}
	
	public boolean deleteSong(int songId) {
		return SongDBCalls.deleteSong(songId);
	}
	
	public boolean createSong(Song song) {
		return SongDBCalls.addSong(song);
	}
	
//	public List<Song> getSongsWithAlbumId(int albumId) {
//		return SongDBCalls.getSongsWithAlbumId(albumId);
//	}
}
