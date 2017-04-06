package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.datalayer.DAOFactory;
import com.epam.pavel_romanenko.j12.ConfigurationManager;

public class EmptyCommand implements ActionCommand {
	
	@Override
	public String execute(HttpServletRequest request, DAOFactory factory) {
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}
}