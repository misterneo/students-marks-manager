package models;

public class Mark {
	private int id, student_id, subject_id;
	private Student student;
	private Subject subject;
	private double score;

	public Mark(int student_id, int subject_id, float score) {
		super();
		this.student_id = student_id;
		this.subject_id = subject_id;
		this.score = score;
	}

	public Mark(Subject subject, float score) {
		super();
		this.subject = subject;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", subject=" + subject.getName() + ", score=" + score + "]";
	}

}
