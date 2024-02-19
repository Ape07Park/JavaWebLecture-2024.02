package ch07_dao.kpop;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Sides;

import org.apache.el.parser.AstIdentifier;

import ch07_dao.City;

@WebServlet({ "/ch07/kpop/list", "/ch07/kpop/insertArtist", "/ch07/kpop/insertSong", 
	"/ch07/kpop/updateArtist","/ch07/kpop/updateSong", "/ch07/kpop/deleteArtist", 
	"/ch07/kpop/deleteSong"})
public class KpopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KpopDao kDao = new KpopDaoImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String requestUri = request.getRequestURI(); // 입력한 주소값을 받음
		String[] uri = requestUri.split("/");
		String action = uri[uri.length - 1];

		// 중복
		RequestDispatcher rd = null;

		switch (action) {
		case "list":
			// ** kDao의 메서드 안 씀
			List<Kpop> list = kDao.getKpopList();
			
			rd = request.getRequestDispatcher("/ch07/kpop/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
			break;

		case "insertArtist":
			if (method.equals("GET")) {
				// GET
				rd = request.getRequestDispatcher("/ch07/kpop/insertArtist.jsp");
				rd.forward(request, response);

			} else {
				// ** "/ch07/insertArtist.jsp의 양식에 맞춤"
				// debut 값을 받아야 함
				// POST
				String name = request.getParameter("name");
				// jsp debut랑 이름 안맞음
				String debut = request.getParameter("debut");

				// sid 안불러옴
				int sid = Integer.parseInt(request.getParameter("songId"));

				Artist artist = new Artist(name, LocalDate.parse(debut), sid);

				kDao.insertArtist(artist);

				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;

		case "updateArtist":
			if (method.equals("GET")) {
				int aid = Integer.parseInt(request.getParameter("aid"));
				Artist artist = kDao.getArtist(aid);
				rd = request.getRequestDispatcher("/ch07/kpop/updateArtist.jsp");
				request.setAttribute("artist", artist);
				rd.forward(request, response);
			} else {
				int aid = Integer.parseInt(request.getParameter("aid"));
				String name = request.getParameter("name");
				String debut = request.getParameter("debut");
				int sid = Integer.parseInt(request.getParameter("songId"));
				Artist artist = new Artist(aid, name, LocalDate.parse(debut), sid);
				kDao.updateArtist(artist);
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "deleteArtist":
			
				int aid = Integer.parseInt(request.getParameter("aid"));
				
				kDao.deleteArtist(aid);
				response.sendRedirect("/jw/ch07/kpop/list");
				break;

		case "insertSong":
			if (method.equals("GET")) {
				// GET
				rd = request.getRequestDispatcher("/ch07/kpop/insertSong.jsp");
				rd.forward(request, response);

			} else {
				// POST
				String title = request.getParameter("title");
				String lyrics = request.getParameter("lyrics");

				Song song = new Song(title, lyrics);
				kDao.insertSong(song);

				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;

		case "updateSong":
			if (method.equals("GET")) {

				// 사용자가 입력한 데이터를 가져옴
				int sid = Integer.parseInt(request.getParameter("sid"));

				Song song = kDao.getSong(sid);

				rd = request.getRequestDispatcher("/ch07/kpop/updateSong.jsp");
				request.setAttribute("song", song);
				rd.forward(request, response);

			} else {
				// POST
				int sid = Integer.parseInt(request.getParameter("sid"));
				String title = request.getParameter("title");
				// jsp debut랑 이름 안맞음
				String lyrics = request.getParameter("lyrics");

				Song song = new Song(sid, title, lyrics);
				kDao.updateSong(song);
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;

		case "deleteSong":
				int sid = Integer.parseInt(request.getParameter("sid"));
				kDao.deleteSong(sid);
				response.sendRedirect("/jw/ch07/kpop/list");
			
			break;

		}

	}
}
