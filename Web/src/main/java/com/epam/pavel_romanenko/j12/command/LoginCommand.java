package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.*;
import com.epam.datalayer.data.*;
import com.epam.pavel_romanenko.j12.ConfigurationManager;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "nick";

	@Override
	public String execute(HttpServletRequest request, DAOFactory factory) {
		UserDAO userDAO = factory.getUserDAO();
		String page = ConfigurationManager.getProperty("path.page.login");
		String login = request.getParameter(PARAM_NAME_LOGIN);
		Role role;
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("nick", login);
//		if(userDAO.isKicked(new User(login))){
//			httpSession.setAttribute("message", "Admin blocked you");
//		} else if(userDAO.isLogged(new User(login))){
//			httpSession.setAttribute("message", "This user is exist");
//		}
//		else{	
		if(login.matches(".*@epam.com")){
			role = Role.ADMIN;
			httpSession.setAttribute("role", "admin");		
		}
		else {
			role = Role.USER;
			httpSession.setAttribute("role", "user");	
		}		
		userDAO.logIn(new User(login, Status.MESSAGE, role));
		page = ConfigurationManager.getProperty("path.page.main");	
//		}
		System.out.println(page);
		return page;
	}
}
