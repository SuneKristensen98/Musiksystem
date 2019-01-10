package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import logic.domainClasses.Song;

public class SongDBCalls {
	static JDBC jdbc = new JDBC();
	
	public static boolean updateSong(Song song) {
		try {
			PreparedStatement stmt = jdbc.connection.prepareStatement("UPDATE song "
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
	
	public static boolean deleteSong(Song song) {
		try {

			String sql = "DELETE FROM song WHERE songId = " + song.getSongId();
//			System.out.println(sql);
			
			Statement statement = jdbc.connection.createStatement();
			int nRows = statement.executeUpdate(sql);
			
			return (nRows == 1);
		}
		catch (SQLException e) {
			System.out.println("Could not delete song: " + song);
			System.out.println(e.getMessage());
			return false;
		}
	}
		
	public static boolean addSong(Song song) {
		try {
		    String query = "INSERT INTO song (albumId, artistId, conductorId, songName, genre, time, songwriter, songNote)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";

		    PreparedStatement preparedStmt = jdbc.connection.prepareStatement(query);
		    preparedStmt.setInt(1, song.getAlbumId());
		    preparedStmt.setInt(2, song.getArtistId());
		    preparedStmt.setInt(3, song.getConductorId());
		    preparedStmt.setString(4, song.getSongName());
		    preparedStmt.setString(5, song.getGenre().stringValue);
		    preparedStmt.setString(6, song.getSongwriter());
		    preparedStmt.setString(7, song.getSongNote());
			
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
}
