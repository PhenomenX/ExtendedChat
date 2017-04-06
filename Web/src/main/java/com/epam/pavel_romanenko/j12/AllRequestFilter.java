package com.epam.pavel_romanenko.j12;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.DBType;
import com.epam.datalayer.MessageDAO;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.User;

public class AllRequestFilter implements Filter {
	private DAOFactory factory;
	private MessageDAO messageDAO;
	private UserDAO userDAO;

	public AllRequestFilter() {
	}

	public void destroy() {
		factory = null;
		messageDAO = null;
		userDAO = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String page = "/controller";
//		HttpSession httpSession = httpRequest.getSession();
//		User user = new User(httpSession.getAttribute("nick").toString());
//		System.out.println(httpSession.getAttribute("nick").toString());
//		System.out.println(userDAO.isKicked(user));
//		if (userDAO.isKicked(user)) {
//			page = ConfigurationManager.getProperty("path.page.login");
//			httpSession.setAttribute("message", "Admin removed you");
//		}
//		if (!(userDAO.isLogged(user))) {
//			page = ConfigurationManager.getProperty("path.page.login");
//			httpSession.setAttribute("message", "Log in, please");
//		}
		httpResponse.sendRedirect(httpRequest.getContextPath() + page);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		factory = DAOFactory.getInstance(DBType.ORACLE);
		messageDAO = factory.getMessageDAO();
		userDAO = factory.getUserDAO();
	}

}
