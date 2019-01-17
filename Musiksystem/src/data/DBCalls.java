package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.TableViewInfo;

public class DBCalls {
	static JDBC jdbc = new JDBC();
	public List<TableViewInfo> getAllMusicWhere(String whereClause, Genre genreParameter, Boolean lp, Boolean cd, int albumMaybeId) {
		ArrayList<TableViewInfo> searchResult = new ArrayList<TableViewInfo>();
		try {
			String[] stringArrayOfColumns = new String[]{"songName", "albumName", "artistName", "conductorName", "yearOfRelease", "type", 
											"albumDescription", "genre", "time", "songwriter", "songNote"};
			 				
			String whereStringOR = getWhereString(whereClause, stringArrayOfColumns);
			String whereStringAND = "";
			
			if (genreParameter != null) {
				whereStringAND = "genre = '" + genreParameter + "' AND ";
			} 
			
			if (!lp) {
				whereStringAND += "type = 'cd' AND ";
			}
			
			if (!cd) {
				whereStringAND += "type = 'lp' AND ";
			}
			
			if (albumMaybeId != -2) {
				whereStringOR = "s.albumId = " + albumMaybeId;
			}
			
			String whereString = whereStringAND + whereStringOR;
			
			PreparedStatement stmt = jdbc.getCon().prepareStatement("SELECT * FROM song s JOIN album al ON s.albumId = al.albumId "
					+ "JOIN artist ar ON s.artistId = ar.artistId JOIN conductor c ON s.conductorId = c.conductorId WHERE " + whereString);

			if (whereClause != "") {
				for (int i = 1; i <= stringArrayOfColumns.length; i++) {
					stmt.setString(i, "%" + whereClause + "%");
				}
			}
					
			ResultSet rs = stmt.executeQuery();
						
			while (rs.next()) {
				String songName = rs.getString("songName");
				int albumId = rs.getInt("albumId");
				int songId = rs.getInt("songId");
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
				
				if (conductorName != null && !conductorName.equals("")) {
					artistName = conductorName + " med " + artistName;					
				}
				
				searchResult.add(new TableViewInfo(songName, albumId, songId, albumName, yearOfRelease, type, albumDescription, artistName, conductorName, genre, time, songwriter, songNote));
			}			
		}
		catch (SQLException e) {
			System.out.println("Error executing SQL statement");
			System.out.println(e.getMessage());
		}
		return searchResult;
	}
	
	public int addArtist(Artist artist) {
		try {
		    String query = "INSERT INTO artist (artistName)" + " values (?)";

		    PreparedStatement preparedStmt = jdbc.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    preparedStmt.setString(1, artist.getName());
			
			int nRows = preparedStmt.executeUpdate();
			if (nRows != 1) {
				return -1;
			} else {
				ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
				generatedKeys.next();
				return (int) generatedKeys.getLong(1);
			}	
		}
		catch (SQLException e) {
			System.out.println("Could not add artist: " + artist);
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	public int addConductor(Conductor conductor) {
		try {
		    String query = "INSERT INTO conductor (conductorName)" + " values (?)";

		    PreparedStatement preparedStmt = jdbc.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    preparedStmt.setString(1, conductor.getConductorName());
			
			int nRows = preparedStmt.executeUpdate();
			if (nRows != 1) {
				return -1;
			} else {
				ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
				generatedKeys.next();
				return (int) generatedKeys.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("Could not add conductor: " + conductor);
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	private String getWhereString(String whereClause, String[] stringArrayOfColumns) {
		String whereString = "";

		if (whereClause == "") {
			whereString = "1 = 1";
		} else {
			for (int x = 0; x < stringArrayOfColumns.length; x++) {
				if (x == 0) {
					whereString = "(";
				}
				if (x != stringArrayOfColumns.length - 1) {
					whereString += stringArrayOfColumns[x] + " LIKE ? OR ";
				} else {
					whereString += stringArrayOfColumns[x] + " LIKE ?)";					
				}
			}
		}
		return whereString;
	}
}