package logic;

import data.AlbumDBCalls;
import domainClasses.Album;

public class AlbumImpl {
	AlbumDBCalls albumDBCalls = new AlbumDBCalls();

	public boolean editAlbum(Album album) {
		return albumDBCalls.updateAlbum(album);
	}

	public boolean deleteAlbum(int albumId) {
		return albumDBCalls.deleteAlbum(albumId);
	}

	public int createAlbum(Album album) {
		return albumDBCalls.addAlbum(album);
	}
	public Album searchAlbumWithId(int albumId) {
		return albumDBCalls.getAlbumWithId(albumId);
	}
}
