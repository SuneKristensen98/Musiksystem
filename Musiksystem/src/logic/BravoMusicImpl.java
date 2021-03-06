package logic;

import java.util.List;

import domainClasses.Album;
import domainClasses.Artist;
import domainClasses.Conductor;
import domainClasses.Genre;
import domainClasses.Song;
import domainClasses.TableViewInfo;

public class BravoMusicImpl implements BravoMusic {

	Impl impl = new Impl();
	AlbumImpl albumImpl = new AlbumImpl();
	SongImpl songImpl = new SongImpl();

	@Override
	public boolean editAlbum(Album album) {
		return albumImpl.editAlbum(album);
	}

	@Override
	public boolean deleteAlbum(int albumId) {
		return albumImpl.deleteAlbum(albumId);
	}

	@Override
	public int createAlbum(Album album) {
		return albumImpl.createAlbum(album);
	}

	@Override
	public Album searchAlbumWithId(int albumId) {
		return albumImpl.searchAlbumWithId(albumId);
	}
	
	@Override
	public boolean editSong(Song song) {
		return songImpl.editSong(song);
	}

	@Override
	public boolean deleteSong(int songId) {
		return songImpl.deleteSong(songId);
	}

	@Override
	public boolean createSong(Song song) {
		return songImpl.createSong(song);
	}

	@Override
	public int createArtist(Artist artist) {
		return impl.createArtist(artist);
	}

	@Override
	public int createConductor(Conductor conductor) {
		return impl.createConductor(conductor);
	}

	@Override
	public List<TableViewInfo> searchMusic(String whereClause, Genre genreParameter, Boolean lp, Boolean cd,
			int albumMaybeId) {
		return impl.searchMusic(whereClause, genreParameter, lp, cd, albumMaybeId);
	}

	@Override
	public int searchArtist(String findAristName) {
		return impl.searchArtist(findAristName);
	}

	@Override
	public int searchConductor(String findConductorName) {
		return impl.searchConductor(findConductorName);
	}

}
