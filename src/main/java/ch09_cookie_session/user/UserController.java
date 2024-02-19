package ch09_cookie_session.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ch07_dao.kpop.Artist;
import ch07_dao.kpop.Song;

@WebServlet({ "/ch09/user/list", "/ch09/user/register", "/ch09/user/update", "/ch09/user/delete", "/ch09/user/login",
		"/ch09/user/logout" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uSvc = new UserServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		HttpSession session = request.getSession();
				
		String requestUri = request.getRequestURI(); // 입력한 주소값을 받음
		String[] uri = requestUri.split("/");
		String action = uri[uri.length - 1];
		RequestDispatcher rd = null;
		String uid = null, pwd = null, pwd2 = null, uname = null, email = null;
		String msg = "",  url = "";
		User user = null;
		switch (action) {
		case "list":

			// 입력값 받기 (page 값을 넣을 수 있도록 함 입력값 받기)
			String page_ = request.getParameter("page");
			int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_); // 입력값 받기
			List<User> list = uSvc.getUserList(page);

			// 내용물 안나오면 size 찍어보기
			System.out.println(list.size());

			// 모델에서 가져오기: "list"에 list 값 세팅
			request.setAttribute("list", list);

			// viewer로 보내기
			rd = request.getRequestDispatcher("/ch09/user/list.jsp");
			// ** 앞에 보여줄려면 꼭 필요
			rd.forward(request, response);
			break;

		case "login":
			if (method.equals("GET")) {
				// 보여주는 거 
				rd = request.getRequestDispatcher("/ch09/user/login.jsp");
				rd.forward(request, response);
				// 이거 작동 x 
			} else {
				// 보여주는 거에서 파라미터 가져오기
				 uid = request.getParameter("uid");
				 pwd = request.getParameter("pwd");
				 int result = uSvc.login(uid, pwd);
				 
				 // * 재대로 된 결과 나오면 session에다가 값 세팅함 
				 if (result == uSvc.CORRECT_LOGIN) {
					 user = uSvc.getUserByUid(uid);
					 session.setAttribute("sessUid", uid);
					 session.setAttribute("sessUname", user.getUname());
					 
					 msg = user.getUname() + "님 환영합니다";
					 // list 화면으로 감
					 url = "/jw/ch09/user/list?page=1";
				
					 // 이거 작동 x 
				 }else if (result == uSvc.WRONG_PASSWORD) {
					 msg = "패스워드가 틀림";
					 url = "/jw/ch09/user/login";
				 }else {
					 msg = "아이디가 틀림";
					 url = "/jw/ch09/user/login";
				 }
				 rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
				 request.setAttribute("msg", msg);
				 request.setAttribute("url", url);
				 rd.forward(request, response);
			}
			break;
			
		case "logout":
			// * session 지우면 로그아웃 됨 
			session.invalidate();
			response.sendRedirect("/jw/ch09/user/list?page=1");
			break;
			
		case "register":
			if(method.equals("GET")) {
				
			}else {
				
			}
			break;
		}

	}
}
