package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

	//PRIVATE
	public static Connection connection;
	
	public JDBC() {
	if (!loadJDBCDriver())
		System.exit(1);

	if (!openConnection("BravoMusicDB"))
		System.exit(2);
	}
	
	//INDKOMMENTER
//	public Connection getCon() {
//		if(connection == null) {
//			if (!loadJDBCDriver())
//				System.exit(1);
//
//			if (!openConnection("BravoMusicDB"))
//				System.exit(2);
//		}
//		return connection;
//	}

	private boolean loadJDBCDriver() {
		System.out.println("Loading JDBC driver...");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("JDBC driver loaded");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load JDBC driver");
			return false;
		}

		return true;
	}

	private boolean openConnection(String databaseName) {

		String connectionString = "jdbc:sqlserver://localhost:1433;" + "instanceName=SQLEXPRESS;" + "databaseName="
				+ databaseName + ";" + "integratedSecurity=true;";

		System.out.println("Connecting to database...");

		try {
			connection = DriverManager.getConnection(connectionString);
			System.out.println("Database connection established");
		} catch (SQLException e) {
			System.out.println("Failed to connect to database");
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

}
//