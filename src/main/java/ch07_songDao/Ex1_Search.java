package ch07_songDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ch07/song/search")
public class Ex1_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = (request.getParameter("sid") == null) ? 101: Integer.parseInt(request.getParameter("sid"));
		
		SongDao sDao = new SongDao();
		Song song = sDao.getSong(sid);
		System.out.println(song);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(song);
	}

	

}
