package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.domainClasses.Album;

public class AlbumDBCalls {
	static JDBC jdbc = new JDBC();

	public static boolean updateAlbum(Album album) {
		try {
			PreparedStatement stmt = jdbc.getCon().prepareStatement("UPDATE album " 
					+ "SET albumName = ?"
					+ ", type = ?"
					+ ", yearOfRelease = ?"
					+ ", albumDescription = ?"
					+ " WHERE albumId = " + album.getAlbumId());

			stmt.setString(1, album.getAlbumName());
			stmt.setString(2, album.getType());
			stmt.setString(4, album.getAlbumDescription());
			

		    if (album.getYearOfRelease() == 0) {
		    	stmt.setNull(3, album.getYearOfRelease());
		    } else {
		    	stmt.setInt(3, album.getYearOfRelease());
		    }
		    
			int nRows = stmt.executeUpdate();

			return (nRows == 1);
		} catch (SQLException e) {
			System.out.println("Could not update Album: " + album);
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean deleteAlbum(int albumId) {
		try {
			String sql = "DELETE FROM album WHERE albumid=" + albumId;

			Statement statement = jdbc.getCon().createStatement();
			int nRows = statement.executeUpdate(sql);

			return (nRows == 1);
		} catch (SQLException e) {
			System.out.println("Could not delete album with Id: " + albumId);
			return false;
		}
	}

	public static int addAlbum(Album album) {
		try {
		    String query = "INSERT INTO album (albumName, type, YearOfRelease, albumDescription)" + " values (?, ?, ?, ?)";
		    PreparedStatement preparedStmt = jdbc.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    preparedStmt.setString(1, album.getAlbumName());
		    preparedStmt.setString(2, album.getType());
		    preparedStmt.setString(4, album.getAlbumDescription());
		   
		    if (album.getYearOfRelease() == 0) {
		    	preparedStmt.setNull(3, album.getYearOfRelease());
		    } else {
		    	preparedStmt.setInt(3, album.getYearOfRelease());
		    }

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
			System.out.println("Could not add album: " + album);
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	public Album getAlbumWithId(int albumId) {
		ArrayList<Album> foundAlbum = new ArrayList<Album>();
		try {		
			PreparedStatement stmt = jdbc.getCon().prepareStatement("SELECT * FROM album WHERE albumId = " + albumId);
					
			ResultSet rs = stmt.executeQuery();
						
			while (rs.next()) {
				String albumName = rs.getString("albumName");
				String type = rs.getString("type");
				int yearOfRelease = rs.getInt("yearOfRelease");
				String albumDescription = rs.getString("albumDescription");
				
				
				foundAlbum.add(new Album(albumId, albumName, type, yearOfRelease, albumDescription));
			}			
		}
		
		catch (SQLException e) {
			System.out.println("Error executing SQL statement");
			System.out.println(e.getMessage());
		}
		
		if (foundAlbum.size() == 1) {			
			return foundAlbum.get(0);
		} else {
			return null;
		}
	}
}
