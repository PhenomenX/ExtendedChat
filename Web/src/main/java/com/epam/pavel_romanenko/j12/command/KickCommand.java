package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.*;
import com.epam.pavel_romanenko.j12.ConfigurationManager;

public class KickCommand implements ActionCommand {
	public String execute(HttpServletRequest request, DAOFactory factory) {
		UserDAO userDAO = factory.getUserDAO();
		String page = null;
		HttpSession httpSession = request.getSession();
		if (!(httpSession.getAttribute("message") == null) || httpSession.getAttribute("nick") == null) {
			page = ConfigurationManager.getProperty("path.page.login");
		} else {
		String login = request.getParameter("username");
		String adminLogin = httpSession.getAttribute("nick").toString();
		User admin = new User(adminLogin, Status.LOGIN, Role.ADMIN);
		User kickableUser = new User(login, Status.KICK, Role.USER);
		page = ConfigurationManager.getProperty("path.page.main");
		userDAO.kick(admin, kickableUser);
		}
		return page;
	}
}
