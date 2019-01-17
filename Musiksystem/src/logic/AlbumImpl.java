package logic;

import data.AlbumDBCalls;
import logic.domainClasses.Album;


public class AlbumImpl {
	public boolean editAlbum(Album album) {
		return AlbumDBCalls.updateAlbum(album);
	}
	
	public boolean deleteAlbum(int albumId) {
		return AlbumDBCalls.deleteAlbum(albumId);
	}
	
	public int createAlbum(Album album) {
		return AlbumDBCalls.addAlbum(album);
	}
	
	public Album searchAlbumWithId(int albumId) {
		return new AlbumDBCalls().getAlbumWithId(albumId);
	}
}
