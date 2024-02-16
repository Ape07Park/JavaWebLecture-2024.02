package ch07_dao.kcity;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ch07_dao.City;
import ch07_dao.CityDao;

//@WebServlet("/ch07/kcity/*")
@WebServlet({ "/ch07/kcity/list", "/ch07/kcity/insert", "/ch07/kcity/update", "/ch07/kcity/delete",
		"/ch07/kcity/wrong" })

public class Kcitycontroller extends HttpServlet { // HttpServlet 추상 클래스
	private static final long serialVersionUID = 1L;
	private CityDao cDao = new CityDao();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String requestUri = request.getRequestURI(); // 입력한 주소값을 받음
		String[] uri = requestUri.split("/");
		String action = uri[uri.length - 1];

		// 중복되는 변수는 위에 만듦
		RequestDispatcher rd = null;
		String district = null;
		String name = null;
		String countryCode = null;
		String population_ = null;
		int population = 0;
		int id = 0;
		City city = null;
		String[] districts = "Cheju,Chollabuk,Chollanam,Chungchongbuk,Chungchongnam,Inchon,Kang-won,Kwangju,Kyonggi,Kyongsangbuk,Kyongsangnam,Pusan,Seoul,Taegu,Taejon"
				.split(",");

		switch (action) {
		case "list":
			district = request.getParameter("district");

			district = (district == null || district.equals("")) ? "Kyonggi" : district;

			String num_ = request.getParameter("num");
			int num = (num_ == null || num_.equals("")) ? 10 : Integer.parseInt(num_);

			String offset_ = request.getParameter("offset ");
			int offset = (offset_ == null || offset_.equals("")) ? 0 : Integer.parseInt(offset_);

			List<City> list = cDao.getCityList(district, num, offset);
			rd = request.getRequestDispatcher("/ch07/kcity/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
			break;

		case "insert":
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/ch07/kcity/insert.jsp");

				request.setAttribute("districts", districts);

				rd.forward(request, response);

			} else {
				name = request.getParameter("name");
				countryCode = request.getParameter("countryCode");
				district = request.getParameter("district");
				population_ = request.getParameter("population");

				population = (population_.equals("")) ? 0 : Integer.parseInt(population_);

				city = new City(name, countryCode, district, population);
				cDao.insertCity(city);

				response.sendRedirect("/jw/ch07/kcity/list?district=" + district + "&num=30&offset=0");
			}
			break;

		case "update":
			if (method.equals("GET")) {
				// 수정 버튼을 통해서만 들어오기에 굳이 거르는 작업 필요 x
				id = Integer.parseInt(request.getParameter("id"));
				city = cDao.getCity(id);
				
				rd = request.getRequestDispatcher("/ch07/kcity/update.jsp");
				request.setAttribute("districts", districts);
				request.setAttribute("city", city);
				rd.forward(request, response); // 화면에 값 나오게 함
			} else {
				id = Integer.parseInt(request.getParameter("id"));

				name = request.getParameter("name");
				countryCode = request.getParameter("countryCode");
				district = request.getParameter("district");
				population_ = request.getParameter("population");

				population = (population_.equals("")) ? 0 : Integer.parseInt(population_);

				city = new City(id, name, countryCode, district, population);
				cDao.updateCity(city);

				response.sendRedirect("/jw/ch07/kcity/list?district=" + district + "&num=30&offset=0");
			}
			break; // 페이지 이동은 딱 1번만 발생해야 해서

		case "delete":
			id = Integer.parseInt(request.getParameter("id"));
			cDao.deleteCity(id);
			response.sendRedirect("/jw/ch07/kcity/list?district=Kyonggi&num=30&offset=0");
			break;

		default:
			rd = request.getRequestDispatcher("/ch07/kcity/alertMsg.jsp");
			// "/ch07/kcity/alertMsg.jsp" 와 연결 및 msg, url 과 대응 즉 매핑
			request.setAttribute("msg", "잘못된 접근");
			request.setAttribute("url", "/jw/ch07/kcity/list");
			rd.forward(request, response);

		}

	}

}
