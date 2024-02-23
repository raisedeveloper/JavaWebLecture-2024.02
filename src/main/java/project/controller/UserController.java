package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.User;
import project.service.UserService;
import project.service.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import ch07_dao.CityEX.City;

@WebServlet({ "/bbs/user/list", "/bbs/user/register", "/bbs/user/update", 
			  "/bbs/user/delete", "/bbs/user/login", "/bbs/user/logout" })
public class UserController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private UserService uSvc = new UserServiceImpl();

   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String[] uri = request.getRequestURI().split("/");
      String action = uri[uri.length - 1];
      String method = request.getMethod();
      HttpSession session = request.getSession();
      RequestDispatcher rd = null;
      
      String uid = null, pwd = null, pwd2 = null, hashedPwd = null, email = null, uname = null;
      String msg = null, url = null;
      User user = null;

      switch (action) {
      case "list": {
         String page_ = request.getParameter("page");
         int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
         session.setAttribute("currentUserPage", page);
         List<User>userList = uSvc.getUserList(page);
         request.setAttribute("userList", userList);
         
         // for pagination
         int totalUsers = uSvc.getUserCount();	// 유저의 명수 출력
         int totalPages = (int) Math.ceil(totalUsers * 1.0 / uSvc.count_per_page);
        		 // 유저 목록 페이지 출력 ex> 12일 때 2 페이지 출력
         List<String> pageList = new ArrayList<>();
         for (int i = 1; i <= totalPages; i++)
        	 pageList.add(String.valueOf(i));	// i 를 String 으로 바꿔줌
         request.setAttribute("pageList", pageList);

//         rd = request.getRequestDispatcher("/WEB-INF/view/user/list.jsp");
         rd = request.getRequestDispatcher("/WEB-INF/view/user/list.jsp");

         rd.forward(request, response);
         break;
      }
      case "register": {
         if (method.equals("GET")) {
//            rd = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
            rd = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
            rd.forward(request, response);
         } else {
            uid = request.getParameter("uid");
            pwd = request.getParameter("pwd");
            pwd2 = request.getParameter("pwd2");
            uname = request.getParameter("uname");
            email = request.getParameter("email");
            if (uSvc.getUserByUid(uid) != null) {
               rd = request.getRequestDispatcher("/WEB-INF/view/user/alertMsg.jsp");
               request.setAttribute("msg", "아이디가 중복입니다.");
               request.setAttribute("url", "/jw/bbs/user/register");
               rd.forward(request, response);
            } else if (pwd.equals(pwd2)) {
               user = new User(uid, pwd, uname, email);
               uSvc.registerUser(user);
               response.sendRedirect("/jw/bbs/user/list?page=1");
            } else {
               msg = "비밀번호가 일치하지 않습니다.";
               url = "/WEB-INF/view/user/register.jsp";
               rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
               request.setAttribute("msg", msg);
               request.setAttribute("url", url);
               rd.forward(request, response);
            }
         }
         break;
      }
      case "login": {
         if (method.equals("GET")) {
//            rd = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
            rd = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
            rd.forward(request, response);
         } else {
            uid = request.getParameter("uid");
            pwd = request.getParameter("pwd");
            int result = uSvc.login(uid, pwd);
            if (result == uSvc.correct_login) {
               user = uSvc.getUserByUid(uid);
               session.setAttribute("sessUid", uid);
               session.setAttribute("sessUname", user.getUname());
               msg = user.getUname() + "님 환영합니다.";
               url = "/jw/bbs/board/list?p=1"; // 초기화면
            } else if (result == uSvc.WRONG_PASSWORD) {
               msg = "패스워드가 틀립니다.";
               url = "/jw/bbs/user/login";
            } else {
               msg = "등록되지 않은 사용자입니다.";
               url = "/jw/bbs/user/login";
            }
            rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
            request.setAttribute("msg", msg);
            request.setAttribute("url", url);
            rd.forward(request, response);
         }
         break;
      }
      case "logout": {
         // session을 정리하면 된다.
         session.invalidate();
         response.sendRedirect("/jw/bbs/user/login");
         break;
      }
      case "update": {
         if (method.equals("GET")) {
            uid = request.getParameter("uid");
            user = uSvc.getUserByUid(uid);
//            rd = request.getRequestDispatcher("/WEB-INF/view/user/update.jsp");
            rd = request.getRequestDispatcher("/WEB-INF/view/user/update.jsp");
            request.setAttribute("user", user);
            rd.forward(request, response);
         } else {
            uid = request.getParameter("uid");
            pwd = request.getParameter("pwd");
            pwd2 = request.getParameter("pwd2");
            hashedPwd = request.getParameter("hashedPwd");
            uname = request.getParameter("uname");
            email = request.getParameter("email");
            if (pwd != null && pwd.equals(pwd2))
               hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            user = new User(uid, hashedPwd, uname, email);
            uSvc.updateUser(user);
            response.sendRedirect("/jw/bbs/user/list?page=1");
         }
         break;
      }
      case "delete": {
         uid = request.getParameter("uid");
         uSvc.deleteUser(uid);
         String sessUid = (String) session.getAttribute("sessUid");
         if (!sessUid.equals("admin"))
            session.invalidate();
         response.sendRedirect("/jw/bbs/user/list?page=1");
         break;
      }

      }
   }
}