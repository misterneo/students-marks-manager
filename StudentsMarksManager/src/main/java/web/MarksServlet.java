package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IDaoImpl;
import dao.Mark;

/**
 * Servlet implementation class GradesServlet
 */
@WebServlet("/marks")
public class MarksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final IDaoImpl dao = new IDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("path", "marks");
		request.setAttribute("title", "Marks");

		ArrayList<Object> data = dao.getAllMarks();
		@SuppressWarnings("unchecked")
		List<List<String>> studentsData = (List<List<String>>) data.get(0);

		@SuppressWarnings("unchecked")
		List<String> columns = (List<String>) data.get(1);

		request.setAttribute("columns", columns);
		request.setAttribute("data", studentsData);

		request.getRequestDispatcher("pages/marks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Enumeration<String> enumeration = request.getParameterNames();
		int studentId = Integer.parseInt(request.getParameter("student_id"));

		List<String> params = new ArrayList<>();

		while (enumeration.hasMoreElements()) {
			String parameterName = enumeration.nextElement();
			params.add(parameterName);
		}

		for (String q : params) {
			if (q.equals("student_id")) {
				continue;
			}
			float score = -1;
			if (!request.getParameter(q).isEmpty()) {
				try {
					score = Float.parseFloat(request.getParameter(q));
				} catch (Exception e) {
				}

				int subjectId = Integer.parseInt(q.substring(0, q.indexOf(" |")));

				if (score != -1 && score >= 0 && score <= 20) {
					dao.addMark(new Mark(studentId, subjectId, score));
				} else {
					request.setAttribute("error", "badInputs");
				}
			}

		}

		doGet(request, response);
	}

}
