package com.epam;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.DBType;
import com.epam.datalayer.MessageDAO;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.Role;
import com.epam.datalayer.data.Status;
import com.epam.datalayer.data.User;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ChatTest {

	public void testChat() {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		User admin = new User("admin");
		User user = new User("nick", Status.LOGIN, Role.USER);
		userDAO.logIn(user);
		boolean isLogged = userDAO.isLogged(user);
		userDAO.kick(admin, user);
		boolean isKicked = userDAO.isKicked(user);
		Assert.assertEquals(isLogged, true);
		Assert.assertEquals(isKicked, true);
	}

}
