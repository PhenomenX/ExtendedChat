package com.epam.pavel_romanenko.j12;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.datalayer.DAOFactory;
import com.epam.datalayer.DBType;
import com.epam.datalayer.MessageDAO;
import com.epam.datalayer.UserDAO;
import com.epam.datalayer.data.User;
import com.epam.pavel_romanenko.j12.command.ActionCommand;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory factory;
	private MessageDAO messageDAO;
	private UserDAO userDAO;

	public FrontController() {
		super();
	}

	/**
	 * The method is used to get the factories when the controller is
	 * initialized
	 */
	@Override
	public void init() {
		factory = DAOFactory.getInstance(DBType.ORACLE);
		messageDAO = factory.getMessageDAO();
		userDAO = factory.getUserDAO();
	}

	/**
	 * The method is used for processing get requests
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		generateData(httpSession);
		String page = null;
		if (httpSession.getAttribute("nick") == null) {
			page = ConfigurationManager.getProperty("path.page.login");
		} else {
			page = ConfigurationManager.getProperty("path.page.main");
		}
		httpSession.setAttribute("message", null);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	/**
	 * The method is used for processing post requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		ActionFactory client = new ActionFactory();
		HttpSession httpSession = request.getSession();
		ActionCommand command = client.defineCommand(request);
		page = command.execute(request, factory);
		generateData(httpSession);
		if (page != null) {
			response.sendRedirect(request.getContextPath() + page);
		} else {
			page = ConfigurationManager.getProperty("path.page.login");
			request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
			response.sendRedirect(request.getContextPath() + page);
		}

	}

	/**
	 * Method using for generate and update data in main page
	 */
	private void generateData(HttpSession httpSession) {
		httpSession.setAttribute("messages", messageDAO.getLast(10));
		httpSession.setAttribute("users", userDAO.getAllLogged());
		httpSession.setAttribute("kickedUsers", userDAO.getAllKicked());
	}

	/**
	 * The method is used for logouting all users when the controller is
	 * destroyed
	 */
	public void destroy() {
		List<User> userList = userDAO.getAllLogged();
		for (User user : userList) {
			userDAO.logOut(user);
		}
	}
}
