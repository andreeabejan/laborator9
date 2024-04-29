package laborator9.laborator9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String URL =
			"jdbc:postgresql://localhost:5432/Lab_java";
	private static final String USER = "postgres";
	private static final String PASSWORD = "student";
	private static Connection connection = null;
	
	private Database() {}
	
	public static Connection getConnection() {
		createConnection();
		return connection;
	}
	
	private static void createConnection() {
		try {
			connection= DriverManager.getConnection(URL, USER, PASSWORD);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("There was an error at closing the connection.");
			e.printStackTrace();
		}
	}
}
