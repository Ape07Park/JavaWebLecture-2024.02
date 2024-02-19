package ch09_cookie_session;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;

@WebServlet("/ch09/setCookie")
public class Ex03_SetCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		
		// 쿠키 c1 생성- map 형식
		Cookie c1 = new Cookie("cookie-name", "cookie-value");
		
		// 쿠키의 특성 설정
		c1.setMaxAge(24 * 60 * 60); // 유효기간: 24 * 60 * 60 초 -> 1일
		
		// 도메인 설정
		c1.setDomain("c1");
		
		// 이런 식으로 쿠기 던져주면 다음부턴 가지고 들어옴
		response.addCookie(c1);
		
		String kMsg = URLEncoder.encode("한글 데이터", "utf-8");
		Cookie c2 = new Cookie("hangul-name", kMsg);
		c2.setMaxAge(-1); // -1은 브라우저가 닫히면 삭제됨
		c2.setDomain("c2");
		response.addCookie(c2);
		
		out.write("<h1>현재시간: " + new Date() + "/<h1>");
	}

	

}
