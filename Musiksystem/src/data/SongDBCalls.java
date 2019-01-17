package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import logic.domainClasses.Song;

public class SongDBCalls {
	static JDBC jdbc = new JDBC();
	
	public static boolean updateSong(Song song) {
		try {
			PreparedStatement stmt = jdbc.getCon().prepareStatement("UPDATE song "
					+ "SET songName = ?" +
						", genre = '" + song.getGenre().stringValue + 
						"', time = ?" + 
						", songwriter = ?" + 
						", songNote = ?" + 
					" WHERE songId = " + song.getSongId());
			
			stmt.setString(1, song.getSongName());
			stmt.setInt(2, song.getTime());
			stmt.setString(3, song.getSongwriter());
			stmt.setString(4, song.getSongNote());
			int nRows = stmt.executeUpdate();

			return (nRows == 1);
		}
		catch (SQLException e) {
			System.out.println("Could not update song: " + song);
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean deleteSong(int songId) {
		try {

			String sql = "DELETE FROM song WHERE songId = " + songId;
			
			Statement statement = jdbc.getCon().createStatement();
			int nRows = statement.executeUpdate(sql);
			
			return (nRows == 1);
		}
		catch (SQLException e) {
			System.out.println("Could not delete song with songId: " + songId);
			System.out.println(e.getMessage());
			return false;
		}
	}
		
	public static boolean addSong(Song song) {
		try {
		    String query = "INSERT INTO song (albumId, artistId, conductorId, songName, genre, time, songwriter, songNote)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";

		    PreparedStatement preparedStmt = jdbc.getCon().prepareStatement(query);
		    preparedStmt.setInt(1, song.getAlbumId());
		    preparedStmt.setInt(2, song.getArtistId());
		    preparedStmt.setInt(3, song.getConductorId());
		    preparedStmt.setString(4, song.getSongName());
		    preparedStmt.setString(5, song.getGenre().stringValue);
		    preparedStmt.setString(7, song.getSongwriter());
		    preparedStmt.setString(8, song.getSongNote());
			
		    if (song.getTime() == 0) {
		    	preparedStmt.setNull(6, song.getTime());
		    } else {
		    	preparedStmt.setInt(6, song.getTime());
		    }
		    
			int nRows = preparedStmt.executeUpdate();
			if (nRows != 1) {
				return false;
			}
			
			return true;
		}
		catch (SQLException e) {
			System.out.println("Could not add song: " + song);
			System.out.println(e.getMessage());
			return false;
		}
	}
	
//	public static List<Song> getSongsWithAlbumId(int albumId) {
//		ArrayList<Song> foundSongs = new ArrayList<Song>();
//		try {		
//			PreparedStatement stmt = jdbc.getCon().prepareStatement("SELECT * FROM song WHERE albumId = " + albumId);
//					
//			ResultSet rs = stmt.executeQuery();
//			
//			while (rs.next()) {
//				int songId = rs.getInt("songId");
//				int artistId = rs.getInt("artistId");
//				int conductorId = rs.getInt("conductorId");
//				String songName = rs.getString("songName");
//				//TODO fiks genre
//				String genre = rs.getString("genre");
//				int time = rs.getInt("time");
//				String songwriter = rs.getString("songwriter");
//				String songNote = rs.getString("songNote");
//				
//				//TODO den der genre der
//				foundSongs.add(new Song(songId, albumId, artistId, conductorId, songName, Genre.ALTERNATIVE, time, songwriter, songNote));
//			}		
//		}
//		
//		catch (SQLException e) {
//			System.out.println("Error executing SQL statement");
//			System.out.println(e.getMessage());
//		}
//		return foundSongs;
//	}
}
