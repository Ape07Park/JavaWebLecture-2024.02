package ch07_dao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ch07/city/insert") // insert.jsp에서 작업이 일어나면(서로 매핑되기에) 여기서 데이터 처리 즉 백엔드 단
public class Ex03_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	// 입력 폼 보여주기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/ch07/insert.jsp"); // 어느 위치에서 보여 줄 것인지
		rd.forward(request, response); // 웹 페이지 앞(front 단)에서 보여주기
	}
	
	// 사용자 입력 처리하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String countryCode = request.getParameter("countryCode");
		String district = request.getParameter("district");
		String population_ = request.getParameter("population");
		
		int population = (population_.equals("")) ? 0 : Integer.parseInt(population_);
		
		City city = new City(name, countryCode, district, population);
		CityDao cDao = new CityDao();
		cDao.insertCity(city);
		
		response.sendRedirect("/jw/ch07/city/list?district="+ district + "&num=30&offset=0");
	}

}
