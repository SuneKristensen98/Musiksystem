package logic.domainClasses;

import java.sql.Time;

public class Song {
	private int songId;
	private int albumId;
	private int artistId;
	private int conductorId;
	private String songName;
	private Genre genre;
	private Time time;
	private String songwriter;
	private String songNote;
	
	public Song(int songId, int albumId, int artistId, int conductorId, String songName, Genre genre, Time time, String songwriter, String songNote) {
		this.songId = songId;
		this.albumId = albumId;
		this.artistId = artistId;
		this.conductorId = conductorId;
		this.songName = songName;
		this.genre = genre;
		this.time = time;
		this.songwriter = songwriter;
		this.songNote = songNote;
	}
	
	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getSongId() {
		return songId;
	}

	public int getArtistId() {
		return artistId;
	}

	public int getConductorId() {
		return conductorId;
	}

	public String getSongName() {
		return songName;
	}

	public Genre getGenre() {
		return genre;
	}

	public Time getTime() {
		return time;
	}

	public String getSongwriter() {
		return songwriter;
	}

	public String getSongNote() {
		return songNote;
	}

	@Override
	public String toString() {
		return "Song [songId=" + songId + ", albumId=" + albumId + ", artistId=" + artistId + ", conductorId="
				+ conductorId + ", songName=" + songName + ", genre=" + genre + ", time=" + time + ", songwriter="
				+ songwriter + ", songNote=" + songNote + "]";
	}
}
