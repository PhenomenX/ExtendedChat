package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.MessageDAO;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.Message;
import com.epam.datalayer.data.Role;
import com.epam.datalayer.data.Status;
import com.epam.datalayer.data.User;
import com.epam.pavel_romanenko.j12.ConfigurationManager;

public class MessageCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "nick";

	public String execute(HttpServletRequest request, DAOFactory factory) {
		MessageDAO messageDAO = factory.getMessageDAO();
		HttpSession httpSession = request.getSession();
		String page = null;
		if (!(httpSession.getAttribute("message") == null) || httpSession.getAttribute(PARAM_NAME_LOGIN) == null) {
			page = ConfigurationManager.getProperty("path.page.login");
		} else {
			String login = httpSession.getAttribute(PARAM_NAME_LOGIN)
					.toString();
			Role role;
			String text = request.getParameter("text");
			role = Role.valueOf(httpSession.getAttribute("role").toString()
					.toUpperCase());
			messageDAO.sendMessage(new Message(null, new User(login,
					Status.MESSAGE, role), text));
			page = ConfigurationManager.getProperty("path.page.main");
		}
		return page;
	}
}
