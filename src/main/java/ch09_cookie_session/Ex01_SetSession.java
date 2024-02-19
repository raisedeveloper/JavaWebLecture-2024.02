package ch09_cookie_session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ch09/setSession") // 7CD68D56BAC74327CAB95727FECA15E3
public class Ex01_SetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html; charset=utf-8");
//		out.print("<h1>isNew: " + session.isNew());

		session.setAttribute("price", 12500);
		session.setAttribute("uid", "james");
		String[] fruits = { "apple", "banana", "cherry" };
		session.setAttribute("fruits", fruits);
		session.setMaxInactiveInterval(24 * 60 * 60); // 기본은 30분이지만 지정을 통해 늘릴 수 있
//		response.getWriter().append("Served at : ").append(request.getContextPath());
		
		session.invalidate(); // 모든 set값 삭제
		
		RequestDispatcher rd = request.getRequestDispatcher("/ch09/session.jsp");
		rd.forward(request, response);
	}
}
