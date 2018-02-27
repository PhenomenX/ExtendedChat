package com.epam.pavel_romanenko.j12;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.DBType;
import com.epam.datalayer.MessageDAO;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.User;

public class ServletFilter implements Filter {
	private DAOFactory factory;
	private MessageDAO messageDAO;
	private UserDAO userDAO;

	public ServletFilter() {
	}

	public void destroy() {
		factory = null;
		messageDAO = null;
		userDAO = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();
		String nick = defineNick(httpSession, httpRequest);
		if (nick != null) {
			User user = new User(nick);
			if (userDAO.isKicked(user)) {
				System.out.println(nick + " blocked");
				httpSession.removeAttribute("nick");
				httpResponse.sendRedirect(
						httpRequest.getContextPath() + ConfigurationManager.getProperty("path.page.login"));
				httpSession.setAttribute("message", "User " + nick + " is blocked");
			} else {
				System.out.println("User not kicked");
				httpSession.setAttribute("message", null);
				chain.doFilter(request, response);
			}
		} else {
			System.out.println("What the fuck?");
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		factory = DAOFactory.getInstance(DBType.ORACLE);
		messageDAO = factory.getMessageDAO();
		userDAO = factory.getUserDAO();
	}

	private String defineNick(HttpSession httpSession, HttpServletRequest httpRequest) {
		if (httpSession.getAttribute("nick") != null) {
			return httpSession.getAttribute("nick").toString();
		} else
			return null;
	}

}
