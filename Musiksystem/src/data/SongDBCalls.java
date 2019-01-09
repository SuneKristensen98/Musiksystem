package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.domainClasses.Song;

public class SongDBCalls {
	
	//private static Connection connection;
	public static boolean updateSong(Song song) {
		try {
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
			String sql = "DELETE FROM song WHERE id = " + song.getSongId();
//			System.out.println(sql);
			
			Statement statement = JDBC.connection.createStatement();
			int nRows = statement.executeUpdate(sql);
			
			return (nRows == 1);
		}
		catch (SQLException e) {
			System.out.println("Could not delete song: " + song);
			return false;
		}
	}
		
	public static boolean addSong(Song song) {
		try {
			JDBC.loadJDBCDriver();
			JDBC.openConnection("BravoMusicDB");

		    String query = "INSERT INTO song (albumId, artistId, conductorId, songName, genre, songwriter, songNote)" + " values (?, ?, ?, ?, ?, ?, ?)";

		          // create the mysql insert preparedstatement
		          PreparedStatement preparedStmt = JDBC.connection.prepareStatement(query);
		          preparedStmt.setInt(1, song.getAlbumId());
		          preparedStmt.setInt(2, song.getArtistId());
		          preparedStmt.setInt(3, song.getConductorId());
		          preparedStmt.setString(4, song.getSongName());
		          preparedStmt.setObject(5, song.getGenre());
		          preparedStmt.setString(6, song.getSongwriter());
		          preparedStmt.setString(7, song.getSongNote());
//			System.out.println(sql);
			
			int nRows = preparedStmt.executeUpdate();
			if (nRows != 1) {
				return false;
			}
			
			//get auto-generated key
			ResultSet resultSet = preparedStmt.executeQuery("SELECT SCOPE_IDENTITY()");
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				song.setSongId(id);
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
