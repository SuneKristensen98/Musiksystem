package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Song;

public class DBCalls {
	static JDBC jdbc = new JDBC();
	public static List getAllMusic(String whereClause) {
		ArrayList array = new ArrayList();
		try {
			PreparedStatement stmt = JDBC.connection.prepareStatement("SELECT * FROM song, album, artist, conductor WHERE keyword = ?");
			stmt.setString(1, whereClause);
			ResultSet rs = stmt.executeQuery();
			
//			System.out.println(rs);
//
			
			while (rs.next()) {
//				
//				String songName = rs.getString("songName");
//				String albumName = rs.getString("albumName");
//				String artistName = rs.getString("artistName");
//				String conductorName = rs.getString("conductorName");
//				
//				array.add(songName, albumName, artistName);			
			}			
		}
		catch (SQLException e) {
			System.out.println("Error executing SQL statement");
			System.out.println(e.getMessage());
		}
		return array;
	}
	
	public static boolean addArtist(Artist artist) {
		try {
		    String query = "INSERT INTO artist (artistName)" + " values (?)";

		    PreparedStatement preparedStmt = jdbc.connection.prepareStatement(query);
		    preparedStmt.setString(1, artist.getName());
			
			int nRows = preparedStmt.executeUpdate();
			if (nRows != 1) {
				return false;
			}
			
			return true;	
		}
		catch (SQLException e) {
			System.out.println("Could not add artist: " + artist);
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean addConductor(Conductor conductor) {
		try {
		    String query = "INSERT INTO conductor (conductorName)" + " values (?)";

		    PreparedStatement preparedStmt = jdbc.connection.prepareStatement(query);
		    preparedStmt.setString(1, conductor.getConductorName());
			
			int nRows = preparedStmt.executeUpdate();
			if (nRows != 1) {
				return false;
			}
			
			return true;	
		}
		catch (SQLException e) {
			System.out.println("Could not add conductor: " + conductor);
			System.out.println(e.getMessage());
			return false;
		}
	}
}
