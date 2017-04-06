package com.epam.datalayer.oracledb;

import java.sql.Connection;

import java.util.Formatter;
import java.util.List;

import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.Message;
import com.epam.datalayer.data.Role;
import com.epam.datalayer.data.Status;
import com.epam.datalayer.data.User;
import com.epam.ResourceManager;

public class OracleUserDAO implements UserDAO {
	private Connection connection;

	public OracleUserDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void logIn(User user) {
		Formatter formatter = new Formatter();
		formatter.format(ResourceManager.getRegExp("loginF"), user.getName());
		user.setStatus(Status.LOGIN);
		Message message = new Message(user, formatter.toString());
		OracleDBHandler.sendMessage(message, connection);

	}

	@Override
	public void logOut(User user) {
		Formatter formatter = new Formatter();
		formatter.format(ResourceManager.getRegExp("logoutF"), user.getName());
		user.setStatus(Status.LOGOUT);
		Message message = new Message(user, formatter.toString());
		OracleDBHandler.sendMessage(message, connection);
	}

	@Override
	public boolean isLogged(User user) {
		return OracleDBHandler.isLogged(user, connection);
	}

	@Override
	public void kick(User admin, User kickableUser) {
		Formatter formatter = new Formatter();
		formatter.format(ResourceManager.getRegExp("kickF"), admin.getName(), kickableUser.getName());
		kickableUser.setStatus(Status.KICK);
		Message message = new Message(kickableUser, formatter.toString());
		OracleDBHandler.sendMessage(message, connection);

	}

	@Override
	public void unkick(User user) {
		Formatter formatter = new Formatter();
		formatter.format(ResourceManager.getRegExp("unkickF"), user.getName());
		user.setStatus(Status.LOGOUT);
		Message message = new Message(user, formatter.toString());
		OracleDBHandler.sendMessage(message, connection);
	}

	@Override
	public boolean isKicked(User user) {
		return OracleDBHandler.isKicked(user, connection);
	}

	@Override
	public List<User> getAllLogged() {
		return OracleDBHandler.getAllLogged(connection);
	}

	public Role getRole(String nick) {
		return OracleDBHandler.getRole(nick, connection);
	}

	@Override
	public List<User> getAllKicked() {
		return OracleDBHandler.getAllKicked(connection);
	}
	

}
