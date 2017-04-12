package com.iteachcoding.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iteachcoding.web.dao.PersonDao;
import com.iteachcoding.web.dao.impl.PersonDaoException;
import com.iteachcoding.web.dao.impl.PersonDaoImpl;
import com.iteachcoding.web.util.WorkbookUtility;

/**
 * Servlet implementation class PopulateDatabaseController
 */
@WebServlet("/PopulateDatabase")
public class PopulateDatabaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	  String target = null;
    
    try {
      
      final String filePath = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);
      final PersonDao personDao = new PersonDaoImpl();
      
      personDao.populate(filePath);
      
      request.setAttribute("message", "Database populated...");
      target = "success.jsp";
      
    } catch (PersonDaoException e) {
      e.printStackTrace();
      request.setAttribute("message", e.getMessage());
      target = "error.jsp";
    }
    
    request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
