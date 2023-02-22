package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDaoImpl;
import dao.Subject;

/**
 * Servlet implementation class SubjctsServlet
 */
@WebServlet("/subjects")
public class SubjctsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final IDaoImpl dao = new IDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjctsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("path", "subjects");
		request.setAttribute("title", "Subjects");

		List<Subject> subjects = dao.getAllSubjects();
//
		request.setAttribute("subjects", subjects);

		request.getRequestDispatcher("pages/subjects.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		int coef = Integer.parseInt(request.getParameter("coef"));

		if (name != "" && coef > 0) {
			Subject newSubject = new Subject(name, coef);
			dao.addSubject(newSubject);
		}

		System.out.println("Subject Added.");
		doGet(request, response);
	}

}
