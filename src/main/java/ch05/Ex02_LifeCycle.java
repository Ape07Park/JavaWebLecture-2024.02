package ch05;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ch05/lifecycle") // 이걸로 주소 변경 o  http://localhost:8080/jw/ch05/lifecycle
public class Ex02_LifeCycle extends HttpServlet {
	
	//  불려지면 init
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() method");
	}

	public void destroy() {
		System.out.println("destroy() method");
	}
	// 호출 - 분기 get/ get, post 혼재 시 service 사용
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() method");
		doGet(request, response);
	}
	// 주소창에서 칠 때는 get, form으로 보낼 시에만 post
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() method");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet() method stop");
	}

}
