package ch06_basic;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 경로 잘못 잡거나 @WebServlet의 괄호안에 잘못 넣으면 톰캣 에러 발생
@WebServlet("/ch06/requestMethod2")
public class Ex01_RequestMethod2 extends HttpServlet {
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
		
		req.setAttribute("contextPath", contextPath);
		req.setAttribute("method", method);
		req.setAttribute("requestUri", requestUri);
		req.setAttribute("serverName", serverName);
		req.setAttribute("servletPath", servletPath);
		req.setAttribute("pathInfo", pathInfo);
		
		// 
		RequestDispatcher rd = req.getRequestDispatcher("/ch06/requestMethod.jsp");
		rd.forward(req, res);
	}
}
