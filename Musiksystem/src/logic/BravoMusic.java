package logic;

import java.util.List;

import domainClasses.Album;
import domainClasses.Artist;
import domainClasses.Conductor;
import domainClasses.Genre;
import domainClasses.Song;
import domainClasses.TableViewInfo;

public interface BravoMusic {

	public boolean editAlbum(Album album);

	public boolean deleteAlbum(int albumId);

	public int createAlbum(Album album);
	
	public Album searchAlbumWithId(int albumId);

	public boolean editSong(Song song);

	public boolean deleteSong(int songId);

	public boolean createSong(Song song);

	public int createArtist(Artist artist);

	public int createConductor(Conductor conductor);

	public List<TableViewInfo> searchMusic(String whereClause, Genre genreParameter, Boolean lp, Boolean cd,
			int albumMaybeId);

	public int searchArtist(String findArtistName);

	public int searchConductor(String findConductorName);
	

}
