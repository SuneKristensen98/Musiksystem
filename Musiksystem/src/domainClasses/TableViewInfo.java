package domainClasses;

public class TableViewInfo {

	private String songName;
	private int albumId;
	private int songId;
	private String albumName;
	private int yearOfRelease;
	private String type, albumDescription;
	private String artistName;
	private String conductorName;
	private Genre genre;
	private int time;
	private String songwriter;
	private String songNote;
	private String conductorWithArtist;
	
	public TableViewInfo(String songName, int albumId, int songId, String albumName, int yearOfRelease, String type, String albumDescription,
			String artistName, String conductorName, Genre genre, int time, String songwriter, String songNote, String conductorWithArtist) {
		super();
		this.songName = songName;
		this.albumId = albumId;
		this.songId = songId;
		this.albumName = albumName;
		this.yearOfRelease = yearOfRelease;
		this.type = type;
		this.albumDescription = albumDescription;
		this.artistName = artistName;
		this.conductorName = conductorName;
		this.genre = genre;
		this.time = time;
		this.songwriter = songwriter;
		this.songNote = songNote;
		this.conductorWithArtist = conductorWithArtist;
	}
 
	@Override
	public String toString() {
		return "TableViewInfo [songName=" + songName + ", albumName=" + albumName + ", yearOfRelease=" + yearOfRelease
				+ ", type=" + type + ", albumDescription=" + albumDescription + ", artistName=" + artistName
				+ ", conductorName=" + conductorName + ", genre=" + genre + ", time=" + time + ", songwriter="
				+ songwriter + ", songNote=" + songNote + "]";
	}

	public String getSongName() {
		return songName;
	}
	
	public int getAlbumId() {
		return albumId;
	}
	
	public int getSongId() {
		return songId;
	}


	public String getAlbumName() {
		return albumName;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	} 

	public String getType() {
		return type;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getConductorName() {
		return conductorName;
	}

	public Genre getGenre() {
		return genre;
	}

	public int getTime() {
		return time;
	}

	public String getSongwriter() {
		return songwriter;
	}

	public String getSongNote() {
		return songNote;
	}
	
	public String getConductorWithArtist() {
		return conductorWithArtist;
	}
}
