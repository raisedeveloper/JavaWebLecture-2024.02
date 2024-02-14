package ch06_basic;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/ch06/register")
public class Ex04_RegisterMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// register form (just form) 을 제공해주는 입력 / form을 제공해주는 것이 get 의 역할입니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/ch06/registerForm.jsp");
		rd.forward(request, response);
	}

	// 입력 form 을 처리해주는 역할
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("uname");
		String email = request.getParameter("email");
		// uid 가 이미 존재하는지 중복 확인 장치를 추가해야 합니다. DAO에서 null 값이 있는지 확인 해줍니다.
		// pwd 와 pwd2의 일치 여부 확인 장치를 추가해야 합니다. equals 를 사용합니다.
		// 입력된 값으로 User 객체를 반듭니다.
		// UserService 라고 예를 들때, registerMember를 호출하려면 DB에 등록 해야합니다.
		// 환영 메세지와 함께 로그인 창으로 보내줘야 합니다.
		
		User user = new User(uid, pwd, name, email, LocalDate.now(), 0);  // 결과표현용 LocalDate 일시적 추가
		RequestDispatcher rd = request.getRequestDispatcher("/ch06/registerResult.jsp");
		request.setAttribute("user",  user);	// 객체를 내려보내는 작업입니다.
		rd.forward(request, response);
	}
}