package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.DBType;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.Role;
import com.epam.datalayer.data.Status;
import com.epam.datalayer.data.User;
import com.epam.pavel_romanenko.j12.ConfigurationManager;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "nick";
	DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
	UserDAO userDAO = factory.getUserDAO();

	@Override
	public String execute(HttpServletRequest request, DAOFactory factory) {
		UserDAO userDAO = factory.getUserDAO();
		String page = ConfigurationManager.getProperty("path.page.login");
		String login = request.getParameter(PARAM_NAME_LOGIN);
		Role role;
		HttpSession httpSession = request.getSession();
		User user = new User(login);
		System.out.println(userDAO.isKicked(user));
		if (userDAO.isKicked(user)) {
		} else {
			httpSession.setAttribute("nick", login);
			if (login.matches(".*@epam.com")) {
				role = Role.ADMIN;
				httpSession.setAttribute("role", "admin");
			} else {
				role = Role.USER;
				httpSession.setAttribute("role", "user");
			}
			userDAO.logIn(new User(login, Status.MESSAGE, role));
			page = ConfigurationManager.getProperty("path.page.main");
		}
		System.out.println(page);
		return page;
	}
}
