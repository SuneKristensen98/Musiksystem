package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import logic.domainClasses.Album;

public class AlbumDBCalls {
	static JDBC jdbc = new JDBC();

	public static boolean updateAlbum(Album album) {
		try {
			PreparedStatement stmt = jdbc.connection.prepareStatement("UPDATE album " 
					+ "SET albumName'" + album.getAlbumName()
					+ "', type=" + album.getType() 
					+ "', YearOfRelease =" + album.getYearOfRelease() 
					+ "' WHERE id=" + album.getAlbumId());
		//	System.out.println(sql);

			stmt.setString(1, album.getAlbumName());
			stmt.setString(2, album.getType());
			stmt.setInt(3, album.getYearOfRelease());
			stmt.setString(4, album.getAlbumDescription());
			int nRows = stmt.executeUpdate();

			return (nRows == 1);
		} catch (SQLException e) {
			System.out.println("Could not update Album: " + album);
			return false;
		}
	}

	public static boolean deleteAlbum(Album album) {
		try {
			String sql = "DELETE FROM album WHERE albumid=" + album.getAlbumId();
			System.out.println(sql);

			Statement statement = jdbc.connection.createStatement();
			int nRows = statement.executeUpdate(sql);

			return (nRows == 1);
		} catch (SQLException e) {
			System.out.println("Could not delete album: " + album);
			return false;
		}
	}

	public static boolean addAlbum(Album album) {
		try {
		    String query = "INSERT INTO album (albumName, type, YearOfRelease, albumDescription)" + " values (?, ?, ?, ?)";

		    PreparedStatement preparedStmt = jdbc.connection.prepareStatement(query);
		    preparedStmt.setString(1, album.getAlbumName());
		    preparedStmt.setString(2, album.getType());
		    preparedStmt.setInt(3, album.getYearOfRelease());
		    preparedStmt.setString(4, album.getAlbumDescription());

			int nRows = preparedStmt.executeUpdate();
			if (nRows != 1) {
				return false;
			}
			
			return true;
			
		}
		catch (SQLException e) {
			System.out.println("Could not add album: " + album);
			System.out.println(e.getMessage());
			return false;
		}
	}
}	
		
		
//		try {
//			String sql = "INSERT INTO album " + "VALUES ('" + album.getAlbumName() 
//					+ "', " + album.getType() + "', " + album.getYearOfRelease() + "', " + album.getAlbumDescription() + ")";
//			System.out.println(sql);
//
//			Statement statement = connection.createStatement();
//			int nRows = statement.executeUpdate(sql);
//
//			if (nRows != 1)
//				return false;
//
//			// get auto-generated key
//			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
//
//			if (resultSet.next()) {
//				int id = resultSet.getInt(1);
//				album.setAlbumId(id);
//			}
//
//			return true;
//		} catch (SQLException e) {
//			System.out.println("Could not add Album: " + album);
//			return false;
//		}

