package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/bbs/product/insert", "/bbs/product/view"})
@MultipartConfig(
		fileSizeThreshold = 1 * 1024 *1024,		// 1 MB
		maxFileSize = 10 * 1024 * 1024,			// 10 MB
		maxRequestSize = 10 * 1024 * 1024				
)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String UPLOAD_PAth = "c:Temp/upload/bbs";    

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String[] uri = request.getRequestURI().split("/");
	String action = uri[uri.length - 1];
	String method = request.getMethod();
	HttpSession session = request.getSession();
	RequestDispatcher rd = null;
	
	seitch(action) {
		case "insert":
			
	}
  }
}