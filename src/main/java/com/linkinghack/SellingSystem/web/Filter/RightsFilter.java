package com.linkinghack.SellingSystem.web.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.linkinghack.SellingSystem.dao.JdbcTools;

public class RightsFilter implements Filter {

	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest requ = (HttpServletRequest) request;

		request.setAttribute("type",0);
		//判断用户是否登陆
//		String userName = (String) session.getAttribute("userName");
//		String password = (String) session.getAttribute("password");
//		ServletContext context = requ.getServletContext();
//		if (userName!=null)
//		try {
//			if (jdbcTools.hasUser(userName, password))
//				context.setAttribute("hasUser", true);
//		}catch (Exception e) {
//			context.setAttribute("hasUser", false);
//		}
//
		
	}

	
	
}
