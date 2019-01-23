package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import domainClasses.Song;

public class SongDBCalls {
	static JDBC jdbc = new JDBC();

	public boolean updateSong(Song song) {
		try {

			PreparedStatement stmt = jdbc.getCon()
					.prepareStatement("UPDATE song " + "SET songName = ?" + ", genre = '" + song.getGenre().stringValue
							+ "', time = ?" + ", songwriter = ?" + ", songNote = ?" + ", artistId = ?"
							+ ", conductorId = ?" + " WHERE songId = " + song.getSongId());

			stmt.setString(1, song.getSongName());
			stmt.setString(3, song.getSongwriter());
			stmt.setString(4, song.getSongNote());
			stmt.setInt(5, song.getArtistId());

			if (song.getTime() == 0) {
				stmt.setNull(2, song.getTime());
			} else {
				stmt.setInt(2, song.getTime());
			}

			// Sørger for at conductorId bliver sat till NULL i databasen, hvis der ingen conductor er
			if (song.getConductorId() == 0) {
				stmt.setNull(6, song.getConductorId());
			} else {
				stmt.setInt(6, song.getConductorId());
			}

			int nRows = stmt.executeUpdate();
			return (nRows == 1);
		}

		catch (SQLException e) {
			System.out.println("Could not update song: " + song);
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean deleteSong(int songId) {
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

	public boolean addSong(Song song) {
		try {
			String query = "INSERT INTO song (albumId, artistId, conductorId, songName, genre, time, songwriter, songNote)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = jdbc.getCon().prepareStatement(query);
			preparedStmt.setInt(1, song.getAlbumId());
			preparedStmt.setInt(2, song.getArtistId());
			preparedStmt.setString(4, song.getSongName());
			preparedStmt.setString(5, song.getGenre().stringValue);
			preparedStmt.setString(7, song.getSongwriter());
			preparedStmt.setString(8, song.getSongNote());

			// Sørger for at conductorId bliver sat till NULL i databasen, hvis der ingen conductor er
			if (song.getConductorId() == 0) {
				preparedStmt.setNull(3, song.getConductorId());
			} else {
				preparedStmt.setInt(3, song.getConductorId());
			}

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
}
