package dao;

public class Subject {
	private int id;
	private String name;
	private int coefficient;

	public Subject(int id, String name, int coefficient) {
		super();
		this.id = id;
		this.name = name;
		this.coefficient = coefficient;
	}

	public Subject(String name, int coefficient) {
		super();
		this.name = name;
		this.coefficient = coefficient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", coefficient=" + coefficient + "]";
	}

}
