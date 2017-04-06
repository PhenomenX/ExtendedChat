package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.Role;
import com.epam.datalayer.data.Status;
import com.epam.datalayer.data.User;
import com.epam.pavel_romanenko.j12.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "nick";

	@Override
	public String execute(HttpServletRequest request, DAOFactory factory) {
		UserDAO userDAO = factory.getUserDAO();
		Role role;
		String page = ConfigurationManager.getProperty("path.page.login");;
		HttpSession httpSession = request.getSession();
		if (!(httpSession.getAttribute("message") == null) || httpSession.getAttribute(PARAM_NAME_LOGIN) == null) {
			page = ConfigurationManager.getProperty("path.page.login");
		} else {
		String login = httpSession.getAttribute(PARAM_NAME_LOGIN).toString();
		role = Role.valueOf(httpSession.getAttribute("role").toString().toUpperCase());
		userDAO.logOut(new User(login, Status.LOGOUT, role));
		page = ConfigurationManager.getProperty("path.page.login");
//		request.getSession().invalidate();
		}
		return page;
	}
}
