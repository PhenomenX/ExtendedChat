package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.Role;
import com.epam.datalayer.data.Status;
import com.epam.datalayer.data.User;
import com.epam.pavel_romanenko.j12.ConfigurationManager;

public class UnkickCommand implements ActionCommand {

	public String execute(HttpServletRequest request, DAOFactory factory) {
		UserDAO userDAO = factory.getUserDAO();
		String page = null;
		HttpSession httpSession = request.getSession();
		String login = request.getParameter("username");
		if (!(httpSession.getAttribute("message") == null)
				|| httpSession.getAttribute("nick") == null) {
			page = ConfigurationManager.getProperty("path.page.login");
		} else {
			User user = new User(login, Status.KICK, Role.USER);
			page = ConfigurationManager.getProperty("path.page.main");
			userDAO.unkick(user);
		}
		return page;
	}
}
