package com.epam;

import java.sql.Timestamp;
import java.util.List;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.DBType;
import com.epam.datalayer.DBTypeException;
import com.epam.datalayer.MessageDAO;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.Message;
import com.epam.datalayer.data.Role;
import com.epam.datalayer.data.Status;
import com.epam.datalayer.data.User;

public class Tester {

	/**
	 * Class for test methods of DAO
	 */
	public static void main(String[] args) {
		try {
			DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
			MessageDAO messageDAO = factory.getMessageDAO();
			UserDAO userDAO = factory.getUserDAO();
			User admin = new User("admin");
			// sendMessage()
//			Timestamp date = Timestamp.valueOf("2016-10-10 12:13:52.0");
			User newUser = new User("jack", Status.LOGIN, Role.USER);
//			Message message = new Message(date, newUser, "aloha");
//			messageDAO.sendMessage(message);
//			System.out.printf("Test of method 'sendMessage'. User"
//					+ " - %s, message - 'aloha'. Result message:\n%s\n\n",
//					newUser.getName(), messageDAO.getLast(1));
		
//			// logout()
//			userDAO.logOut(newUser);
//			System.out.printf("Test of method 'logout'. User"
//					+ " - %s. Result message:\n%s\n\n", newUser.getName(),
//					messageDAO.getLast(1));
//			// login()
//			userDAO.logIn(newUser);
//			System.out.printf("Test of method 'login'. User"
//					+ " - %s. Result message:\n%s\n\n", newUser.getName(),
//					messageDAO.getLast(1));
//			// isLogged()
//			User user = new User("nick");
//			boolean isLogged = userDAO.isLogged(user);
//			System.out.printf(
//					"Test of method 'isLogged'. User - %s. Result:\n%s\n\n",
//					user.getName(), isLogged);
//			// isKicked()
//			boolean isKicked = userDAO.isKicked(user);
//			System.out.printf(
//					"Test of method 'isKicked'. User - %s. Result:\n%s\n\n",
//					user.getName(), isKicked);
//			// getRole()
//			System.out.printf(
//					"Test of method 'getRole'. User - %s. Result:\n%s\n\n",
//					newUser.getName(), userDAO.getRole(newUser.getName()));
//
//			// kick()
//			userDAO.kick(admin, newUser);
//			System.out.printf("Test of method 'kick'. User"
//					+ " - %s, Admin - '%s'. Result message:\n%s\n\n",
//					newUser.getName(), admin.getName(), messageDAO.getLast(1));
//			// unKick()
//			userDAO.unkick(newUser);
//			System.out.printf("Test of method 'unKick'. User"
//					+ " - %s. Result message:\n%s\n\n", newUser.getName(),
//					messageDAO.getLast(1));
//			// getAllLogged()
//			List<User> userList = userDAO.getAllLogged();
//			System.out
//					.println("Test of method 'getAllLogged'. All logged users:");
//			for (User onlineUser : userList) {
//				System.out.println(onlineUser);
//			}
			// getLast()
//			int count = 3;
//			List<Message> messages = messageDAO.getLast(count);
//			System.out.printf("\nTest of method 'getLast(%d)':\n", count);
//			for (Message messageLast : messages) {
//				System.out.println(messageLast);
//			}

		} catch (DBTypeException e) {
			e.printStackTrace();
		}
	}
}
