package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import project.entity.Product;
import project.service.ImageUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@WebServlet({"/bbs/product/insert", "/bbs/product/view"})
@MultipartConfig(
		fileSizeThreshold = 1 * 1024 * 1024,			// 1 MB
		maxFileSize = 10 * 1024 * 1024,					// 10 MB
		maxRequestSize = 10 * 1024 * 1024
)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_PATH = "c:/Temp/upload/bbs";
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		// * 업로드는 별도의 장소에 한다 
		switch(action) {
		case "insert":
			if (method.equals("GET")) {
			//   화면 보여주기
				rd = request.getRequestDispatcher("/WEB-INF/view/product/insert.jsp");
				rd.forward(request, response);
			} else {
				String category = request.getParameter("category");
				String pname = request.getParameter("pname");
				String price_ = request.getParameter("price");
				int price = Integer.parseInt(price_);
				String description = request.getParameter("description");
				
// 				List<Part> parts = request.getParts(); 여러 파일 받아올 시
				// 파일 읽기(jsp에서 이미지 파일 받아오기)
				Part filePart = request.getPart("imgFile");
				String filename = filePart.getSubmittedFileName();// 파일 이름 구하기
				
				// 확장자 뽑아내기
				// 정규표현식이 되기 때문에
				String[] ext = filename.split("\\.");
				String extension = ext[ext.length - 1];
				String fname = category + System.currentTimeMillis() + "." + extension;
				// 파일 이름 정하기
				String path = UPLOAD_PATH + "/" + fname;
				
				// 쓰기
				filePart.write(path);
				
				Product product = new Product(category, pname, price, description, fname);
				rd = request.getRequestDispatcher("/WEB-INF/view/product/detail.jsp");
				request.setAttribute("product", product);
				rd.forward(request, response);
			}
			break;
			
		case "squareInsert":
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/product/squareInsert.jsp");
				rd.forward(request, response);
			} else {
				String category = request.getParameter("category");
				String pname = request.getParameter("pname");
				String price_ = request.getParameter("price");
				int price = Integer.parseInt(price_);
				String description = request.getParameter("description");
				
				Part filePart = request.getPart("imgFile");
				String filename = filePart.getSubmittedFileName();
				String path = UPLOAD_PATH + "/" + filename;
				filePart.write(path);
				
				ImageUtil imageUtil = new ImageUtil();
				String fname = imageUtil.squareImage(category, filename);
				
				Product product = new Product(category, pname, price, description, fname);
				rd = request.getRequestDispatcher("/WEB-INF/view/product/detail.jsp");
				request.setAttribute("product", product);
				rd.forward(request, response);
			}
			break;
			
		case "view":
			byte[] buffer = new byte[8*1024];		// 8 KB buffer
			String fname = request.getParameter("filename");
			String path = UPLOAD_PATH + "/" + fname;
			OutputStream os = response.getOutputStream();
			response.setContentType("text/html; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Content-disposition", "attachment; fileName=" +
								URLEncoder.encode(fname, "utf-8"));
			
			FileInputStream fis = new FileInputStream(path);
			while (true) {
				int count = fis.read(buffer);
				if (count == -1) {
					
					break;
				}
				os.write(buffer, 0, count);
			}
			fis.close(); 
			os.close();
			break;
		}
	}

}
