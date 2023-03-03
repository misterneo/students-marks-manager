package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Mark;
import models.Student;
import models.Subject;

public interface IDao {
	public boolean addStudent(Student student);

	public boolean addSubject(Subject subject);

	public boolean addMark(Mark mark);

	public ArrayList<Object> getAllMarks();

	public List<Student> getAllStudents();

	public List<Subject> getAllSubjects();

	public Map<String, String> getDataSummary();

	public boolean deleteStudent(int student_id);

	public boolean deleteSubject(int subject_id);
}
