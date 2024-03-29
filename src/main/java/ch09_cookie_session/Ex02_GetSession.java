package ch09_cookie_session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/ch09/getSession")
public class Ex02_GetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 세팅할 때 Objcet 타입으로 세팅함 
		int price = (Integer) session.getAttribute("price");
		String uid = (String) session.getAttribute("uid");
		System.out.println("price: " + price + ","+ "uid: " + uid);
		
		String[] fruits = (String[]) session.getAttribute("fruits");
		System.out.println(Arrays.toString(fruits));
		
		// session ID 가져오기
		String sessionId = session.getId();
		int interval = session.getMaxInactiveInterval();
		
		// 로그아웃 세션 무효화 - removeSetAttribute
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		
		out.print("<h1>price: " + price + ","+ "uid: " + uid+ "</h1>");
		out.print("<h1>" + Arrays.toString(fruits)+ "</h1><br>");
		out.print("<h1>isNew" + session.isNew());
		out.print("<h1>id: " + sessionId);
		out.print("<h1>MaxInactiveInterval:" + interval + "초");
		
	}


}
