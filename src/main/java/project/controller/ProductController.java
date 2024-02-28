package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import project.entity.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet({ "/bbs/product/insert", "/bbs/product/view" })
// 파일 업로드 용량 제한
@MultipartConfig(fileSizeThreshold = 1 * 1024 * 1024, // 1 MB
		maxFileSize = 10 * 1024 * 1024, // 10 MB
		maxRequestSize = 10 * 1024 * 1024)

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 파일 업로드 위치
	public static final String UPLOAD_PATH = "c:/Temp/upload/bbs";

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();

		HttpSession session = request.getSession(); // session 받기

		String requestUri = request.getRequestURI(); // 입력한 주소값을 받음

		String[] uri = requestUri.split("/"); // '/' 기준으로 나누기

		String action = uri[uri.length - 1]; // url 길이의 -1 /bbs/user/register의 -1 인 "register"를 본다

		// 반복된 변수 세팅
		RequestDispatcher rd = null;

		switch (action) {
		case "insert":
			if (method.equals("GET")) {
				//   화면 보여주기
				rd = request.getRequestDispatcher("/WEB-INF/view/product/insert.jsp");
				rd.forward(request, response);
				
			}else {
				String category = request.getParameter("category");
				String pname = request.getParameter("pname");
				String price_ = request.getParameter("price");
				int price = Integer.parseInt(price_);
				String description = request.getParameter("description");
				
				//List<Part> parts = request.getParts(); // 파일 여러개 받아오기
				Part filePart = request.getPart("imgFile");	// 파일 읽기(jsp에서 이미지 파일 받아오기
				String filename = filePart.getSubmittedFileName();  // 파일 이름 구하기
				String[] ext = filename.split("\\.");
				String extension = ext[ext.length - 1];
				String fname = category + System.currentTimeMillis() + "." + extension;
				String path = UPLOAD_PATH + "/" + fname;
				filePart.write(path);
				
				Product product = new Product(category, pname, price, description, fname);
				rd = request.getRequestDispatcher("/WEB-INF/view/product/detail.jsp");
				request.setAttribute("product", product);
				rd.forward(request, response);
				
				}
			break;
			
		case "view":
			byte[] buffer = new byte[8*1024]; // 8 kB buffer
			String fname = request.getParameter("filename");
			String path = UPLOAD_PATH + "/" + fname;
			OutputStream os = response.getOutputStream();
			response.setContentType("text/html; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Content-disposition", "attachment; fileName=" + 
													URLEncoder.encode(fname, "utf-8"));
			
			FileInputStream fis = new FileInputStream(path);
			while (true) {
				int count = fis.read(buffer);
				if (count == -1) {
					break;
				}
				os.write(buffer, 0, count);
			}
			fis.close();
			os.close();
			break;
		}
	}
}