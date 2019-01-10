package logic;

import logic.domainClasses.Album;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Song;

public interface BravoMusic {

	public boolean createAlbum(Album album);

	public boolean editAlbum(Album album);

	public boolean deleteAlbum(int albumId);

	
	public boolean createSong(Song song);

	public boolean editSong(Song song);

	public boolean deleteSong(int songId);
	
	public boolean createArtist(Artist artist);
	
	public boolean createConductor(Conductor conductor);
	
	public boolean searchMusic(String whereClause);
	


	
	
}
