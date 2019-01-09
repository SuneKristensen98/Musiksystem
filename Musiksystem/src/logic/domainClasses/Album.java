package logic.domainClasses;

public class Album {
	
	private int albumId;
	private int yearOfRelease;
	private String albumName, type, albumDescription;
	
	public Album(int albumId, String albumName, String type, int yearOfRelease, String albumDescription) {
		this.albumId = albumId;
		this.albumName = albumName;
		this.type = type;
		this.yearOfRelease = yearOfRelease;
		this.albumDescription = albumDescription;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public String getAlbumName() {
		return albumName;
	}

	public String getType() {
		return type;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + 
				", yearOfRelease=" + yearOfRelease + 
				", albumName=" + albumName + 
				", type=" + type + 
				", albumDescription=" + albumDescription + "]";
	}
}