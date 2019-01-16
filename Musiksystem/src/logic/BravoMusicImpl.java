package logic;

import java.util.List;
import logic.domainClasses.Album;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.Song;
import logic.domainClasses.TableViewInfo;

public class BravoMusicImpl implements BravoMusic {

	Impl impl = new Impl();
	AlbumImpl albumImpl = new AlbumImpl();
	SongImpl songImpl = new SongImpl();
	
	@Override
	public boolean editAlbum(Album album) {
		return albumImpl.editAlbum(album);
	}
	
	@Override
	public boolean deleteAlbum(Album album) {
		return albumImpl.deleteAlbum(album);
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
	public boolean deleteSong(Song song) {
		return songImpl.deleteSong(song);
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
	public List<TableViewInfo> searchMusic(String whereClause, Genre genreParameter, Boolean lp, Boolean cd, int albumMaybeId) {
		return impl.searchMusic(whereClause, genreParameter, lp, cd, albumMaybeId);
	}
}
