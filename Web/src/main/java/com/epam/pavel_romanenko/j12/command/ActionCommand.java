package com.epam.pavel_romanenko.j12.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.datalayer.DAOFactory;

public interface ActionCommand {
	String execute(HttpServletRequest request, DAOFactory factory);
}