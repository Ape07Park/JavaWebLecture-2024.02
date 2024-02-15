package ch07_songDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/ch07/song/list")
public class Ex2_List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		title = (title == null || title.equals("")) ? "Tell Me" : title;
		
		String num_ = request.getParameter("num");
		int num = (num_ == null || num_.equals("")) ? 5 : Integer.parseInt(num_);
		
		String offset_ = request.getParameter("offset ");
		int offset = ( offset_ == null || offset_.equals("")) ? 0 : Integer.parseInt(offset_);
		
		SongDao sDao = new SongDao();
		List<Song> list = sDao.getSongList(title, num, offset);
		list.forEach(x -> System.out.println(x));
 		
//		RequestDispatcher rd = request.getRequestDispatcher("/ch07/songList.jsp");
//		request.setAttribute("list", list);
//		rd.forward(request, response); 
		
		String data = "";
		for (Song s:list) {
			data +="<tr>";
			data += "	<td>" + s.getSid() + "</td>";
			data += "	<td>" + s.getTitle() + "</td>";
			data += "	<td>" + s.getLyrics()+ "</td>";
			
			data +="</tr>";
			
		}
		
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>Song list</title>"
				+ "<style>"
				+ "	th, td {padding: 3px;}"
				+ "</style>"
				+ "</head>"
				+ "<body style=\"margin:50px;\">"
				+ "	<h1>Song list</h1>"
				+ "	<hr>"
				+ "	"
				+ "	<table border=\"1\">"
				+ "	<tr>"
				+ "	<th>sid</th>"
				+ "	<th>title</th>"
				+ "	<th>lyrics</th>"
				+ "	</tr>";
		
		html += data;
		
		html +="</table>"
				+ "</body>"
				+ "</html>";
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(html);
	}
}

	
	


