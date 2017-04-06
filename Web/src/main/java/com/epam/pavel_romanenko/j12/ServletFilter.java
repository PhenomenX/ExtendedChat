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
		if (!(httpSession.getAttribute("nick") == null)) { //если атрибут ник есть
			User user = new User(httpSession.getAttribute("nick").toString());
			//System.out.println("Nick is null");
			if (userDAO.isKicked(user)) {
				System.out.println("user blocked");
				httpSession.setAttribute("message", "Admin blocked you");
			} else {
				System.out.println("User not kicked");
				httpSession.setAttribute("message", null);
			}
		}
		//httpSession.setAttribute("nick", null);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		factory = DAOFactory.getInstance(DBType.ORACLE);
		messageDAO = factory.getMessageDAO();
		userDAO = factory.getUserDAO();
	}

}
