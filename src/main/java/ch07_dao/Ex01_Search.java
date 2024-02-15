package ch07_dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ch07/city/search")
public class Ex01_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/*
 * 		HTTP 요청의 파라미터 값인 id를 얻기 위해 사용
 */
		int id = (request.getParameter("id") == null) ? 2331 : Integer.parseInt(request.getParameter("id"));
		
		/*
		 *  getCity(id) 사용
		 */
		CityDao cDao = new CityDao();
		City city = cDao.getCity(id);
		System.out.println(city);
		
		/*
		 * UTF-8로 바꾸고, 응답으로 내보낼 출력 스트림을 얻기, 출력" 
		 */
//		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(city);
		
		
		/*
		  HTTP 프로토콜은 간단하게 얘기해서,

        클라이언트가 서버에 무언가(보통은 웹페이지)를 요청(request)하면,

        서버가 이 요청에 해당하는 것을 응답(response) 해주는 구조로 되어있습니다.
        

		HTTP 요청을 보낼 때,  파라미터(parameter)를 함께 끼워보낼 수 있습니다.

        가령, 로그인을 할 때, 로그인 폼(form : 양식)을 입력하고, 버튼을 누르죠.

        그러면, HTTP 요청 안에 폼 내용이 함께 끼워져서 서버로 날아가게 됩니다.

        로그인 폼 내용이 바로, HTTP 요청의 파라미터(parameter)가 되는 겁니다.


        이러한 HTTP 요청을 받은 서버는 이제 어떤 일을 할까요?

        파라미터로 날아온 로그인 폼을 일단 봐야지, 뭘하든 하겠죠.


        그러려면 일단 파라미터 값을 얻어내야 하는데...

		HTTP 요청의 파라미터 값을 얻기 위해 사용하는 것이 request.getParameter() 메쏘드입니다.

        가령, 로그인 폼에, ID를 입력하는 <input type="text" name="id">가 있었다면,

        서블릿에서 String strId = request.getParameter("id"); 와 같은 방식으로,

        클라이언트가 입력한 ID가 뭐였는지 알아낼 수 있습니다.
        
        

       로그인에 성공했다면, 이제 어떻게 할까요?

       클라이언트의 회원정보를 DB에서 읽어서 페이지에 뿌려주기로 합시다.

       그러려면, 서블릿은 회원정보를 JSP에게 보내줘야 합니다. 그래야 뿌리죠.

       그런데 어떻게???

	다른 곳으로 정보를 넘겨주기 위해서 request 객체의 속성(attribute)을 사용합니다.

	(더 정확하게는, 웹 애플리케이션 상에서 정보를 공유하기 위해서 속성을 사용합니다.)

		가령, 회원정보 중에서 '취미'를 JSP에게 넘겨주기 위해서,

        서블릿에서 request.setAttribute("hobby", strHobby); 로 속성을 집어넣고,

        JSP에서 <% String strHobby = (String)request.getAttribute("hobby"); %><% String hobby = (String)request.getAttribute("hobby"); %>로 속성을 얻는거죠.
		 */
	}

}
