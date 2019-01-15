package logic;

import java.util.List;

import data.AlbumDBCalls;
import data.DBCalls;
import logic.domainClasses.Album;
import logic.domainClasses.Genre;
import logic.domainClasses.TableViewInfo;


public class AlbumImpl {
	public boolean editAlbum(Album album) {
		return AlbumDBCalls.updateAlbum(album);
	}
	
	public boolean deleteAlbum(Album album) {
		return AlbumDBCalls.deleteAlbum(album);
	}
	
	public int createAlbum(Album album) {
		return AlbumDBCalls.addAlbum(album);
	}
	
	public Album searchAlbumWithId(int albumId) {
		return new AlbumDBCalls().getAlbumWithId(albumId);
	}
}
