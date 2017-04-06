package com.epam.pavel_romanenko.j12;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.DBType;
import com.epam.datalayer.MessageDAO;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.*;

@WebListener
public class SessionListener implements HttpSessionListener {

	public SessionListener() {
	}

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		HttpSession httpSession = httpSessionEvent.getSession();
		httpSession.setMaxInactiveInterval(300);
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		HttpSession httpSession = httpSessionEvent.getSession();
		if (!(httpSession.getAttribute("nick") == null)) {
			String nick = httpSession.getAttribute("nick")
					.toString();
			if (!(userDAO.isKicked(new User(nick)))) {
				Role role = Role.valueOf(httpSession.getAttribute("role")
						.toString().toUpperCase());
				System.out.println((httpSession.getAttribute("nick")));
				User user = new User(httpSession.getAttribute("nick")
						.toString(), Status.LOGOUT, role);
				userDAO.logOut(user);
			}
		}
	}
}
