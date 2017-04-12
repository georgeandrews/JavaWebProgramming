/**
 * 
 */
package com.iteachcoding.web.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
 * Servlet implementation class ViewAllController
 */
@WebServlet("/ViewAll")
public class ViewAllController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String target = null;
    
    try {
      
      final PersonDao personDao = new PersonDaoImpl();
      final List<Person> people = personDao.retrievePeople();
      
      final String sortType = request.getParameter("sortType");
      
      if (sortType != null) {
        sortPeople(people, sortType);
      }
      
      request.setAttribute("people", people);
      
      target = "view-all.jsp";
      
    } catch (PersonDaoException e) {
      e.printStackTrace();
      request.setAttribute("message", e.getMessage());
      target = "error.jsp";
    }
    
    request.getRequestDispatcher(target).forward(request, response);
  }

  private void sortPeople(final List<Person> people, final String sortType) {
    switch (sortType) {
    case "lastName":
      Collections.sort(people, (person1, person2) -> person1.getLastName().compareTo(person2.getLastName()));
      break;
    case "age":
      Collections.sort(people, (person1, person2) -> person1.getAge().compareTo(person2.getAge()));
      break;
    case "firstName":
      Collections.sort(people, (person1, person2) -> person1.getFirstName().compareTo(person2.getFirstName()));
      break;
    case "favoriteColor":
      Collections.sort(people, (person1, person2) -> person1.getFavoriteColor().compareTo(person2.getFavoriteColor()));
      break;
    default:
      break;
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
