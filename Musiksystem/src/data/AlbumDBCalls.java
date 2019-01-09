package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import logic.domainClasses.Album;

public class AlbumDBCalls extends JDBC { 

	public static boolean updateAlbum(Album album) {
		try {
			String sql = "UPDATE album " + "SET albumName'" + album.getAlbumName() 
					+ "', type=" + album.getType() + " WHERE id="
					+ album.getYearOfRelease() + " WHERE id=" + album.getAlbumId();
			System.out.println(sql);

			Statement statement = connection.createStatement();
			int nRows = statement.executeUpdate(sql);

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

			Statement statement = connection.createStatement();
			int nRows = statement.executeUpdate(sql);

			return (nRows == 1);
		} catch (SQLException e) {
			System.out.println("Could not delete album: " + album);
			return false;
		}
	}

	public static boolean addAlbum(Album album) {
		try {
			String sql = "INSERT INTO album " + "VALUES ('" + album.getAlbumName() 
					+ "', " + album.getType() + "', " + album.getYearOfRelease() + "', " + album.getAlbumDescription() + ")";
			System.out.println(sql);

			Statement statement = connection.createStatement();
			int nRows = statement.executeUpdate(sql);

			if (nRows != 1)
				return false;

			// get auto-generated key
			ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");

			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				album.setAlbumId(id);
			}

			return true;
		} catch (SQLException e) {
			System.out.println("Could not add Album: " + album);
			return false;
		}
	}
}
