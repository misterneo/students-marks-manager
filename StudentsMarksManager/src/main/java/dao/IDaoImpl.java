package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDaoImpl implements IDao {

	private static final String INSERT_STUDENT_SQL = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
	private static final String INSERT_SUBJECT_SQL = "INSERT INTO subjects (name, coefficient) VALUES (?, ?)";
	private static final String INSERT_MARK_SQL = "INSERT INTO marks (student_id, subject_id, score) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE score = VALUES(score)";
	private static final String SELECT_MARKS_BY_STUDENT_ID_SQL = "SELECT marks.score, subjects.name, subjects.coefficient, subjects.id, students.first_name, students.last_name, students.email FROM marks "
			+ "JOIN subjects ON marks.subject_id = subjects.id " + "JOIN students ON marks.student_id = students.id "
			+ "WHERE marks.student_id = ?";
	private static final String SELECT_ALL_STUDENTS_SQL = "SELECT * from students";
	private static final String SELECT_ALL_SUBJECTS_SQL = "SELECT * from subjects";

	private static final String DATA_SUMMARY_SQL = "SELECT\n"
			+ "    (SELECT COUNT(*) FROM students) AS total_students,\n"
			+ "    (SELECT COUNT(*) FROM subjects) AS total_subjects,\n"
			+ "    ROUND(SUM(marks.score * subjects.coefficient) / SUM(subjects.coefficient), 2) AS overall_avg_score\n"
			+ "FROM\n" + "    marks\n" + "    JOIN subjects ON marks.subject_id = subjects.id;";

	private static final String DELETE_STUDENT_SQL = "DELETE FROM students WHERE id = ?";
	private static final String DELETE_SUBJECT_SQL = "DELETE FROM subjects WHERE id = ?";

	@Override
	public boolean addStudent(Student student) {

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT_SQL)) {

			ps.setString(1, student.getFirst_name());
			ps.setString(2, student.getLast_name());
			ps.setString(3, student.getEmail());

			int rowInserted = ps.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e1) {

			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addSubject(Subject subject) {
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT_SUBJECT_SQL)) {

			ps.setString(1, subject.getName());
			ps.setInt(2, subject.getCoefficient());

			int rowInserted = ps.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e1) {

			e1.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addMark(Mark mark) {

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT_MARK_SQL)) {

			ps.setInt(1, mark.getStudent_id());
			ps.setInt(2, mark.getSubject_id());
			ps.setDouble(3, mark.getScore());

			int rowInserted = ps.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e1) {

			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_ALL_STUDENTS_SQL)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String email = rs.getString("email");

					Student student = new Student(id, first_name, last_name, email);

					students.add(student);
				}
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		return students;
	}

	@Override
	public List<Subject> getAllSubjects() {
		List<Subject> subjects = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SUBJECTS_SQL)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int coef = rs.getInt("coefficient");

					Subject subject = new Subject(id, name, coef);

					subjects.add(subject);
				}
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		return subjects;
	}

	@Override
	public ArrayList<Object> getStudentMarks(int student_id) {
		List<Mark> marks = new ArrayList<>();
		Student student = null;

		ArrayList<Object> data = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_MARKS_BY_STUDENT_ID_SQL)) {
			ps.setInt(1, student_id);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					float score = rs.getFloat("score");
					String subject_name = rs.getString("name");
					int subject_coef = rs.getInt("coefficient");

					if (student == null) {
						String first_name = rs.getString("first_name");
						String last_name = rs.getString("last_name");
						String email = rs.getString("email");

						student = new Student(first_name, last_name, email);
					}

					Subject subject = new Subject(subject_name, subject_coef);

					marks.add(new Mark(subject, score));
				}
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		data.add(student);
		data.add(marks);

		return data;
	}

	@Override
	public ArrayList<Object> getAllMarks() {
		ArrayList<Object> res = new ArrayList<>();
		List<List<String>> data = new ArrayList<>();
		List<String> columns = new ArrayList<>();

		try {
			Connection conn = DatabaseConnection.getConnection();

			// Step 1: Get a list of distinct subject names and their coefficients
			String getSubjectsQuery = "SELECT GROUP_CONCAT(DISTINCT CONCAT('MAX(CASE WHEN name = ''', name, ''' THEN score ELSE NULL END) AS `', CONCAT(id, ' | ', name, ' (x', coefficient, ')'), '`') ORDER BY name) AS subjectColumns FROM subjects";
			PreparedStatement getSubjectsStmt = conn.prepareStatement(getSubjectsQuery);
			ResultSet subjectsResult = getSubjectsStmt.executeQuery();
			subjectsResult.next();
			String subjectColumns = subjectsResult.getString("subjectColumns");

			// Step 2: Construct the dynamic pivot query
			String query = String.format(
					"SELECT students.id AS student_id, students.first_name, students.last_name, %s, ROUND(SUM(marks.score * subjects.coefficient) / SUM(subjects.coefficient), 2) AS avg_score FROM students LEFT JOIN marks ON students.id = marks.student_id LEFT JOIN subjects ON marks.subject_id = subjects.id GROUP BY students.id",
					subjectColumns);

			// Step 3: Execute the dynamic query
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);

			ResultSetMetaData crsmd = result.getMetaData();
			int cnumColumns = crsmd.getColumnCount();
			for (int i = 1; i <= cnumColumns; i++) {
				columns.add(crsmd.getColumnName(i));
			}

			// Step 4: Process the result set
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String id = result.getString("student_id");
				double avgScore = result.getDouble("avg_score");

				List<String> studentData = new ArrayList<>();

				studentData.add(id);
				studentData.add(firstName);
				studentData.add(lastName);

				ResultSetMetaData rsmd = result.getMetaData();
				int numColumns = rsmd.getColumnCount();
				for (int i = 1; i <= numColumns; i++) {
					String columnName = rsmd.getColumnName(i);
					// Skip the columns that are not subject names
					if (columnName.equals("id") || columnName.equals("first_name") || columnName.equals("last_name")
							|| columnName.equals("email") || columnName.equals("avg_score")) {
						continue;
					}

					Double score = (Double) result.getObject(columnName);
					if (score != null) {
						studentData.add(Double.toString(score));
					} else {
						studentData.add("-");
					}

				}
				studentData.add(Double.toString(avgScore));
				data.add(studentData);
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		res.add(data);
		res.add(columns);

		return res;

	}

	@Override
	public Map<String, String> getDataSummary() {
		Map<String, String> summary = new HashMap<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(DATA_SUMMARY_SQL)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int totalStudents = rs.getInt("total_students");
					int totalSubjects = rs.getInt("total_subjects");
					double overallAvgScore = rs.getDouble("overall_avg_score");

					summary.put("totalStudents", Integer.toString(totalStudents));
					summary.put("totalSubjects", Integer.toString(totalSubjects));
					summary.put("overallAvgScore", Double.toString(overallAvgScore));
				}
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		return summary;
	}

	@Override
	public boolean deleteStudent(int student_id) {
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(DELETE_STUDENT_SQL)) {

			ps.setInt(1, student_id);

			int rowInserted = ps.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e1) {

			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteSubject(int subject_id) {
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(DELETE_SUBJECT_SQL)) {

			ps.setInt(1, subject_id);

			int rowInserted = ps.executeUpdate();

			return rowInserted > 0;

		} catch (Exception e1) {

			e1.printStackTrace();
			return false;
		}

	}

}
