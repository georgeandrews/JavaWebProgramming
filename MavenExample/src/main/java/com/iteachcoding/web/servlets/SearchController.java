package com.iteachcoding.web.servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iteachcoding.web.dao.PersonDao;
import com.iteachcoding.web.dao.impl.PersonDaoException;
import com.iteachcoding.web.dao.impl.PersonDaoImpl;
import com.iteachcoding.web.model.Person;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/Search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String target = null;
    
    try {
      
      final PersonDao personDao = new PersonDaoImpl();
      final List<Person> people = personDao.retrievePeople();
      
      final String firstName = request.getParameter("firstName");
      
      final List<Person> filtered = people
                                      .stream()
                                      .filter((person) -> person.getFirstName().equalsIgnoreCase(firstName))
                                      .collect(Collectors.toList());
      
      request.setAttribute("people", filtered);
      
      target = "view-all.jsp";
      
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
