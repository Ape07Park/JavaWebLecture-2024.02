package ch05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Ex01_FirstServlet
 */
@WebServlet("/hello")
public class Ex01_FirstServlet extends HttpServlet {
	
	
	/*
	 * get, post 둘 다 싫을 시 service로 처리
	 */
	
	// Get 방식의 요청이 왔을 때 처리해주는 코드 
	// request: 요청 받음  response: 요청에 대한 반응
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"EUC-KR\">"
				+ "<title>첫번째 서블릿</title>"
				+ "</head>"
				+ "<body>"
				+ "	<h1>자바 서블릿에서 작성한 코드</h1>"
				+ "</body>"
				+ "</html>";	
		
		// 한글 깨짐 방지 
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(html);
		}

	
	 //Post 요청이 왔을 때 처리해주는 코드 
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
