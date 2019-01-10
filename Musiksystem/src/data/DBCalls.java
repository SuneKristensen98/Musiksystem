package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCalls {
	private static List getAllMusic(String whereClause) {
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
}
