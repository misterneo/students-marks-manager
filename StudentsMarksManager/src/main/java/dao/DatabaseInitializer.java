package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
	public static void initialize() {

		try (Connection conn = DatabaseConnection.getConnection(true); Statement s = conn.createStatement()) {
			ResultSet rs = s.executeQuery(
					"SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'students_marks_manager'");
			if (!rs.next()) {
				Statement s2 = conn.createStatement();
				s2.executeUpdate("CREATE DATABASE IF NOT EXISTS students_marks_manager");
				s2.executeUpdate("USE students_marks_manager;");
				s2.executeUpdate("CREATE TABLE students (\n" + "  id INT NOT NULL AUTO_INCREMENT,\n"
						+ "  first_name VARCHAR(255) NOT NULL,\n" + "  last_name VARCHAR(255) NOT NULL,\n"
						+ "  email VARCHAR(255) NOT NULL,\n" + "  PRIMARY KEY (id)\n" + ");");
				s2.executeUpdate("CREATE TABLE subjects (\n" + "  id INT NOT NULL AUTO_INCREMENT,\n"
						+ "  name VARCHAR(255) NOT NULL,\n" + "	coefficient INT NOT NULL,\n" + "  PRIMARY KEY (id)\n"
						+ ");");
				s2.executeUpdate("CREATE TABLE marks (\n" + "  id INT NOT NULL AUTO_INCREMENT,\n"
						+ "  student_id INT NOT NULL,\n" + "  subject_id INT NOT NULL,\n" + "  score DOUBLE NOT NULL,\n"
						+ "	 CONSTRAINT unique_mark UNIQUE (student_id, subject_id)," + "  PRIMARY KEY (id),\n"
						+ "  FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE\n"
						+ "       ON UPDATE CASCADE, \n"
						+ "  FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE\n"
						+ "       ON UPDATE CASCADE\n" + ");");
				s2.close();

				System.out.println("Database initialized successfully.");
			} else {
//				System.out.println("Database already exists.");
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}

	}
}
