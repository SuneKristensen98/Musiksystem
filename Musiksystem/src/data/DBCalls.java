package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.TableViewInfo;

public class DBCalls {
	static JDBC jdbc = new JDBC();
	//TODO Hvorfor skal den væære static?
	private static List<TableViewInfo> getAllMusicWhere(String whereClause) {
		ArrayList<TableViewInfo> array = new ArrayList<TableViewInfo>();
		try {
			PreparedStatement stmt = JDBC.connection.prepareStatement("SELECT * FROM song s JOIN album al ON s.albumId = al.albumId JOIN artist ar ON s.artistId = ar.artistId JOIN"
					+ " conductor c ON s.conductorId = c.conductorId WHERE " + whereClause);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String songName = rs.getString("songName");
				String albumName = rs.getString("albumName");
				String artistName = rs.getString("artistName");
				String conductorName = rs.getString("conductorName");
				int yearOfRelease = rs.getInt("yearOfRelease");
				String type = rs.getString("type");
				String albumDescription = rs.getString("albumDescription");
				String genre = rs.getString("genre");
				int time = rs.getInt("time");
				String songwriter = rs.getString("songwriter");
				String songNote = rs.getString("songNote");
				
				array.add(new TableViewInfo(songName, albumName, yearOfRelease, type, albumDescription, artistName, conductorName, genre, time, songwriter, songNote));
			}			
		}
		catch (SQLException e) {
			System.out.println("Error executing SQL statement");
			System.out.println(e.getMessage());
		}
		return array;
	}
	
	public static List<TableViewInfo> getAllMusic() {
				return getAllMusicWhere("1=1");
	}
	
	public List<TableViewInfo> getAllMusicSearch(String whereClause1) {
		String whereClause = "songName LIKE '%" + whereClause1 + "%' OR albumName LIKE '%" +  whereClause1 + "%'";
		return getAllMusicWhere(whereClause); 
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
//