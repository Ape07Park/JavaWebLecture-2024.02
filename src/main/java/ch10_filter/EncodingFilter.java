package ch10_filter;

import jakarta.servlet.Filter; 
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;

@WebFilter("/ch/10/*")
public class EncodingFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EncodingFilter() {
        super();
        System.out.println("생성자");
    }

	
	public void destroy() {
		System.out.println("destroy()");
	}

	// 필요한 작업을 코딩하는 부분, 가장 많이 사용 
	// chain: 여러 개 필터 매달기 o
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8"); // 인코딩 작업
		
		System.out.println("doFilter()");
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init()");
	}

}
