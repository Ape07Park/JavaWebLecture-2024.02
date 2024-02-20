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

@WebServlet("/ch09/setSession")
public class Ex01_SetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 // ServletRequest를 상속 받은 HttpServletRequest
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기억공간(메모리) 생성
		HttpSession session = request.getSession();
		
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html; charset=utf-8");
//		out.print("<h1>isNew" + session.isNew());
		
		//  * 세션에 값 세팅(key, value 형태), value는 Object 타입 
		session.setAttribute("price", 12500); 
		session.setAttribute("uid", "james");
		String[] fruits = {"apple", "banana", "cherry"};
		session.setAttribute("fruits", fruits);
		
		
		// 로그인 지속시간 24시간으로 세팅 
		session.setMaxInactiveInterval(24 * 60 * 60); // 24시간 60분 60초 24시간 
		
//		session.invalidate();
		
		RequestDispatcher rd = request.getRequestDispatcher("/ch09/session.jsp"); // Setsession이라고 함 
		rd.forward(request, response);
		
		
	}

}
