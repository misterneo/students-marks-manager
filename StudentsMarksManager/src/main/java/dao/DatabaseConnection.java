package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection conn = null;
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/";
	private static String DOCKER_DB_URL = "jdbc:mysql://db:3306/";
	private static String DB_NAME = "students_marks_manager";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = "";
	private static String DOCKER_DB_PASSWORD = "root";

	public static Connection getConnection(boolean... isInit) throws SQLException {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB_URL + (isInit.length > 0 ? "" : DB_NAME), DB_USER, DB_PASSWORD);
		} catch (Exception e) {

			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(DOCKER_DB_URL + (isInit.length > 0 ? "" : DB_NAME), DB_USER,
						DOCKER_DB_PASSWORD);
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}

		return conn;

	}
}
