package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDaoImpl;
import models.Student;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final IDaoImpl dao = new IDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("path", "students");
		request.setAttribute("title", "Students");

		List<Student> students = dao.getAllStudents();

		request.setAttribute("students", students);

		request.getRequestDispatcher("pages/students.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");
		String email = request.getParameter("email");

		if (fn != "" && ln != "" && email != "") {
			Student newStudent = new Student(fn, ln, email);
			dao.addStudent(newStudent);
		} else {
			request.setAttribute("error", "badInputs");
		}

		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		boolean res = dao.deleteStudent(Integer.parseInt(id));

		String jsonResponse = "{\"message\": \"Something went wrong!\"}";

		if (res) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json");
			jsonResponse = "{\"message\": \"Resource deleted successfully\"}";
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			response.setContentType("application/json");
		}

		response.getWriter().write(jsonResponse);
	}

}
