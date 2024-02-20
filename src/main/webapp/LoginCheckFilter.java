package ch10_filter;

import jakarta.servlet.Filter; 
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
 
@WebFilter("/ch09/user/list")
public class LoginCheckFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//  HttpServletRequest타입의 request, HttpServletResponse타입의 response 생성, 이유는 servlet에서 http~~를 쓸거니 
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session =  httpRequest.getSession();
		
		String sessUid = (String) session.getAttribute("sessUid");
		
		if(sessUid == null || sessUid.equals("")) {
			httpResponse.sendRedirect("/jw/ch09/user/login");
		}
		chain.doFilter(request, response);
	}


}
