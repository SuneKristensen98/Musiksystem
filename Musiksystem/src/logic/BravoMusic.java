package logic;

import logic.domainClasses.Album;
import logic.domainClasses.Song;

public interface BravoMusic {

	public boolean addAlbum(Album album);

	public boolean updateAlbum(Album album);

	public boolean deleteAlbum(int albumId);

	public boolean addSong(Song song);

	public boolean updateSong(Song song);

	public boolean deleteSong(int songId);

}
