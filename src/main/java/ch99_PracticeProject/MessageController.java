package ch99_PracticeProject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet({"/ch99/message/list, /ch99/message/insertMessage, "
		+ "/ch99/message/updateMessage, /ch99/message/deleteMessage"})
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageDao mDao = new MessageDaoimpl();
	
	protected void service(HttpServletRequest request, HttpServletResponse response, Object writer, MessageProject modTime, Object Message) throws ServletException, IOException {
	String[] uri = request.getRequestURI().split("/");
	String action = uri[uri.length -1];
	String method = request.getMethod();
	RequestDispatcher rd = null;
	MessageProject message = null;
	int mid = 0;
	String content = null; writer = null; modTime = null;
	
	switch(action) {
	case "list":
		List<com.mysql.cj.protocol.Message> list = mDao.getmessageList();
		rd = request.getRequestDispatcher("/ch99/message/list");
		request.setAttribute("list", list);
		rd.forward(request, response);
		break;
		
	case "insertMessage":
		if(method.equals("GET")) {
			rd = request.getRequestDispatcher("/ch07/kpop/insertMessage.jsp");
			rd.forward(request, response);
		} else {
			content = request.getParameter("content");
			writer = request.getParameter("writer");
//			modTime = request.getParameter("modTime");
			mid = Integer.parseInt(request.getParameter("messageId"));
//			message = new MessageProject(content, LocalDate.parse(modTime.getModTime()), mid);
			mDao.insertMessage(Message);
			response.sendRedirect("/jw/ch99/message/list");
		}
		break;
		
		}
	}
	}