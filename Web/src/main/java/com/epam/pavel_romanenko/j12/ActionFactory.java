package com.epam.pavel_romanenko.j12;

import javax.servlet.http.HttpServletRequest;

import com.epam.pavel_romanenko.j12.command.ActionCommand;
import com.epam.pavel_romanenko.j12.command.CommandEnum;
import com.epam.pavel_romanenko.j12.command.EmptyCommand;

public class ActionFactory {
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction",
					action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}