package com.iteachcoding.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;
import com.iteachcoding.web.dao.PersonDao;
import com.iteachcoding.web.dao.impl.PersonDaoException;
import com.iteachcoding.web.dao.impl.PersonDaoImpl;
import com.iteachcoding.web.model.Person;

/**
 * Servlet implementation class AddPersonController
 */
@WebServlet("/AddPerson")
public class AddPersonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String target = null;
    
    try {
      final String firstName = request.getParameter("firstName");
      final String lastName = request.getParameter("lastName");
      final String ageString = request.getParameter("age");
      final String favoriteColor = request.getParameter("favoriteColor");

      if (Strings.isNullOrEmpty(firstName)
          || Strings.isNullOrEmpty(lastName)
          || Strings.isNullOrEmpty(ageString)
          || Strings.isNullOrEmpty(favoriteColor)) {

        request.setAttribute("message", "You must complete all fields to submit the form.");
        target = "error.jsp";
        
      } else {
        
        try {
          final int age = Integer.parseInt(ageString);
          
          final PersonDao personDao = new PersonDaoImpl();
          
          final Person person = new Person(firstName, lastName, age, favoriteColor);
          personDao.insertPerson(person);
          
          request.setAttribute("message", "The person has been added to the database.");
          target = "success.jsp";
        } catch (final NumberFormatException e) {
          e.printStackTrace();
          request.setAttribute("message", "You must enter a number for the age.");
          target = "error.jsp";
        }
        
      }
      
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
