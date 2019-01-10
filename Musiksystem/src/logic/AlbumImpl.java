package logic;

import data.AlbumDBCalls;
import logic.domainClasses.Album;
import logic.domainClasses.Song;

public class AlbumImpl {

	//AlbumDBCalls AlbumDBCalls = new AlbumDBCalls();
	
		public boolean createAlbum(Album album) {
			return AlbumDBCalls.addAlbum(album);
		}
		
		public boolean editAlbum(Album album) {
			return AlbumDBCalls.updateAlbum(album);
		}
		
		public boolean deleteAlbum(Album album) {
			return AlbumDBCalls.deleteAlbum(album);
		}
	
}
