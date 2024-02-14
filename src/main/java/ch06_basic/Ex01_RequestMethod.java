package ch06_basic;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/ch06/requestMethod")
public class Ex01_RequestMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 
		PrintWriter out = res.getWriter();
		
		// /jw
		String contextPath = req.getContextPath();
		// get
		String method = req.getMethod();
		// jw/ch06/requestMethod
		String requestUri = req.getRequestURI();
		// serverName=localhost
		String serverName = req.getServerName();
		// servletPath=/ch06/requestMethod
		String servletPath = req.getServletPath();
		// pathInfo=null
		String pathInfo = req.getPathInfo();
		
		res.setContentType("text/html; charset=utf-8");
		
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"EUC-KR\">"
				+ "<title>Request Method</title>"
				+ "</head>"
				+ "<body>"
				+ "	<h1>HttpServletRequest의 다양한 메서드</h1>"
				+" <hr>"
				+ " <ul>"
				+ "      <li>contextPath=" + contextPath + "</li>"    
				+ "      <li>method=" + method + "</li>"    
				+ "      <li>requestUri=" + requestUri + "</li>"    
				+ "      <li>serverName=" + serverName + "</li>"    
				+ "      <li>servletPath=" + servletPath + "</li>"    
				+ "      <li>pathInfo=" + pathInfo + "</li>"    
				+ " </ul>"
				+ "</body>"
				+ "</html>";	
		out.print(html);
		
	}
}
