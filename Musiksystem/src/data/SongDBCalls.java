package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.domainClasses.Song;

public class SongDBCalls {
	
	//private static Connection connection;
	public static boolean updateSong(Song song) {
		try {
			JDBC.loadJDBCDriver();
			JDBC.openConnection("BravoMusicDB");
			PreparedStatement stmt = JDBC.connection.prepareStatement("UPDATE song "
					+ "SET songName = ?" +
						", genre = '" + song.getGenre() + 
						"' "/*, time = ?" + 
						"*/ + ", songwriter = ?" + 
						", songNote = ?" + 
					"' WHERE id = " + song.getSongId());
			
			stmt.setString(1, song.getSongName());
//			stmt.setTime(2, song.getTime());
			stmt.setString(3, song.getSongwriter());
			stmt.setString(4, song.getSongNote());
			int nRows = stmt.executeUpdate();
			
			return (nRows == 1);
		}
		catch (SQLException e) {
			System.out.println("Could not update song: " + song);
			return false;
		}
	}
	
	public static boolean deleteSong(Song song) {
		try {
			JDBC.loadJDBCDriver();
			JDBC.openConnection("BravoMusicDB");
			String sql = "DELETE FROM song WHERE songId = " + song.getSongId();
//			System.out.println(sql);
			
			Statement statement = JDBC.connection.createStatement();
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
			JDBC.loadJDBCDriver();
			JDBC.openConnection("BravoMusicDB");

		    String query = "INSERT INTO song (albumId, artistId, conductorId, songName, genre, "/*time,*/ + "songwriter, songNote)" + " values (?, ?, ?, ?, ?, ?, ?)";

		    PreparedStatement preparedStmt = JDBC.connection.prepareStatement(query);
		    preparedStmt.setInt(1, song.getAlbumId());
		    preparedStmt.setInt(2, song.getArtistId());
		    preparedStmt.setInt(3, song.getConductorId());
		    preparedStmt.setString(4, song.getSongName());
		    preparedStmt.setString(5, "ALTERNATIVE");
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
