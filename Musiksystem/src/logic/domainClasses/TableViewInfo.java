package logic.domainClasses;

public class TableViewInfo {

	private String songName;
	private String albumName;
	private int yearOfRelease;
	private String type, albumDescription;
	private String artistName;
	private String conductorName;
	private String genre;
	private int time;
	private String songwriter;
	private String songNote;
	
	public TableViewInfo(String songName, String albumName, int yearOfRelease, String type, String albumDescription,
			String artistName, String conductorName, String genre, int time, String songwriter, String songNote) {
		super();
		this.songName = songName;
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

	public String getGenre() {
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
}
