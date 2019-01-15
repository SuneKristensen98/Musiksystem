package logic;

import java.util.List;
import logic.domainClasses.Album;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;
import logic.domainClasses.TableViewInfo;

public interface BravoMusic {
	
	public boolean editAlbum(Album album);
	
	public boolean deleteAlbum(Album album);

	public int createAlbum(Album album);

	
	public boolean editSong(Song song);
	
	public boolean deleteSong(Song song);
	
	public boolean createSong(Song song);
	
	
	public int createArtist(Artist artist);
	
	public int createConductor(Conductor conductor);
	
	public List<TableViewInfo> searchMusic(String whereClause, Genre genreParameter, Boolean lp, Boolean cd);

	public Album searchAlbumWithId(int albumId);
}
