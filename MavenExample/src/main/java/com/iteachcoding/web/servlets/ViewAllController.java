/**
 * 
 */
package com.iteachcoding.web.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.iteachcoding.web.comparators.AgeComparator;
import com.iteachcoding.web.comparators.LastNameComparator;
import com.iteachcoding.web.model.Person;
import com.iteachcoding.web.util.WorkbookUtility;

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
    
    final String fileName = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);
    final File inputFile = new File(fileName);
    
    String target = null;
    
    try {
      
      final List<Person> people = WorkbookUtility.retrievePeopleFromWorkbook(inputFile);
      
      final String sortType = request.getParameter("sortType");
      
      if (sortType != null) {
        sortPeople(people, sortType);
      }
      
      request.setAttribute("people", people);
      
      target = "view-all.jsp";
      
    } catch (InvalidFormatException e) {
      e.printStackTrace();
      throw new IOException("The workbook file has an invalid format.");
    }
    
    request.getRequestDispatcher(target).forward(request, response);
  }

  private void sortPeople(final List<Person> people, final String sortType) {
    switch (sortType) {
    case "lastName":
      Collections.sort(people, new LastNameComparator());
      break;
    case "age":
      Collections.sort(people, new AgeComparator());
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
