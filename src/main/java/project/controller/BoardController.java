package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Board;
import project.entity.Reply;
import project.service.BoardService;
import project.service.BoardServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/bbs/board/list", "/bbs/board/insert", "/bbs/board/update", "/bbs/board/delete", "/bbs/board/detail"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService bSvc = new BoardServiceImpl();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		String title = "", content = "", sessUid = "", field="", query = "", page_="";
		Board board = null; 
		int bid, page= 0; 
	      
	    switch(action) {
	    case "list":		// /jw/bbs/board/list?p=1&f=title&q=검색
	    	page_= request.getParameter("p");
	    	field= request.getParameter("f");
	    	query= request.getParameter("q");
	    	page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
	    	session.setAttribute("currentBoardPage", page);
	    	field = (field == null || field.equals("")) ? "title" : field;
	    	query = (query == null || query.equals("")) ? "" : query;
	    	session.setAttribute("field", field);
	    	session.setAttribute("query", query);
	    	List<Board> boardList = bSvc.getBoardList(page, field, query);
	    	request.setAttribute("boardList", boardList);
    	  
	    	// for pagination
	    	int totalItems = bSvc.getBoardCount(field, query);	// 보드의 갯수 출력
	    	int totalPages = (int) Math.ceil(totalItems * 1.0 / bSvc.COUNT_PER_PAGE);
	    	// 유저 목록 페이지 출력 ex> 12일 때 2 페이지 출력
	    	List<String> pageList = new ArrayList<>();
	    	for (int i = 1; i <= totalPages; i++)
	    		pageList.add(String.valueOf(i));	// i 를 String 으로 바꿔줌
	      	request.setAttribute("pageList", pageList);
		  
	    	rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
	    	rd.forward(request, response);
	    	break;
    	  
    	case "insert":
			sessUid = (String) session.getAttribute("sessUid");
			if (sessUid == null || sessUid.equals("")) {
			response.sendRedirect("/jw/bbs/user/login");
			break;
			}
			    	  
			if(method.equals("GET")) {
			rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");	
			// - 오류 코드 - sendRedirect와 forward 중 한번만 쓸 수 있음. 왔다 갔다 하는 코드 쓰면 오류남.
			rd.forward(request, response);
			} else {
			title = request.getParameter("title");
			content= request.getParameter("content");
			board = new Board(title, content, sessUid);
			bSvc.insertBoard(board);
			response.sendRedirect("/jw/bbs/board/list?p=1");
			}
			break;
    	  
    	case "detail":
			bid = Integer.parseInt(request.getParameter("bid"));	// 부를 수 있는 방법 == 클릭 (안전장치를 했으니 그냥 쓰면 됨)
			bSvc.increaseViewCount(bid);		// 자기 글 일때 Count 세지 않는 기능 - 나중에 구현!! 2024-02-22 이 시점엔 미구현
			  
			board = bSvc.getBoard(bid);
			request.setAttribute("board", board);
			  
			List<Reply> replyList = null;		// 댓글 목록도 필요 - 나중에 구현!!! 2024-02-22 이 시점엔 미구현
			request.setAttribute("replyList", replyList);
			  
			rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
			rd.forward(request, response);
			break;
	  
	  
    	case "delete":
			bid = Integer.parseInt(request.getParameter("bid"));
			bSvc.deleteBoard(bid);
			page = (Integer) session.getAttribute("currentBoardPage");
			field = (String) session.getAttribute("field");
			query = (String) session.getAttribute("query");
			query = URLEncoder.encode(query, "utf-8");
			response.sendRedirect("/jw/bbs/board/list?p=" + page + "&f=" + field + "&q=" + query);
			break;   
		
    	case "update":
    		if (method.equals("GET")) {
    			bid = Integer.parseInt(request.getParameter("bid"));
    			board = bSvc.getBoard(bid);
    			request.setAttribute("board", board);
    			rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
    			rd.forward(request, response);
    		} else {
    			bid = Integer.parseInt(request.getParameter("bid"));
    			title = request.getParameter("title");
    			content = request.getParameter("content");
    			board = new Board(bid, title, content);
    			
    			bSvc.updateBoard(board);
    			response.sendRedirect("/jw/bbs/board/detail?bid=" + bid);
    		}
    		break;
    	}
	}
}